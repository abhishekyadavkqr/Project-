package com.portfolio.abhi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class AdminLoginServlet extends HttpServlet {

	private static final String ADMIN_LOGIN = "SELECT COUNT(*) FROM USER_TABLE WHERE MAIL = ? AND PASSWORD = ? ";
	private static final String RETRIEVE_MESSAGES = "SELECT FEEDBACK_ID, PERSON_NAME, PERSON_MAIL_ID, MESSAGE FROM SUGGESTION ORDER BY FEEDBACK_ID ASC";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
				try(PreparedStatement ps = getPooledConnection().prepareStatement(ADMIN_LOGIN)) {
							String username = req.getParameter("mail");
							String password = req.getParameter("password");
							ps.setString(1, username);
							ps.setString(2, password);
							System.out.println("username : "+username+"\npassword : "+password);
							
							ResultSet rs = ps.executeQuery();
							if(rs == null) {
								rs = ps.executeQuery();
								System.out.println("rs is null");
							}
							rs.next();
							if( rs.getInt(1)==1) {
								
								try(PreparedStatement ps1 = getPooledConnection().prepareStatement(RETRIEVE_MESSAGES);){
									ResultSet rs1 = ps1.executeQuery();
									boolean flag = false;
									pw.println("<head>\r\n"
											+ "    <title>message display</title>"
											+ "</head>"
											+ "<body>"
											+ " <div style=\"display: flex; height: 100%; width: 100%; justify-content: center; align-items:start; line-height: 10px; border: 2px solid black;\">"
											+ " <form action='' method='post'>"
											
											+ "  <table border=\"1\">\r\n"
											+ "   <tr>\r\n"
											+ "   <th>sr. no</th>\r\n"
											+ "   <th> Name</th>\r\n"
											+ "   <th> Mail</th>\r\n"
											+ "   <th> Message</th>\r\n"
											+ "   </tr>");
									while(rs1.next()) {
										flag = true;
										pw.println("<tr>\r\n"
												+ "  <td>"+ rs1.getString(1)+"</td>" 
												+ "  <td>"+ rs1.getString(2)+"</td>"
												+ "  <td>"+ rs1.getString(3)+"</td>"
												+ "  <td>"+ rs1.getString(4)+"</td>"
												+ "  <td><input type=\"submit\" value='"+rs1.getString(1)+"' name=\"delete\" > </td>"
												
												+ "  </tr>");
									}
									if(flag )
										pw.println("</form> </table></div><br><br> <a href=\"index.html\"><input type=\"button\" value=\"Back\"></a></body>");
									
									else 
										pw.println("<h2> No Messages...</h2><br><br> <a href=\"index.html\"><input type=\"button\" value=\"Back\"></a>");
									

								}
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
	
//	Database object provider
	private Connection getPooledConnection() throws NamingException, SQLException {
		Connection con = null;
		
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup(getServletConfig().getInitParameter("jndi"));
		con = ds.getConnection();
		return con;
	}
	@Override
     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
