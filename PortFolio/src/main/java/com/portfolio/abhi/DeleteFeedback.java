package com.portfolio.abhi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.portfolio.bean.BeanServlet;

public class DeleteFeedback extends HttpServlet {
	private static final String DELETEFEEDBACKQUERY  = "DELETE FROM SUGGESTION WHERE FEEDBACK_ID = ?";
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();

				String  delete = req.getParameter("del");
				System.out.println("delete is working and  going to delete the  suggest id  "+ delete);
				BeanServlet bs = new BeanServlet();
				

				try(PreparedStatement ps = bs.getPooledConnection(getServletContext()).prepareStatement(DELETEFEEDBACKQUERY) ){
				int del = Integer.parseInt(delete);
				ps.setInt(1, del);
				int dbRes = ps.executeUpdate();
				if(dbRes ==1) {
					RequestDispatcher rd =  req.getRequestDispatcher("/feedback.in");
					 rd.include(req, res);
				}
					
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	
}
