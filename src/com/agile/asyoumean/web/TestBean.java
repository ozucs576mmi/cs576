package com.agile.asyoumean.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.util.AsYouMeanUtil;


@ViewScoped
@ManagedBean(name="testBean")
public class TestBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915515470802383339L;
	private String test;
	private String resultMessage;

	public void action() {
		
		AsYouMeanResult asYouMeanResult = AsYouMeanUtil.getInstance().getAsYouMeanResult(test);
		
		resultMessage = "Input: " + test + "<br/>" + asYouMeanResult.toString();
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("infoDialogWidget.show()");
	//	addMessage("input: " + test + " " + asYouMeanResult.toString());
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
