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
		String temp;
		List<String> listMessage = null;
		ConnectRemedyDb data;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		if((incidentNumber = request.getParameter("incident")) !=null)
			{
				data = new ConnectRemedyDb(incidentNumber);
				listMessage = data.getTest();
				
				while ()
				out.println(temp);
				
			}
		else
			{
				out.println("<html>");
				out.println("<body>");
				out.println("Переписки данному инциденту не зарегистрировано");
				out.println("</body>");
				out.println("</html>");
			}
	
		//List<HashMap<String,String>> message = data.getMessages();
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
