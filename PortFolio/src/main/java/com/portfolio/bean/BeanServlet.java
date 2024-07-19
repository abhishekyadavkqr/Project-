package com.portfolio.bean;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class BeanServlet extends HttpServlet {
	private Connection con = null;

//	Database object provider
	private   void pooledConnectionGenerator(ServletContext sc) throws NamingException, SQLException {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("getPooledConnection  is running ....");
			System.out.println("ServletContext :: "+sc);
		
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup(sc.getInitParameter("jndi"));
		con = ds.getConnection();
	
	}
	
	public Connection getPooledConnection(ServletContext sc) throws NamingException, SQLException  {
		pooledConnectionGenerator(sc);
		return con;
	}
	
	public void closePooledConnection() {
		try {
			con.close();
			System.out.println("Database Connection is closed...");
		} catch (Exception e) {
			con = null;
		}
	}
}