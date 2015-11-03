package com.agile.asyoumean.junitTestSuit.testCase;

import org.junit.Test;

import com.agile.asyoumean.util.JaroWinklerDistance;

import static org.junit.Assert.assertEquals;

public class Algorithm1Test {

   String result = "83.33333333333334";	
   JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();
   
   @Test
   public void testPrintMessage() {	
	   System.out.println(jaroWinklerDistance.getDistancePercentage("ABBA", "BAAB"));
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage("ABBA", "BAAB"));   
   }
}