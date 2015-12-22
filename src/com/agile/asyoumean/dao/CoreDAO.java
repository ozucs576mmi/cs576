<<<<<<< HEAD
package com.agile.asyoumean.dao;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.util.ArrayList;import java.util.Hashtable;import java.util.List;import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;import com.agile.asyoumean.model.externalmodel.DictionaryItem;import com.agile.asyoumean.model.externalmodel.DictionaryWordItem;import com.agile.asyoumean.model.externalmodel.MatchRatioItem;import com.agile.asyoumean.model.externalmodel.StatisticsItem;public class CoreDAO extends DAO {	private static CoreDAO coreDAO = new CoreDAO();	private Connection con = null;	private PreparedStatement pstmt = null;	private ResultSet rs = null;	public static CoreDAO getInstance() {		return coreDAO;	}	public List<DictionaryItem> getWordList() {		List<DictionaryItem> wordList = new ArrayList<DictionaryItem>();		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement("select WORD, EXACT_MATCH from uccs_tester.dummy_word_list");			rs = pstmt.executeQuery();			while (rs.next()) {				DictionaryItem dic = new DictionaryItem();				dic.setWord(rs.getString("WORD"));				dic.setExactMatch(rs.getString("EXACT_MATCH") == null ? "" : rs						.getString("EXACT_MATCH"));				wordList.add(dic);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return wordList;	}	public List<MatchRatioItem> getMatchRatio() {		List<MatchRatioItem> ratioList = new ArrayList<MatchRatioItem>();		MatchRatioItem ratio;		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"SELECT A.NUM segm, B.CNT " +					"FROM (" +					"  SELECT ROWNUM NUM FROM DUAL CONNECT BY ROWNUM <= 100) A " +					"LEFT OUTER JOIN ( " +					"SELECT FLOOR (ACCURACY_RATIO) AS ACC_SEGMENT, FLOOR (ACCURACY_RATIO) AS SEGM, SUM (1) AS CNT " +					"FROM UCCS_TESTER.DUMMY_SEARCH_LOG " +					"WHERE MATCH_ALGORITHM = 'JW' " +					"GROUP BY FLOOR (ACCURACY_RATIO)) B ON A.NUM = B.SEGM " +					"WHERE A.NUM >= 60 " +					"ORDER BY A.NUM"					);						rs = pstmt.executeQuery();			while (rs.next()) {				ratio = new MatchRatioItem();				ratio.setSegm(rs.getString("SEGM"));				ratio.setCnt(rs.getInt("CNT"));				ratioList.add(ratio);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return ratioList;	}	public Hashtable<String, String> getDummyWordList() {		Hashtable<String, String> wordList = new Hashtable<String, String>();		String keyword;		String strictMatch;		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement("select WORD, EXACT_MATCH from uccs_tester.dummy_word_list");			rs = pstmt.executeQuery();			while (rs.next()) {				keyword = rs.getString("WORD");				strictMatch = rs.getString("EXACT_MATCH") == null ? "" : rs.getString("EXACT_MATCH");				wordList.put(keyword.toLowerCase(), strictMatch);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return wordList;	}	public void logResultsToDB(String keyword, AsYouMeanResult asYouMeanResult){		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"insert into uccs_tester.dummy_search_log(GIVEN_WORD, MATCHED_WORD, ACCURACY_RATIO, MATCH_ALGORITHM) values('" 						+ keyword + "', '" 						+ asYouMeanResult.getWordGuessed() + "', "						+ asYouMeanResult.getSimilarityRatio().replace("%", "") + ", "						+ "'JW')"					);			rs = pstmt.executeQuery();		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}	}	public List<StatisticsItem> getStatisticsFromLog(){				List<StatisticsItem> statisticsList = new ArrayList<StatisticsItem>();		StatisticsItem statistic;		try {			con = JNDISource.getInstance().getBSCSConnection();			//en Ã§ok kullanÄ±lmÄ±ÅŸ ilk 10 kelimeyi belirle ve log detayÄ±nÄ± al								pstmt = con.prepareStatement(					"Select UPPER(BSD.MATCHED_WORD) MATCHED_WORD, BSD.CNT MATCHED_WORD_CNT, UPPER(ASD.GIVEN_WORD) GIVEN_WORD, Count(*) GIVEN_WORD_CNT " +					"From uccs_tester.DUMMY_SEARCH_LOG ASD " +					"Inner Join(" +					"  Select MATCHED_WORD, RANK() OVER(Order By MATCHED_WORD_COUNT Desc) NUM, MATCHED_WORD_COUNT CNT From (" +					"    Select Count(MATCHED_WORD) MATCHED_WORD_COUNT, MATCHED_WORD From uccs_tester.DUMMY_SEARCH_LOG " +					"    Group By MATCHED_WORD)) BSD On ASD.MATCHED_WORD = BSD.MATCHED_WORD " +					//"Where BSD.NUM < 20" +					"Having Count(*) > 1 " +					"Group By BSD.MATCHED_WORD, BSD.CNT, ASD.GIVEN_WORD " +					"Order By GIVEN_WORD_CNT Desc, BSD.CNT Desc");			rs = pstmt.executeQuery();			while (rs.next()) {				statistic = new StatisticsItem();				statistic.setMatchedWord(rs.getString("MATCHED_WORD"));				statistic.setMatchedWordCount(rs.getInt("MATCHED_WORD_CNT"));				statistic.setGivenWord(rs.getString("GIVEN_WORD"));				statistic.setGivenWordCount(rs.getInt("GIVEN_WORD_CNT"));				statisticsList.add(statistic);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return statisticsList;	}	public void insertWord(DictionaryItem newItem) {		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con					.prepareStatement("INSERT INTO uccs_tester.dummy_word_list  (WORD) VALUES (lower('"							+ newItem.getWord() +"') )");			rs = pstmt.executeQuery();		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}	}	public void deleteWord(String word){		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"DELETE FROM UCCS_TESTER.DUMMY_WORD_LIST WHERE lower(word) = '" +							word.toLowerCase() +							"'"					);			rs = pstmt.executeQuery();		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}	}	public List<DictionaryItem> searchWord(String word){				List<DictionaryItem> dictionaryWordItemList = new ArrayList<DictionaryItem>();		DictionaryItem dictionaryItem=null ;		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"SELECT WORD,EXACT_MATCH FROM UCCS_TESTER.DUMMY_WORD_LIST " +					"WHERE lower(word) = '" +						word.toLowerCase() + "'"					);			rs = pstmt.executeQuery();						dictionaryItem=new DictionaryItem();							while (rs.next()){								    	dictionaryItem.setWord(rs.getString("WORD").toLowerCase());					dictionaryItem.setExactMatch(rs.getString("EXACT_MATCH") == null ? "" : rs							.getString("EXACT_MATCH"));					dictionaryWordItemList.add(dictionaryItem);			    } 									} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return dictionaryWordItemList;	}		//en cok hata yapilan kelimeler 	public List<StatisticsItem> getStatisticsForCommon(String keyword){				List<StatisticsItem> statisticsList = new ArrayList<StatisticsItem>();		StatisticsItem statistic;		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"SELECT lower(given_word) GIVEN_WORD, COUNT (*)  GIVEN_WORD_CNT" +					" FROM dummy_search_log " +					" WHERE LOWER(matched_word) = '" +						keyword.toLowerCase() +					"' AND given_word != matched_word " +					" GROUP BY given_word" +					" ORDER BY COUNT (*) DESC");			rs = pstmt.executeQuery();			while (rs.next()) {				statistic = new StatisticsItem();				statistic.setGivenWord(rs.getString("GIVEN_WORD"));				statistic.setGivenWordCount(rs.getInt("GIVEN_WORD_CNT"));				statisticsList.add(statistic);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return statisticsList;	}}
=======
package com.agile.asyoumean.dao;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.util.ArrayList;import java.util.Hashtable;import java.util.List;import com.agile.asyoumean.model.externalmodel.AsYouMeanResult;import com.agile.asyoumean.model.externalmodel.DictionaryItem;import com.agile.asyoumean.model.externalmodel.DictionaryWordItem;import com.agile.asyoumean.model.externalmodel.MatchRatioItem;import com.agile.asyoumean.model.externalmodel.StatisticsItem;import com.agile.asyoumean.util.Constants;public class CoreDAO extends DAO {	private static CoreDAO coreDAO = new CoreDAO();	private Connection con = null;	private PreparedStatement pstmt = null;	private ResultSet rs = null;	public static CoreDAO getInstance() {		return coreDAO;	}	public List<DictionaryItem> getWordList() {		List<DictionaryItem> wordList = new ArrayList<DictionaryItem>();		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement("select WORD, EXACT_MATCH from uccs_tester.dummy_word_list where rownum < 300");			rs = pstmt.executeQuery();			while (rs.next()) {				DictionaryItem dic = new DictionaryItem();				dic.setWord(rs.getString("WORD"));				dic.setExactMatch(rs.getString("EXACT_MATCH") == null ? "" : rs						.getString("EXACT_MATCH"));				wordList.add(dic);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return wordList;	}	public List<MatchRatioItem> getMatchRatio() {		List<MatchRatioItem> ratioList = new ArrayList<MatchRatioItem>();		MatchRatioItem ratio;		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"SELECT A.NUM SEGM, B.CNT " +					"FROM (" +					"  SELECT ROWNUM NUM FROM DUAL CONNECT BY ROWNUM <= 100) A " +					"LEFT OUTER JOIN (" +					"  SELECT FLOOR (ACCURACY_RATIO) AS ACC_SEGMENT, FLOOR (ACCURACY_RATIO) AS SEGM, SUM (1) AS CNT " +					"  FROM UCCS_TESTER.DUMMY_SEARCH_LOG " +					"  WHERE MATCH_ALGORITHM = 'JW' " +					"  GROUP BY FLOOR (ACCURACY_RATIO)) B ON A.NUM = B.SEGM " +					"WHERE A.NUM >= 60 " +					"ORDER BY A.NUM"					);						rs = pstmt.executeQuery();			while (rs.next()) {				ratio = new MatchRatioItem();				ratio.setSegm(rs.getString("SEGM"));				ratio.setCnt(rs.getInt("CNT"));				ratioList.add(ratio);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return ratioList;	}	public Hashtable<String, String> getDummyWordList() {		Hashtable<String, String> wordList = new Hashtable<String, String>();		String keyword;		String strictMatch;		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement("select WORD, EXACT_MATCH from uccs_tester.dummy_word_list");			rs = pstmt.executeQuery();			while (rs.next()) {				keyword = rs.getString("WORD");				strictMatch = rs.getString("EXACT_MATCH") == null ? "" : rs.getString("EXACT_MATCH");				wordList.put(keyword.toLowerCase(), strictMatch);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return wordList;	}	public void logResultsToDB(String keyword, AsYouMeanResult asYouMeanResult){		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"insert into uccs_tester.dummy_search_log(GIVEN_WORD, MATCHED_WORD, ACCURACY_RATIO, MATCH_ALGORITHM) values('" 						+ keyword + "', '"						+ asYouMeanResult.getWordGuessed() + "', "						+ asYouMeanResult.getSimilarityRatio().replace("%", "") + ", "						+ "'JW')"					);			rs = pstmt.executeQuery();		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}	}	public List<StatisticsItem> getStatisticsFromLog(){				List<StatisticsItem> statisticsList = new ArrayList<StatisticsItem>();		StatisticsItem statistic;		try {			con = JNDISource.getInstance().getBSCSConnection();			//en çok kullanılmış ve en çok hata yapılmış kelimeleri belirleyip sırala			pstmt = con.prepareStatement(					"Select UPPER(BSD.MATCHED_WORD) MATCHED_WORD, BSD.CNT MATCHED_WORD_CNT, UPPER(ASD.GIVEN_WORD) GIVEN_WORD, Count(*) GIVEN_WORD_CNT " +					"From uccs_tester.DUMMY_SEARCH_LOG ASD " +					"Inner Join(" +					"  Select MATCHED_WORD, RANK() OVER(Order By MATCHED_WORD_COUNT Desc) NUM, MATCHED_WORD_COUNT CNT From (" +					"    Select Count(MATCHED_WORD) MATCHED_WORD_COUNT, MATCHED_WORD From uccs_tester.DUMMY_SEARCH_LOG " +					"    Group By MATCHED_WORD)) BSD On ASD.MATCHED_WORD = BSD.MATCHED_WORD " +					//"Where BSD.NUM < 20" +					"Having Count(*) > 1 " +					"Group By BSD.MATCHED_WORD, BSD.CNT, ASD.GIVEN_WORD " +					"Order By GIVEN_WORD_CNT Desc, BSD.CNT Desc");			rs = pstmt.executeQuery();			while (rs.next()) {				statistic = new StatisticsItem();				statistic.setMatchedWord(rs.getString("MATCHED_WORD"));				statistic.setMatchedWordCount(rs.getInt("MATCHED_WORD_CNT"));				statistic.setGivenWord(rs.getString("GIVEN_WORD"));				statistic.setGivenWordCount(rs.getInt("GIVEN_WORD_CNT"));				statisticsList.add(statistic);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return statisticsList;	}	public void insertWord(){		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"INSERT INTO uccs_tester.dummy_word_list(word) VALUES (lower('" +							Constants.DUMMY.toLowerCase() +							"'), lower('" +							Constants.DUMMY.toLowerCase() +							"'))"					);			rs = pstmt.executeQuery();		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}	}	public void deleteWord(String word){		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"DELETE FROM UCCS_TESTER.DUMMY_WORD_LIST WHERE lower(word) = '" +							word.toLowerCase() +							"'"					);			rs = pstmt.executeQuery();		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}	}	public DictionaryWordItem searchWord(String word){				DictionaryWordItem dictionaryWordItem = new DictionaryWordItem();		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"SELECT WORD, EXACT_MATCH FROM UCCS_TESTER.DUMMY_WORD_LIST " +					"WHERE lower(word) = '" +						word.toLowerCase() + "'"					);			rs = pstmt.executeQuery();						rs.next();			dictionaryWordItem.setWord(rs.getString("WORD").toLowerCase());			dictionaryWordItem.setExactMatch(rs.getString("EXACT_MATCH").toLowerCase());		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return dictionaryWordItem;	}		//en çok hata yapılan kelimeler	public List<StatisticsItem> getStatisticsForCommon(String keyword){				List<StatisticsItem> statisticsList = new ArrayList<StatisticsItem>();		StatisticsItem statistic;		try {			con = JNDISource.getInstance().getBSCSConnection();			pstmt = con.prepareStatement(					"SELECT lower(given_word) GIVEN_WORD, COUNT(*) GIVEN_WORD_CNT " +					"FROM dummy_search_log " +					"WHERE LOWER(matched_word) = '" +						keyword.toLowerCase() +					"' AND given_word != matched_word " +					"GROUP BY given_word " +					"ORDER BY COUNT(*) DESC");			rs = pstmt.executeQuery();			while (rs.next()) {				statistic = new StatisticsItem();				statistic.setGivenWord(rs.getString("GIVEN_WORD"));				statistic.setGivenWordCount(rs.getInt("GIVEN_WORD_CNT"));				statisticsList.add(statistic);			}		} catch (Exception e) {			throw new RuntimeException(e);		} catch (Throwable e) {			throw new RuntimeException(e);		} finally {			freeConnection(con, pstmt, rs);		}		return statisticsList;	}}
>>>>>>> origin/master
