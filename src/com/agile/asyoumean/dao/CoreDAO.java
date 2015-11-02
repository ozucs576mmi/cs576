package com.agile.asyoumean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;


public class CoreDAO  extends DAO {
	
	private static CoreDAO coreDAO = new CoreDAO();
	
	public static CoreDAO getInstance(){
		return coreDAO;
	}		
	


	public Hashtable<String, String> getDummyWordList() {		
		Hashtable<String, String> smsAppCodes = new Hashtable<String,String>();
//		String method = "CoreDAO.getDummyWordList";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = JNDISource.getInstance().getBSCSConnection();
			pstmt = con.prepareStatement("select * from uccs_tester.dummy_word_list ");
			rs = pstmt.executeQuery();
			while(rs.next()) {
					String keyword =  rs.getString("keyword");
					String strictMatch =  rs.getString("strictmatch");
					smsAppCodes.put(keyword,strictMatch);
			}					
		} catch(Exception e){
			throw new RuntimeException(e);
		} catch(Throwable e){
			throw new RuntimeException(e);
		} finally {
			freeConnection(con, pstmt, rs);			
		}
		return smsAppCodes;
	}
	

}
