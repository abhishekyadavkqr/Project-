package com.portfolio.abhi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/feedback")
public class Feedback extends HttpServlet {
	private static final String RETRIEVE_MESSAGES = "SELECT FEEDBACK_ID, PERSON_NAME, PERSON_MAIL_ID, MESSAGE FROM SUGGESTION ORDER BY FEEDBACK_ID ASC";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw = res.getWriter();
		BeanServlet bs = new BeanServlet();
		
		try(PreparedStatement ps1 = bs.getPooledConnection().prepareStatement(RETRIEVE_MESSAGES);){
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

}
