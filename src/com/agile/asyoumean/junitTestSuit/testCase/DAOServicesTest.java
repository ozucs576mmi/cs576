package com.agile.asyoumean.junitTestSuit.testCase;

import static org.junit.Assert.assertTrue;

import java.util.Hashtable;
import java.util.List;

import org.junit.Test;

import com.agile.asyoumean.dao.CoreDAO;
<<<<<<< HEAD
import com.agile.asyoumean.model.externalmodel.DictionaryItem;
import com.agile.asyoumean.model.externalmodel.MatchRatioItem;
import com.agile.asyoumean.model.externalmodel.StatisticsItem;
=======
import com.agile.asyoumean.model.externalmodel.*;
>>>>>>> origin/master


public class DAOServicesTest {
	
	   @Test
	   public void getWordListTest() {
		   
		   List<DictionaryItem> testList = CoreDAO.getInstance().getWordList();
		   
		   assertTrue(testList.size() > 0);
   
	   }
	   
	   @Test
	   public void getDummyWordListTest() {
		   
		   Hashtable<String, String> testHashtable = CoreDAO.getInstance().getDummyWordList();
		   
		   assertTrue(testHashtable.size() > 0);
		   
	   }
	   
	   @Test
	   public void getMatchRatioTest() {
		   
		   List<MatchRatioItem> testList = CoreDAO.getInstance().getMatchRatio();
		   
		   assertTrue(testList.size() > 0);
   
	   }
	   
	   @Test
	   public void getStatisticsForCommonTest() {
		   
		    List<StatisticsItem> testList = CoreDAO.getInstance().getStatisticsForCommon("turkcell");
		    
		    assertTrue(testList.size() > 0);
   
	   }
	   
	   @Test
	   public void getStatisticsFromLogTest() {
		   
		    List<StatisticsItem> testList = CoreDAO.getInstance().getStatisticsFromLog();
		    
		    assertTrue(testList.size() > 0);
   
	   }
	   
	   @Test
	   public void searchWordTest() {
		   
<<<<<<< HEAD
		   List<DictionaryItem> testList = CoreDAO.getInstance().searchWord("turkcell");
		    
		    assertTrue(testList.get(0).getWord().toUpperCase().equals("TURKCELL"));
=======
		    DictionaryWordItem testList = CoreDAO.getInstance().searchWord("turkcell");
		    
		    assertTrue(testList.getWord().toUpperCase().equals("TURKCELL"));
>>>>>>> origin/master
   
	   }
	   
	   

}
