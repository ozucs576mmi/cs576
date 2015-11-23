package com.agile.asyoumean.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.agile.asyoumean.dao.UserDAO;
import com.agile.asyoumean.model.externalmodel.DictionaryItem;
import com.agile.asyoumean.model.externalmodel.User;
import com.agile.asyoumean.util.Util;

@ViewScoped
@ManagedBean(name = "adminPageBean")
public class AdminPageBean  extends Util implements Serializable {
	
	
	 List<DictionaryItem> dictionarylist;
	 private static final long serialVersionUID = 8915515470802383339L;
	 private List<DictionaryItem> dictionary;
	 private List<User> users;
	 private DictionaryItem selectedWord;
	 private User selectedUser;
	 private DictionaryItem newWord= new DictionaryItem();
	 private boolean superUser;

	@PostConstruct
	public void postConstruct(){
		
		dictionary=listDictionary ();	
		users=listUsers();
		checkSuperUser();
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
	 
	 public  List<User> listUsers () {
	      
			List<User> userList = UserDAO.getInstance().userList();
		 
		
	         
	        return userList;
	    }
	 
	 private boolean checkSuperUser(){
		 
			HttpSession session = Util.getSession();
		if(	session.getAttribute("username").toString().equalsIgnoreCase("admin")){
			
			superUser=true;
		}
		
		return superUser;
		 
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
	    
	    public void onRowSelectUser(SelectEvent event) {
			 
			 selectedUser=(User) event.getObject();
		    }
		 
		    public void onRowUnselectUser(UnselectEvent event) {
		    	
		    	selectedUser=null;
		        
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}


}
