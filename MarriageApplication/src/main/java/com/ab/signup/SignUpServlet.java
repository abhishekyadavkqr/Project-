package com.ab.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class SignUpServlet extends HttpServlet {
	
	
	private static final String insertQuery = "INSERT INTO USER_RECORD(NAME,ADDRS,DISTRICT,STATE,EMAIL,MOBILENO,"
									+ "LANGUAGE,SEX,DATE_OF_BIRTH,OCCUPTION,INCOME) VALUES(?,?,?,?,?,?,?,?,?,?,?)"  ;
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("Text/html");
		PrintWriter pw = res.getWriter();
			try(PreparedStatement ps=getPooledConnection().prepareStatement(insertQuery);){
				String name = req.getParameter("name");
				String addrs = req.getParameter("addrs"); 
				String dist = req.getParameter("dist"); 
				String state = req.getParameter("state"); 
				String mail= req.getParameter("mail"); 
				String mobNo= req.getParameter("mobileno"); 
				int sex= Integer.valueOf(req.getParameter("sex")); 
				String bDay= req.getParameter("bDay"); 
				String occuption = req.getParameter("occuption");
				String income = req.getParameter("income");
				
				
				pw.println("<p> Name "+name+ " </p>");
				pw.println("<p> Address "+addrs+ " </p>");
				pw.println("<p> district "+dist+ " </p>");
				pw.println("<p> State "+state+ " </p>");
				pw.println("<p> Mail "+mail+ " </p>");
				pw.println("<p> mobNo "+mobNo+ " </p>");
				pw.println("<p> sex "+(sex ==1?" Male":"Female")+ " </p>");
				pw.println("<p> BirthDay "+bDay+ " </p>");
				pw.println("<p> occupttion "+occuption+ " </p>");
				pw.println("<p> income "+income+ " </p>");
				
				pw.close();
			} catch (SQLException | NamingException  e) {
				pw.println("<p> internal error "+e.getMessage()+ " </p>");
			}
	}
	
//	helper Method
	public Connection getPooledConnection() throws SQLException, NamingException{
		Connection con = null;		 
		
					InitialContext ic = new InitialContext();
					DataSource ds = (DataSource)  ic.lookup(getServletConfig().getInitParameter("jndi"));
				
						con = ds.getConnection();
				
		return con;
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
