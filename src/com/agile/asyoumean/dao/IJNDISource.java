package com.agile.asyoumean.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public abstract class IJNDISource {

	public abstract Connection getVASConnection() throws NamingException, SQLException;

	public abstract void setJndi(IJNDISource jndi);
}
