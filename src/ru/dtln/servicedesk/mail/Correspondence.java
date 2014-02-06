package ru.dtln.servicedesk.mail;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

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
		PrintWriter out = response.getWriter();
				
		String url = "jdbc:jtds:sqlserver://10.4.3.106:1433/RemedyDB;integratedSecurity=false";
		try {
		    Class.forName("net.sourceforge.jtds.jdbc.Driver");
		    Connection conn = DriverManager.getConnection (url, "ARAdmin","AR#Admin#");
		    out.println("Connection successful");     
		} catch (Exception e) {
			 out.println("Cannot connect to database server");
		    e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
