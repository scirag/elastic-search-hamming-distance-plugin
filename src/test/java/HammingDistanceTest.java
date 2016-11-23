/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class HammingDistanceTest {
    
    public HammingDistanceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void maximumHammingDistance(){
        String hash1 = "0000000000000000";
        String hash2 = "FFFFFFFFFFFFFFFF";
        
               
        BigInteger int1 = new BigInteger(hash1,16);
        BigInteger int2 = new BigInteger(hash2,16);
        
        int diff = int1.xor(int2).bitCount();
        System.out.println(diff);
        //assert(diff!=64);
        //assert();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
