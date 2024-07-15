	package com.portfolio.bean;

	import java.sql.Connection;
	import java.sql.SQLException;

	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.servlet.ServletContext;
	import javax.servlet.http.HttpServlet;
	import javax.sql.DataSource;

	public class BeanServlet extends HttpServlet {

//		Database object provider
		public  Connection getPooledConnection(ServletContext sc) throws NamingException, SQLException {
			Connection con = null;
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("getPooledConnection  is running ....");
			
			
			
			System.out.println("ServletContext :: "+sc);
			
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup(sc.getInitParameter("jndi"));
			con = ds.getConnection();
			return con;
		}
		
	}