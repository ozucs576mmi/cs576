package com.agile.asyoumean.model.externalmodel;

public class StatisticsItem {

	String matchedWord, givenWord;
	int matchedWordCount, givenWordCount;
	
	public String getMatchedWord() {
		return matchedWord;
	}
	
	public void setMatchedWord(String matchedWord) {
		this.matchedWord = matchedWord;
	}
	
	public String getGivenWord() {
		return givenWord;
	}
	
	public void setGivenWord(String givenWord) {
		this.givenWord = givenWord;
	}
	
	public int getMatchedWordCount() {
		return matchedWordCount;
	}
	
	public void setMatchedWordCount(int matchedWordCount) {
		this.matchedWordCount = matchedWordCount;
	}
	
	public int getGivenWordCount() {
		return givenWordCount;
	}
	
	public void setGivenWordCount(int givenWordCount) {
		this.givenWordCount = givenWordCount;
	}

}
