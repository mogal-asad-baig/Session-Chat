package chatapp.backend;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



public class TableDestroyer {

	String url="jdbc:mysql://localhost:3306/chatapp";
	/* chatapp is my schema name if you want this code to work
	 * then setup a MYSQL database locally on your machine or 
	 * if you don't want to set it up on your machine then you 
	 * can opt for Amazon RDS from AWS and replace the endpoint
	 * with localhost in the above url similarly chatapp can also be replaced
	 * with your schema name
	 * */
    String user="root";
    String pass="password";
	public boolean destory(String namestable,String messagetable)
	{
		
		System.out.println("hello");
	      String query1="";
	      String query2="";
	      String query3="";
	      
		    
	      try
	      {
	    	  Connection con=DriverManager.getConnection(url,user,pass);
			  Statement st=con.createStatement();
			  query1="drop table "+namestable;
			  query2="drop table "+messagetable;
			  query3="delete from codestore where secretkey='"+messagetable+"'";
			  
			  st.executeUpdate(query1);
			  st.executeUpdate(query2);
			  st.executeUpdate(query3);
			 
			
	      }
	      catch(Exception e)
	      {
	    	  e.printStackTrace();
	    	  return false;
	      }
		return true;
	
	}
	
	public void removename(String namestable,String name)
	{
		System.out.println("hello1");
		  try
		  {
			  Connection con=DriverManager.getConnection(url,user,pass);
			  Statement st=con.createStatement();
			  String query="delete from "+namestable+" where participants='"+name+"'";
			  st.executeUpdate(query);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	}
}
