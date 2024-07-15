package com.portfolio.bean;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class BeanServlet extends HttpServlet {

//	Database object provider
	public  Connection getPooledConnection() throws NamingException, SQLException {
		Connection con = null;
		
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup(getServletConfig().getInitParameter("jndi"));
		con = ds.getConnection();
		return con;
	}
}
