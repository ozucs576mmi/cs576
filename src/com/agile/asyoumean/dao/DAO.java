package com.agile.asyoumean.dao;

import java.sql.*;


public class DAO {
	

    public void freeConnection(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con = null;
            }
        }
    }

    public void freeConnection(Statement stmt, ResultSet rs) {
    	
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }        
    }
    
    public void freeConnection(Connection con) {
    	
    	if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con = null;
            }
        }
    }
    
}

