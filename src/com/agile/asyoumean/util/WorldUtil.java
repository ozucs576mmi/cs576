package com.agile.asyoumean.util;

import java.text.DecimalFormat;
import java.util.Hashtable;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;

public class WorldUtil {

	
	private WorldUtilProduct worldUtilProduct = new WorldUtilProduct();

	private static WorldUtil worldUtil = null; 
	
	public static WorldUtil getInstance(){
		
		if(worldUtil == null)
			worldUtil = new WorldUtil();
		return worldUtil;
	}
	
	public Hashtable<String, String> getWordList() {
		return worldUtilProduct.getWordList();
	}

	//Set word list
	public void setWordList(Hashtable<String, String> wordList) {
		worldUtilProduct.setWordList(wordList);
	}

	//Method for word match
	public AsYouMeanResult wordMatch(String keyword){
		return worldUtilProduct.wordMatch(keyword);
	}
	
}
