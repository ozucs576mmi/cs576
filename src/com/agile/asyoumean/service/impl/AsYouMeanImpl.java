package com.agile.asyoumean.service.impl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.service.AsYouMeanService;

@WebService(serviceName="AsYouMeanService", name="AsYouMeanPortType", targetNamespace="http://www.turkcell.com.tr/webServices/AsYouMeanService")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT,
		use=SOAPBinding.Use.LITERAL,
		parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class AsYouMeanImpl implements AsYouMeanService{

	public AsYouMeanResult getAsYouMean(String keyword){
		
		AsYouMeanResult asYouMeanResult = new AsYouMeanResult();
		
		asYouMeanResult.setSimilarityRatio("%80");
		asYouMeanResult.setWordGuessed("dummy");
		
		return asYouMeanResult;
	}
}
