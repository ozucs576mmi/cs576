package com.agile.asyoumean.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.dao.UserDAO;
import com.agile.asyoumean.model.externalmodel.DictionaryItem;
import com.agile.asyoumean.model.externalmodel.User;
import com.agile.asyoumean.util.Util;

@ViewScoped
@ManagedBean(name = "adminPageBean")
public class AdminPageBean extends Util implements Serializable {

    List<DictionaryItem> dictionarylist;
    private static final long serialVersionUID = 8915515470802383339L;
    private List<DictionaryItem> dictionary;
    private List<User> users;
    private DictionaryItem selectedWord;
    private User selectedUser;
    private DictionaryItem newWord = new DictionaryItem();
    private boolean superUser;
    private String algorithmType;

    @PostConstruct
    public void postConstruct() {
        users = listUsers();
        dictionary= listDictionary();
        checkSuperUser();
    }

    public List<User> listUsers() {

        List<User> userList = UserDAO.getInstance().userList();

        return userList;
    }
    
    public List<DictionaryItem> listDictionary() {

        List<DictionaryItem> wordList = CoreDAO.getInstance().getWordList();

        return wordList;
    }


    private boolean checkSuperUser() {

        HttpSession session = Util.getSession();
        if (session.getAttribute("username").toString().equalsIgnoreCase("admin")) {

            superUser = true;
        }

        return superUser;

    }

    public void removeItem() {

        dictionary.remove(selectedWord);
        selectedWord = null;
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

    public void addItem() {

        dictionary.add(newWord);
        selectedWord = null;

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

}
