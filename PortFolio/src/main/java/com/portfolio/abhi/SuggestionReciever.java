package com.portfolio.abhi;

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

public class SuggestionReciever extends HttpServlet {
	private static final String SUGGESTION_INSERT_QUERY= "INSERT INTO SUGGESTION VALUES (FEED_ID.NEXTVAL,?,?,?)";
@Override
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 				res.setContentType("text/html");
	 				PrintWriter pw = res.getWriter();
	 				pw.println("Data coming here!");
	 				String name =req.getParameter("name");
	 				String mail =req.getParameter("mail");
	 				String feedback =req.getParameter("feedback");
	 				System.out.println("Name : "+name+"\nmail : "+mail+"\nfeedback : "+feedback);
	 				
	 				
	 				try(PreparedStatement ps = getPooledConnection().prepareStatement(SUGGESTION_INSERT_QUERY);){
	 					if(ps!= null)
	 					{
	 						ps.setString(1, name);
	 						ps.setString(2, mail);
	 						ps.setString(3, feedback);
	 						int x = ps.executeUpdate();
	 						System.out.println("X1 : "+x);
	 						if(x==1) {
	 							pw.println("<head><title>msg recorder</title></head><body><div style=\"display: flex; height: 50%; width: 100%; justify-content: center; align-items: center; flex-direction:column;\"><h2> Your suggestion is recorded successfully...</h2><br><h4> &nbsp; &nbsp; &nbsp;  We will work on your suggestion. </h4><br><h2>Thank you</h2></div><a href=\"index.html\"><input type=\"button\" value=\"Back\"></a></body>");
	 						}
	 					else {
	 						System.out.println("x : "+x);
	 					}
	 					}
	 					
	 				} catch (SQLException | NamingException e) {
				
						pw.println("<h2 style='text-align:center> Server Error!<br> Please try again.</h2>");
					}
}

// Connection Provider
private Connection getPooledConnection() throws NamingException, SQLException {
	Connection con = null;
	InitialContext ic = new InitialContext();
	DataSource ds = (DataSource) ic.lookup(getServletConfig().getInitParameter("jndi"));
	con =       ds.getConnection();
	return con;
	
	
}
}
