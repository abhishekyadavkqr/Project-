package com.portfolio.abhi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.bean.BeanServlet;


//@WebServlet("/feedback")
public class Feedback extends HttpServlet {
	private static final String RETRIEVE_MESSAGES = "SELECT FEEDBACK_ID, PERSON_NAME, PERSON_MAIL_ID, MESSAGE FROM SUGGESTION ORDER BY FEEDBACK_ID ASC";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException,IOException {

							res.setContentType("text/html");
                PrintWriter pw = res.getWriter();
                ServletContext sc = getServletContext();
		BeanServlet bs = new BeanServlet();

		try(PreparedStatement ps1 = bs.getPooledConnection(sc).prepareStatement(RETRIEVE_MESSAGES);){
			ResultSet rs1 = ps1.executeQuery();
			boolean flag = false;
			pw.println("<head>\r\n"
					+ "    <title>message display</title>"
					+ "</head>"
					+ "<body>"
					+ "<a href='index.html'><input type='button' value='Back' style='color:red'></a>"
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
						+ "  <td>  <a href='delete.in?del="+rs1.getString(1)+"'  > delete</a> </td>"

						+ "  </tr>");
			}
			if(flag ) {
				pw.println("</form> </table></div><br><br> </body>");
			} else {
				pw.println("<h2> No Messages...</h2><br><br>");
			}

		} catch (SQLException | NamingException e) {

			pw.println("<h2> Server Error..</h2>");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			bs.closePooledConnection();
		}

	}

}
