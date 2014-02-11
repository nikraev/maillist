package ru.dtln.servicedesk.mail;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

/**
 * Servlet implementation class Correspondence
 */
@WebServlet("/correspondence/")
public class Correspondence extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Correspondence() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String incidentNumber;
		List<String> listMessage = null;
		ConnectRemedyDb data;
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		
		if((incidentNumber = request.getParameter("incident")) !=null)
			{
				data = new ConnectRemedyDb(incidentNumber);
				listMessage = data.getMessages();
				
				int N = listMessage.size();
				if( N == 0 ) 
					{
						
						out.println("Переписки данному инциденту не зарегистрировано");
						
					}
				for ( int i = 0; i < N; i++)
					{
						if(i%5 == 0) {out.println("<strong>From:</strong> " + listMessage.get(i) + "<br>" );}
						if(i%5 == 1) {out.println("<strong>To:</strong> " + listMessage.get(i) + "<br>" );}
						if(i%5 == 2) {out.println("<strong>Subject:</strong> " + listMessage.get(i) + "<br>" );}
						if(i%5 == 3) {out.println("<strong>Date :</strong> " + listMessage.get(i) + "<br>" );}
						if(i%5 == 4) {
									  out.println("<br>" + listMessage.get(i) + "<br>" );
									  out.println("-------------------------------------------------<br>");
									 }
						/*
						out.println("i: " + i + "<br>" );
						out.println("From: " + listMessage.get(i) + "<br>" );
						 
						out.println("i: " + i + "<br>" );
						
						out.println("To: " + listMessage.get(i++) + "<br>" );
						out.println("i: " + i + "<br>" );
						out.println("Subject: " + listMessage.get(i++) + "<br>" );
						out.println("i: " + i + "<br>" );
						out.println("Date: " + listMessage.get(i++) + "<br>" + "<br>" );
						out.println("i: " + i + "<br>" );
						out.println(listMessage.get(i) + "<br>" );
						out.println("i: " + i + "<br>" );
						*/
						
					}
				
				
			}
		else
			{
				out.println("Номер инцидента передан неверно");
			}
	
		out.println("</body>");
		out.println("</html>");
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
