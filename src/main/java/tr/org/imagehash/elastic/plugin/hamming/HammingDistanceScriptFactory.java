/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.org.imagehash.elastic.plugin.hamming;

import java.math.BigInteger;
import java.util.Map;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.xcontent.support.XContentMapValues;
import org.elasticsearch.index.fielddata.ScriptDocValues;
import org.elasticsearch.script.AbstractFloatSearchScript;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;
import org.elasticsearch.script.ScriptException;

/**
 *
 * @author safakcirag@gmail.com
 */
public class HammingDistanceScriptFactory implements NativeScriptFactory {

	@Override
	public ExecutableScript newScript(@Nullable Map<String, Object> params) {
		String fieldName = params == null ? null : XContentMapValues.nodeStringValue(params.get("field"), null);
		String hashValue = params == null ? null : XContentMapValues.nodeStringValue(params.get("hash"), null);

		if (fieldName == null) {
			throw new ScriptException("Missing the field parameter");
		}
		return new HammingDistanceScript(fieldName, hashValue);
	}

	@Override
	public boolean needsScores() {
		return true;
	}

	/**
	 * This script takes a long value from the field specified in the
	 * parameter field. And calculates a score based on Hamming distance to value in field 'hash'
	 */
	private static class HammingDistanceScript extends AbstractFloatSearchScript {
            
            private String field;
            private String hash;
            private int length;

            public HammingDistanceScript(String fieldName, String hashValue) {
                super();
                field = fieldName;
                hash = hashValue;
                if(hash != null){
                    length = hash.length() * 4;
                }
            }

            private int hammingDistance(String lhs, String rhs){          
                return length - new BigInteger(lhs, 16).xor(new BigInteger(rhs, 16)).bitCount();
            }

            @Override
            public float runAsFloat() {
                String fieldValue = ((ScriptDocValues.Strings) doc().get(field)).getValue();
                //Serious arse covering:
                if(hash == null || fieldValue == null || fieldValue.length() != hash.length()){
                    return 0.0f;
                }

                return hammingDistance(fieldValue, hash);
            }

	}
}
