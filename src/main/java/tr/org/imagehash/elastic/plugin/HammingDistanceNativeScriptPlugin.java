/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.org.imagehash.elastic.plugin;

import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.script.ScriptModule;
import tr.org.imagehash.elastic.plugin.hamming.HammingDistanceScriptFactory;

/**
 *
 * @author safakcirag@gmail.com
 */
public class HammingDistanceNativeScriptPlugin extends Plugin {

	@Override
	public String description() {
		return "Native script by Şafak ÇIRAĞ <safakcirag@gmail.com>";
	}

	@Override
	public String name() {
		return "hamming_distance";
	}

	public void onModule(ScriptModule module) {
		// Register each script that we defined in this plugin
		module.registerScript("hamming_distance", HammingDistanceScriptFactory.class);
	}
}