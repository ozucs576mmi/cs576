package com.agile.asyoumean.util;

import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;

public class AsYouMeanUtil {
	
	private static AsYouMeanUtil asYouMeanUtil = null;

	public static AsYouMeanUtil getInstance(){
		
		if(asYouMeanUtil == null)
			asYouMeanUtil = new AsYouMeanUtil();
		return asYouMeanUtil;
	}
	
	public AsYouMeanResult getAsYouMeanResult(String keyword){
		
		AsYouMeanResult asYouMeanResult = new AsYouMeanResult();
		
		asYouMeanResult.setSimilarityRatio("%80");
		asYouMeanResult.setWordGuessed("dummy");
		
		return asYouMeanResult;
	}
}
