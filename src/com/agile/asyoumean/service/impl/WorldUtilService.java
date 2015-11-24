package com.agile.asyoumean.service.impl;

import java.util.Hashtable;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.util.JaroWinklerDistance;

import java.text.DecimalFormat;

public class WorldUtilService {
	private Hashtable<String, String> wordList = null;

	public Hashtable<String, String> getWordList() {
		return wordList;
	}

	public void setWordList(Hashtable<String, String> wordList) {
		this.wordList = wordList;
	}

	public void init() {
		if (wordList == null)
			getWorldList();
	}

	public void getWorldList() {
		wordList = CoreDAO.getInstance().getDummyWordList();
	}

	public AsYouMeanResult wordMatch(String keyword) {
		AsYouMeanResult result = new AsYouMeanResult();
		long start = System.currentTimeMillis();
		init();
		System.out.println("cache time: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		double distance = 0;
		double distanceDelta = 0;
		keyword = keyword.toUpperCase();
		String matchedWord = "";
		String similarity = "";
		for (String word : wordList.keySet()) {
			if (keyword.equals(wordList.get(word))) {
				result.setWordGuessed(word);
				result.setSimilarityRatio("%100");
				return result;
			}
			JaroWinklerDistance jaroWDistance = new JaroWinklerDistance();
			distance = jaroWDistance.getDistance(word, keyword);
			if (distance > distanceDelta) {
				distanceDelta = distance;
				matchedWord = word;
			}
		}
		DecimalFormat df = new DecimalFormat("#.####");
		similarity = "" + df.format((distanceDelta * 100));
		System.out.println("jaro winkler match time: " + (System.currentTimeMillis() - start));
		result.setWordGuessed(matchedWord);
		result.setSimilarityRatio(similarity);
		return result;
	}
}