package com.agile.asyoumean.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.model.externalmodel.DictionaryItem;
import com.agile.asyoumean.util.AsYouMeanUtil;

@ViewScoped
@ManagedBean(name = "testBean")
public class TestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915515470802383339L;
	private String test;
	private String resultMessage;
	private List<DictionaryItem> dictionaryList;

	public List<DictionaryItem> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<DictionaryItem> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public void action() {

		AsYouMeanResult asYouMeanResult = AsYouMeanUtil.getInstance().getAsYouMeanResult(test);

		showResult(asYouMeanResult);
	}

	private void showResult(AsYouMeanResult asYouMeanResult) {
		if (test.isEmpty()) {

			resultMessage = "Lutfen bir Keyword giriniz..!";
			RequestContext context = getCurrentInstance();
			context.execute("infoDialogWidget.show()");
		} else {

			resultMessage = "Input: " + test + "<br/>" + asYouMeanResult.toString();

			RequestContext context = getCurrentInstance();
			context.execute("infoDialogWidget.show()");
			// addMessage("input: " + test + " " + asYouMeanResult.toString());
		}
	}

	private RequestContext getCurrentInstance() {
		RequestContext context = RequestContext.getCurrentInstance();
		return context;
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
