package com.agile.asyoumean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.agile.asyoumean.model.externalmodel.User;

public class UserDAO extends DAO {    
	
private static UserDAO userDAO = new UserDAO();
	
	public static UserDAO getInstance(){
		return userDAO;
	}		
	
	
     public static boolean login(String user, int password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	con = JNDISource.getInstance().getBSCSConnection();
            ps = con.prepareStatement("select * from uccs_tester.dummy_user ");
         
  
             rs = ps.executeQuery();
             
             
             while(rs.next()) {
            	 
					 if( rs.getString("USER_NAME").equalsIgnoreCase(user) && rs.getInt("PASS")==password ){
						 
						return true; 
						
					 }
            }
             return false;
             } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
        	
        	freeConnection(con, ps, rs);
        }
    }
     
     
	public static List<User> userList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			con = JNDISource.getInstance().getBSCSConnection();
			ps = con.prepareStatement("select * from uccs_tester.dummy_user ");

			rs = ps.executeQuery();

			while (rs.next()) {

				User usr = new User();
				usr.setUserName(rs.getString("USER_NAME"));
				usr.setPassword(rs.getInt("PASS"));
				userList.add(usr);

			}

			return userList;

		} catch (Exception ex) {

		}

		finally {

			freeConnection(con, ps, rs);
		}
		return userList;
	}
}