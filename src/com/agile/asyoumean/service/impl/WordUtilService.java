package com.agile.asyoumean.service.impl;import java.text.DecimalFormat;import java.util.Hashtable;import com.agile.asyoumean.dao.CoreDAO;import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;import com.agile.asyoumean.util.JaroWinklerDistance;public class WordUtilService {	private Hashtable<String, String> wordList = null;	public Hashtable<String, String> getWordList() {		return wordList;	}	public void setWordList(Hashtable<String, String> wordList) {		this.wordList = wordList;	}	private void init() {		if (wordList == null)			getWorldList();	}    public void getWorldList() {		wordList = CoreDAO.getInstance().getDummyWordList();	}	// Method for word match	public AsYouMeanResult wordMatch(String keyword) {		AsYouMeanResult result = new AsYouMeanResult();		long start = System.currentTimeMillis();		double distance = 0;		double distanceDelta = 0;		String matchedWord = "";		String similarity = "";		JaroWinklerDistance jaroWDistance = new JaroWinklerDistance();		// kelime listesini db'den okuyup wordList'e yaz		init();		System.out.println("cache time: "				+ (System.currentTimeMillis() - start));		start = System.currentTimeMillis();		keyword = keyword.toLowerCase();		for (String word : wordList.keySet()) {			// kelime doðrudan bulunursa algoritmadan geçmeden döndürülür			if (keyword.equals(wordList.get(word))) {				result.setWordGuessed(word);				result.setSimilarityRatio("%100");				return result;			}			distance = jaroWDistance.getDistance(word, keyword);			if (distance > distanceDelta) {				distanceDelta = distance;				matchedWord = word;			}		}		DecimalFormat df = new DecimalFormat("#.####");		similarity = "" + df.format((distanceDelta * 100));		// Print the matching time		System.out.println("jaro winkler word matching time: "				+ (System.currentTimeMillis() - start));		result.setWordGuessed(matchedWord);		result.setSimilarityRatio(similarity);		return result;			}}