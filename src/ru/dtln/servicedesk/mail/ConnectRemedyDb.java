package ru.dtln.servicedesk.mail;

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ConnectRemedyDb {
	
	private Connection  con = null;
    //private final String url = "jdbc:microsoft:sqlserver://";
/*	
	private final String url = "jdbc:sqlserver://";
    private final String serverName= "10.4.3.58";
    private final String portNumber = "1433";
    private final String databaseName= "RemedyDB";
    private final String userName = "ARAdmin";
    private final String password = "AR#Admin#";
    private final String selectMethod = "cursor";
    private String iNumber;
*/
	private final String url = "jdbc:sqlserver://";
    private static String serverName;
    private static String portNumber;
    private static String databaseName;
    private static String userName;
    private static String password;
    private final String selectMethod = "cursor";
    private String iNumber;

    //Конструктор
    public ConnectRemedyDb()
    {
    	this.getProphets();
    }
    //Конструктор
    public ConnectRemedyDb(String incidentNumber)
    {
    	this.iNumber = incidentNumber;
    	this.getProphets();
    }
    
    private void getProphets()
    {
    	Properties prop = new Properties();
    	InputStream instream = null;
    	
    	try{
    		String filename = "config.properties";
    		instream = ConnectRemedyDb.class.getClassLoader().getResourceAsStream(filename);
    		    		 
    		// load a properties file
    		prop.load(instream);
     
    		serverName = prop.getProperty("server");
    		portNumber = prop.getProperty("port");
    		databaseName = prop.getProperty("database");
    		userName = prop.getProperty("dbuser");
    		password = prop.getProperty("dbpassword");
    		
    		
    	}catch(IOException e)
    	{
    		e.getStackTrace();
    	}finally{
        	if(instream!=null)
        	{
        		try {
        			instream.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
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
