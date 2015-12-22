package com.agile.asyoumean.util;

import java.util.Hashtable;

import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;
import com.agile.asyoumean.service.impl.WordUtilService;

public class WordUtil {

    private WordUtilService wordUtilService = new WordUtilService();

    private static WordUtil wordUtil = null;

    public static WordUtil getInstance() {

        if (wordUtil == null)
            wordUtil = new WordUtil();
        return wordUtil;
    }

    public Hashtable<String,String> getWordList() {
        return wordUtilService.getWordList();
    }

    // Set word list
    public void setWordList(Hashtable<String,String> wordList) {
        wordUtilService.setWordList(wordList);
    }

    // Method for word match
    public AsYouMeanResult wordMatch(String keyword) {
        return wordUtilService.wordMatch(keyword);
    }

}
