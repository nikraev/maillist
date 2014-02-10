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
    
    
    public List<HashMap<String,String>> getMessages()
    {
    		
    	List<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();
    	HashMap<String,String> mForm = new HashMap<String, String>();
    	
 //   	Connection con = this.getConnection();
    	
    	if(con!=null)
    		{
    			try{
    				Connection con = this.getConnection();
    				Statement st = con.createStatement();
    				String sQuery = "SELECT Description as qsubject,Detailed_Description as qmessageBody FROM HPD_WorkLog WHERE Incident_Number=\'" + iNumber + "\'";
    				
    				ResultSet resSet = st.executeQuery(sQuery);
    				
    				for( int i = 0; resSet.next() ;  )
    				{
    					//if(i%4 == 0) { i++; }
    					//mForm.put("from", resSet.getString("From"));
    					//mForm.put("to", resSet.getString("From"));
    					if(i%2 == 0) { i++; }
    					
    					mForm.put("subject", resSet.getString("qsubject"));
    					mForm.put("messageBody", resSet.getString("qmessageBody"));
    					
    					System.out.println("1");
    					System.out.println(mForm.get("subject"));
    					
    					mList.add(i, mForm);
    					//System.out.println(mList.get(0).get("subject"));
    				}
    			
    			this.closeConnection(con);	
    			return mList;
    			}
    			catch(SQLException e)
    			{
    				 e.printStackTrace();
    			}
    		}
		return mList;
    }
    
//test
    public List<String> getTest()
    {
    	Connection con;
    	List<String> messages = new ArrayList<String>();
    	
    	if((con = this.getConnection())!=null)
    		{
    			try{
    				Statement st = con.createStatement();
    				String sQuery = "SELECT Description as qsubject,Detailed_Description as qmessage FROM HPD_WorkLog WHERE Incident_Number=\'" + iNumber + "\'";    				
    				ResultSet resSet = st.executeQuery(sQuery);
    				
    				
    				while ( resSet.next() )
    				{
    					messages.add(resSet.getString("qsubject"));
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
//test
    
}
