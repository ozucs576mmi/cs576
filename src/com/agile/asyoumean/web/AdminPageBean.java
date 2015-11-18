package com.agile.asyoumean.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.agile.asyoumean.model.externalmodel.DictionaryItem;

@ViewScoped
@ManagedBean(name = "adminPageBean")
public class AdminPageBean  implements Serializable {
	
	
	 List<DictionaryItem> dictionarylist;
	 private static final long serialVersionUID = 8915515470802383339L;
	 private List<DictionaryItem> dictionary;
	 private DictionaryItem selectedWord;
	 private DictionaryItem newWord= new DictionaryItem();

	@PostConstruct
	public void postConstruct(){
		
		dictionary=listDictionary ();	
	}

	private final static String[] word;

	private final static String[] exactMatch;

	static {
		word = new String[10];
		word[0] = "ABD";
		word[1] = "Abdal";
		word[2] = "abdallýk";
		word[3] = "abdest";
		word[4] = "abdestbozan";
		word[5] = "abdesthane";
		word[6] = "abdestlik";
		word[7] = "Yeabdestsiz";
		word[8] = "abece";
		word[9] = "aberasyon";

		exactMatch = new String[10];
		exactMatch[0] = "ABD";
		exactMatch[1] = "";
		exactMatch[2] = "";
		exactMatch[3] = "abdest";
		exactMatch[4] = "";
		exactMatch[5] = "";
		exactMatch[6] = "";
		exactMatch[7] = "";
		exactMatch[8] = "";
		exactMatch[9] = "";
	}
	 public List<DictionaryItem> listDictionary () {
	       
		 List<DictionaryItem> dictionarylist = new ArrayList<DictionaryItem>();
		 
	        for(int i = 0 ; i < 10 ; i++) {
	        	
	        	DictionaryItem   	dictionaryItem=new DictionaryItem();
	        	dictionaryItem.setWord(word[i]);
	        	dictionaryItem.setExactMatch(exactMatch[i]);
	        	
	        	
	        	dictionarylist.add(dictionaryItem);
	        }
	         
	        return dictionarylist;
	    }
	 
	 public void removeItem() {
		 
		 dictionary.remove(selectedWord);
		 selectedWord = null;
	   }
	 
	 public void onRowSelect(SelectEvent event) {
		 
		 selectedWord=(DictionaryItem) event.getObject();
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	    	
	    	selectedWord=null;
	        
	    }
	 
	    public void addItem() {
	    	
			 dictionary.add(newWord);
			 selectedWord = null;
			 
		   }
	public static String[] getWord() {
		return word;
	}
	public static String[] getExactmatch() {
		return exactMatch;
	}
	public List<DictionaryItem> getDictionarylist() {
		return dictionarylist;
	}
	public void setDictionarylist(List<DictionaryItem> dictionarylist) {
		this.dictionarylist = dictionarylist;
	}
	public List<DictionaryItem> getDictionary() {
		return dictionary;
	}
	public void setDictionary(List<DictionaryItem> dictionary) {
		this.dictionary = dictionary;
	}

	public DictionaryItem getSelectedWord() {
		return selectedWord;
	}

	public void setSelectedWord(DictionaryItem selectedWord) {
		this.selectedWord = selectedWord;
	}

	public DictionaryItem getNewWord() {
		return newWord;
	}

	public void setNewWord(DictionaryItem newWord) {
		this.newWord = newWord;
	}

}
