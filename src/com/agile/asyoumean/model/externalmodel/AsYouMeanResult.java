package com.agile.asyoumean.model.externalmodel;

import javax.xml.bind.annotation.XmlElement;

public class AsYouMeanResult {

	private String wordGuessed;
	private String similarityRatio;
	
	
	@XmlElement(name="wordGuessed")
	public String getWordGuessed() {
		return wordGuessed;
	}
	public void setWordGuessed(String wordGuessed) {
		this.wordGuessed = wordGuessed;
	}
	@XmlElement(name="similarityRatio")
	public String getSimilarityRatio() {
		return similarityRatio;
	}
	public void setSimilarityRatio(String similarityRatio) {
		this.similarityRatio = similarityRatio;
	}
	public String toString(){
		return "WordGuessed: " + wordGuessed + "<br/> SimilarityRatio: " + similarityRatio;
	}
}
