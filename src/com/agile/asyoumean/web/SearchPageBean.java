package com.agile.asyoumean.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.model.externalmodel.DictionaryItem;
import com.agile.asyoumean.util.AsYouMeanUtil;
import com.agile.asyoumean.util.Constants;
import com.agile.asyoumean.util.WordUtil;

@SessionScoped
@ManagedBean(name = "SearchPageBean")
public class SearchPageBean implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8915515470802383339L;
    private String enteredText;
    private String resultMessage;
    private List<DictionaryItem> dictionaryList;
    private AsYouMeanResult asYouMeanResult;
    
    @PostConstruct
    public void init() {
    	
    	asYouMeanResult=null;
    	enteredText=null;
    	
    }

    public List<DictionaryItem> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(List<DictionaryItem> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    public void action() {

    	if (enteredText.isEmpty()) {

			resultMessage = Constants.MSG_ERROR;
            RequestContext context = getCurrentInstance();
            context.execute("infoDialogWidget.show()");
        } else {
    	asYouMeanResult = AsYouMeanUtil.getInstance().getAsYouMeanResult(enteredText);
        }
        //showResult(asYouMeanResult);
    }

    private void showResult(AsYouMeanResult asYouMeanResult) {
	
        if (enteredText.isEmpty()) {

			resultMessage = Constants.MSG_ERROR;
            RequestContext context = getCurrentInstance();
            context.execute("infoDialogWidget.show()");
        } else {

            //???resultMessage = "Input: " + enteredText + "<br/>" + asYouMeanResult.toString();
			
			// iþlemi db'ye log'la
			CoreDAO.getInstance().logResultsToDB(enteredText, asYouMeanResult);

            RequestContext context = getCurrentInstance();
            context.execute("infoDialogWidget.show()");
            // addMessage("input: " + enteredText + " " + asYouMeanResult.toString());
        }
    }

    private RequestContext getCurrentInstance() {
	
        RequestContext context = RequestContext.getCurrentInstance();
		
        return context;
		
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

    public String getEnteredText() {
        return enteredText;
    }

    public void setEnteredText(String enteredText) {
        this.enteredText = enteredText;
    }

	public AsYouMeanResult getAsYouMeanResult() {
		return asYouMeanResult;
	}

	public void setAsYouMeanResult(AsYouMeanResult asYouMeanResult) {
		this.asYouMeanResult = asYouMeanResult;
	}
	
	public void cacheRefresh(){
		WordUtil.getInstance().getWordList();
	}

}
