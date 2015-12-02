package com.agile.asyoumean.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.agile.asyoumean.model.externalmodel.AsYouMeanRequestException;
import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.util.AsYouMeanUtil;

@WebService(serviceName = "AsYouMeanService",
            name = "AsYouMeanPortType",
            targetNamespace = "http://www.turkcell.com.tr/webServices/AsYouMeanService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
             use = SOAPBinding.Use.LITERAL,
             parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class AsYouMeanImpl {

	@WebMethod
	@WebResult(name = "AsYouMeanResult")
	public AsYouMeanResult getAsYouMean(@WebParam(name = "keyword") String keyword) throws AsYouMeanRequestException {
		
		if (keyword.isEmpty()) {

			throw new AsYouMeanRequestException("Request cannot be null", "Request cannot be null");

		} else {
			
			return AsYouMeanUtil.getInstance().getAsYouMeanResult(keyword);

		}

	}
	
}
