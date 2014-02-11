package ru.dtln.servicedesk.mail;

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class ConnectRemedyDb {
	
	private Connection  con = null;
    //private final String url = "jdbc:microsoft:sqlserver://";
	private final String url = "jdbc:sqlserver://";
    private final String serverName= "10.4.3.58";
    private final String portNumber = "1433";
    private final String databaseName= "RemedyDB";
    private final String userName = "ARAdmin";
    private final String password = "AR#Admin#";
    private final String selectMethod = "cursor";
    private String iNumber;
    
    //Конструктор
    public ConnectRemedyDb()
    {
    	
    }
    //Конструктор
    public ConnectRemedyDb(String incidentNumber)
    {
    	this.iNumber = incidentNumber;
    }
    
    //Формирование url подключения
    private String getConnectionUrl(){
        return url+serverName+":"+portNumber+";databaseName="+databaseName+";selectMethod="+selectMethod+";";
    }
    
    //Подключение драйвера 
    private Connection getConnection(){
        try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
             con = java.sql.DriverManager.getConnection(getConnectionUrl(),userName,password);

        }catch(Exception e){
             e.printStackTrace();
             System.out.println("Error Trace in getConnection() : " + e.getMessage());
       }
        return con;
    }
    
    //Закрытие соединения
    private void closeConnection(Connection con){
        try{
             if(con!=null)
                  con.close();
             con=null;
        }catch(Exception e){
             e.printStackTrace();
        }
    }
      
    
    public List<String> getMessages()
    {
    	Connection con;
    	List<String> messages = new ArrayList<String>();
    	    	
    	if((con = this.getConnection())!=null)
    		{
    			try{
    				
    				Statement st = con.createStatement();
    				
    				String sQuery = 
    				"SELECT ToEmailAddress as qto, CCEmailAddress as qcc, Description as qsubject, Detailed_Description as qmessage, dateadd(s,Work_Log_Submit_Date+14400,'19700101') as qdate FROM HPD_WorkLog WHERE Incident_Number=\'" + iNumber + "\'" + "AND Work_Log_Type=16000";
    				
    				ResultSet resSet = st.executeQuery(sQuery);
    				
    				
    				while ( resSet.next() )
    				{	
    					messages.add(resSet.getString("qto"));
    					messages.add(resSet.getString("qcc"));
    					messages.add(resSet.getString("qsubject"));
    					messages.add(resSet.getString("qdate"));
    					messages.add(resSet.getString("qmessage"));
    				}
    		
    			}
    			catch(SQLException e)
    			{
    				System.out.println(e.getMessage());
    			}
    		}
    	
    	this.closeConnection(con);
    	return messages;
    	
    }

    
}
