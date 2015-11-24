package com.agile.asyoumean.util;

import java.text.DecimalFormat;
import java.util.Hashtable;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.service.impl.WorldUtilService;

public class WorldUtil {

	
	private WorldUtilService worldUtilService = new WorldUtilService();

	private static WorldUtil worldUtil = null; 
	
	public static WorldUtil getInstance(){
		
		if(worldUtil == null)
			worldUtil = new WorldUtil();
		return worldUtil;
	}
	
	public Hashtable<String, String> getWordList() {
		return worldUtilService.getWordList();
	}

	//Set word list
	public void setWordList(Hashtable<String, String> wordList) {
		worldUtilService.setWordList(wordList);
	}

	//Method for word match
	public AsYouMeanResult wordMatch(String keyword){
		return worldUtilService.wordMatch(keyword);
	}
	
}
