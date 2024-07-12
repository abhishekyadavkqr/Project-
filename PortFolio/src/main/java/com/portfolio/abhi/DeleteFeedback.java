package com.portfolio.abhi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DeleteFeedback extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
			pw
	}
	
//	Database object provider
	private Connection getPooledConnection() throws NamingException, SQLException {
		Connection con = null;
		
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup(getServletConfig().getInitParameter("jndi"));
		con = ds.getConnection();
		return con;
	}
	
}
