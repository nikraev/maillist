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
@WebServlet("/correspondence")
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ConnectRemedyDb data = new ConnectRemedyDb("INC000000059485");
		System.out.println("3");
		List<HashMap<String,String>> message = data.getMessages();
		
		if(message!=null)
		{
		out.println("<html>");
		out.println("<body>");
		System.out.println("4");
		out.println(message.get(0).get("subject"));
		out.println("</body>");
		out.println("</html>");
		}
		else {out.println("Can't get message");}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
