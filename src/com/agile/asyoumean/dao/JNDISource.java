package com.agile.asyoumean.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.agile.asyoumean.util.Constants;

public class JNDISource {

	private InitialContext initialContext;

	private DataSource bscsDataSource;

	private static JNDISource jndi = new JNDISource();

	public static JNDISource getInstance() {
		return jndi;
	}

	/*
	 * 
	 */
	public InitialContext getContext() throws NamingException {

		if (initialContext == null) {
			
			Hashtable<String, String> env = new Hashtable<String, String>();
			
			/*
			 * TODO parameters shoul be on config file
			 */
			
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"weblogic.jndi.WLInitialContextFactory");
			
			env.put(Context.PROVIDER_URL, Constants.PROVIDER_URL);
			
			env.put("weblogic.jndi.createIntermediateContexts", "true");
			
			initialContext = new InitialContext(env);
			
		}

		return initialContext;
		
	}

	public Connection getBSCSConnection() throws NamingException, SQLException {

		if (bscsDataSource == null) {
			bscsDataSource = (DataSource) getContext().lookup(Constants.BSCS_JNDI);
		}

		Connection con = bscsDataSource.getConnection();
		// con.setAutoCommit(false);

		return con;

	}

}
