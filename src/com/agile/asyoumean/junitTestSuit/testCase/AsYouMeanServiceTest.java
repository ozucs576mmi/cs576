package com.agile.asyoumean.junitTestSuit.testCase;

import org.junit.Test;

import com.agile.asyoumean.util.AsYouMeanUtil;

import static org.junit.Assert.assertEquals;

public class AsYouMeanServiceTest {

   String result = "WordGuessed: dummy<br/> SimilarityRatio: %80";
   AsYouMeanUtil asYouMeanUtil = AsYouMeanUtil.getInstance();
   
   @Test
   public void testPrintMessage() {	
	   System.out.println(asYouMeanUtil.getAsYouMeanResult("DUMMY"));
       assertEquals(result, "" + asYouMeanUtil.getAsYouMeanResult("DUMMY"));   
   }
}