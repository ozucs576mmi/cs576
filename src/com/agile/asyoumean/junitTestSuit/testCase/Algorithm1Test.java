package com.agile.asyoumean.junitTestSuit.testCase;

import org.junit.Test;

import com.agile.asyoumean.util.JaroWinklerDistance;

import static org.junit.Assert.assertEquals;

public class Algorithm1Test {

   
   JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();
   
   @Test
   public void compareTest() {	
	   String result = "83.33333333333334";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage("ABBA", "BAAB"));   
   }
   
   @Test
   public void sourceEmptyTest() {
	   String result = "0.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage("", "BAAB"));  
   }
   
   @Test
   public void sourceNullTest() {	
	   String result = "0.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage(null, "BAAB"));   
   }
   
   @Test
   public void targetEmptyTest() {
	   String result = "0.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage("BAAB", ""));  
   }
   
   @Test
   public void targetNullTest() {	
	   String result = "0.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage("BAAB", null));   
   }
   
   @Test
   public void sourceNullTargetNullTest() {	
	   String result = "0.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage(null, null));   
   }
   
   @Test
   public void sourceEmptyTargetEmptyTest() {	
	   String result = "100.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage("", ""));   
   }
   
   @Test
   public void sourceEmptyTargetNullTest() {	
	   String result = "0.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage("", null));   
   }
   
   @Test
   public void sourceNullTargetEmptyTest() {	
	   String result = "0.0";	
       assertEquals(result, "" + jaroWinklerDistance.getDistancePercentage(null, ""));   
   }
   
}