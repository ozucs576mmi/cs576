package com.agile.asyoumean.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.UnselectEvent;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.dao.UserDAO;
import com.agile.asyoumean.model.externalmodel.DictionaryItem;
import com.agile.asyoumean.model.externalmodel.StatisticsItem;
import com.agile.asyoumean.model.externalmodel.User;
import com.agile.asyoumean.util.Util;

@SessionScoped
@ManagedBean(name = "adminPageBean")
public class AdminPageBean extends Util implements Serializable {

	List<DictionaryItem> dictionarylist;
	private static final long serialVersionUID = 8915515470802383339L;
	private List<DictionaryItem> dictionary;
	private List<User> users;
	private DictionaryItem selectedWord;
	private User selectedUser;
	private DictionaryItem newWord = new DictionaryItem();
	private String matchedWord;
	private String searchedWord;
	private boolean superUser;
	private String algorithmType;
	private List<StatisticsItem> mostCommonMistakes;
	private List<StatisticsItem> topTenMistakes;
	List<DictionaryItem> wordList;
	private StatisticsItem selectedStatisticsItem;

	@PostConstruct
	public void postConstruct() {
		users = listUsers();
//		dictionary = listDictionary();
		topTenMistakes=listTopTenMistakes();
		checkSuperUser();

	}

	public void clearCommonMistakesSearch() {

		mostCommonMistakes = null;

	}
	
	public void clearSearchedWords() {

		mostCommonMistakes = null;

	}

	public List<User> listUsers() {

		List<User> userList = UserDAO.getInstance().userList();

		return userList;
	}

	public List<StatisticsItem> listCommonMistakes() {

		mostCommonMistakes = CoreDAO.getInstance().getStatisticsForCommon(
				matchedWord);

		return mostCommonMistakes;
	}
	
	public List<StatisticsItem> listTopTenMistakes() {

		topTenMistakes = CoreDAO.getInstance().getStatisticsFromLog();

		return topTenMistakes;
	}
	
	

	public List<DictionaryItem> listDictionary() {

		wordList = CoreDAO.getInstance().searchWord(searchedWord);

		return wordList;
	}

	private boolean checkSuperUser() {

		HttpSession session = Util.getSession();
		if (session.getAttribute("username").toString()
				.equalsIgnoreCase("admin")) {

			superUser = true;
		}

		return superUser;

	}

	public void removeItem() {

		CoreDAO.getInstance().deleteWord(selectedWord.getWord());
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Selected word removed from dictionary",
						"Selected word removed from dictionary!"));

		selectedWord = null;
		
	}
	
	public void tabchangelistenerForAnalysis( TabChangeEvent event ){
		
	
			
			topTenMistakes=listTopTenMistakes();
			
		
	}
	
	
	
	

	public void onRowSelect(SelectEvent event) {

		selectedWord = (DictionaryItem) event.getObject();
	}

	public void onRowUnselect(UnselectEvent event) {

		selectedWord = null;

	}

	public void onRowSelectUser(SelectEvent event) {

		selectedUser = (User) event.getObject();
	}

	public void onRowUnselectUser(UnselectEvent event) {

		selectedUser = null;

	}
	
	public void onRowSelectStatisticsItem(SelectEvent event) {

		selectedStatisticsItem = (StatisticsItem) event.getObject();
	}

	public void onRowUnselectStatisticsItem(UnselectEvent event) {

		selectedStatisticsItem = null;

	}
	
	
	public void addExactMatch(){
		
		CoreDAO.getInstance().addExactMatch(selectedStatisticsItem.getMatchedWord(), selectedStatisticsItem.getGivenWord());
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Exactmatch Added", "Exactmatch Added!"));
		
	}

	public void addItem() {

		if (newWord.getWord().equalsIgnoreCase("")) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Please Enter New Word", "Please Enter New Word!"));
		} else {
		List<DictionaryItem> existedWord=	CoreDAO.getInstance().searchWord(newWord.getWord());
			
		if (existedWord.isEmpty()) {

				CoreDAO.getInstance().insertWord(newWord);

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Given word added to dictionary",
								"Given word added to dictionary!"));
//				dictionary = listDictionary();

			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Given word already in dictionary",
								"Given word already in dictionary!"));

			}

		}
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

	public String getAlgorithmType() {
		return algorithmType;
	}

	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}

	public List<StatisticsItem> getMostCommonMistakes() {
		return mostCommonMistakes;
	}

	public void setMostCommonMistakes(List<StatisticsItem> mostCommonMistakes) {
		this.mostCommonMistakes = mostCommonMistakes;
	}

	public String getMatchedWord() {
		return matchedWord;
	}

	public void setMatchedWord(String matchedWord) {
		this.matchedWord = matchedWord;
	}

	public List<StatisticsItem> getTopTenMistakes() {
		return topTenMistakes;
	}

	public void setTopTenMistakes(List<StatisticsItem> topTenMistakes) {
		this.topTenMistakes = topTenMistakes;
	}

	public String getSearchedWord() {
		return searchedWord;
	}

	public void setSearchedWord(String searchedWord) {
		this.searchedWord = searchedWord;
	}

	public List<DictionaryItem> getWordList() {
		return wordList;
	}

	public void setWordList(List<DictionaryItem> wordList) {
		this.wordList = wordList;
	}

	public StatisticsItem getSelectedStatisticsItem() {
		return selectedStatisticsItem;
	}

	public void setSelectedStatisticsItem(StatisticsItem selectedStatisticsItem) {
		this.selectedStatisticsItem = selectedStatisticsItem;
	}

}
