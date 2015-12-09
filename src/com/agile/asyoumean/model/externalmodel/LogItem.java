package com.agile.asyoumean.model.externalmodel;

import java.util.Date;

public class LogItem {
	
	Date logDate;
	String givenWord;
	String  matchedWord;
	int accuracyRatio;
	String matchAlgorithm;
	
	
	
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getGivenWord() {
		return givenWord;
	}
	public void setGivenWord(String givenWord) {
		this.givenWord = givenWord;
	}
	public String getMatchedWord() {
		return matchedWord;
	}
	public void setMatchedWord(String matchedWord) {
		this.matchedWord = matchedWord;
	}
	public int getAccuracyRatio() {
		return accuracyRatio;
	}
	public void setAccuracyRatio(int accuracyRatio) {
		this.accuracyRatio = accuracyRatio;
	}
	public String getMatchAlgorithm() {
		return matchAlgorithm;
	}
	public void setMatchAlgorithm(String matchAlgorithm) {
		this.matchAlgorithm = matchAlgorithm;
	}
	

}
