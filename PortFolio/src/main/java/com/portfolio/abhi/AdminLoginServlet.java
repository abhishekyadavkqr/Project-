package com.portfolio.abhi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.portfolio.bean.BeanServlet;



public class AdminLoginServlet extends HttpServlet {

	private static final String ADMIN_LOGIN = "SELECT COUNT(*) FROM USER_TABLE WHERE MAIL = ? AND PASSWORD = ? ";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter(); 
			
				ServletContext sc = getServletContext();
				BeanServlet bs = new BeanServlet();
				
				try(PreparedStatement ps = bs.getPooledConnection(sc).prepareStatement(ADMIN_LOGIN)) {
							String username = req.getParameter("mail");
							String password = req.getParameter("password");
							ps.setString(1, username);
							ps.setString(2, password);
							System.out.println("username : "+username+"\npassword : " + password);
							
							ResultSet rs = ps.executeQuery();
							if(rs == null) {
								rs = ps.executeQuery();
								System.out.println("rs is null");
							}  
							rs.next();
							if( rs.getInt(1)==1) {
								pw.println("<h2> Entered username or password valid..</h2>");
								 RequestDispatcher rd =  req.getRequestDispatcher("/feedback.in");
								 rd.include(req, res);
									
							}
							else
								pw.println("<h2> Entered username or password invalid..</h2>");
				
								
							
				} catch (SQLException | NamingException e) {
					// TODO Auto-generated catch block
					pw.println("<h2> Server Error..</h2>");
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
	}
	

	@Override
     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
