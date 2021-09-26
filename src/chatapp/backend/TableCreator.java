package chatapp.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class TableCreator {


	 public String admin="";
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
     public long mainnametime;
     public long mainmsgtime;
     public boolean create_name_table(String s,String name)
     {
    	 long nametime=0;
    	 s=s+"names";
	     try
	     {
	    	 Connection  con = DriverManager.getConnection(url,user,pass);
	    	
	    	 Statement st=con.createStatement();
	    	 String sq="create table if not exists "+s+"(milli BIGINT(255),participants varchar(20))";
	         st.executeUpdate(sq);
	        
	         String query1="select count(*) from "+s;
	        
	         ResultSet res=st.executeQuery(query1);
	         res.next();
	         Date obj=new Date();
	        
	         if(res.getInt(1)==0)
	          {
	        	 admin=name;
	        	 nametime=obj.getTime();
	        	 mainnametime=nametime;
	        	 mainmsgtime=nametime;
	        	 String query3="insert into "+s+" values("+nametime+",'"+admin+"')";
	        	 st.executeUpdate(query3);
	        	 System.out.println(admin);
	          }
	         else
	         {
	         String query2="select count(*) from "+s+" where participants='"+name+"'";
	
	         Statement st2=con.createStatement();
	         ResultSet res1=st2.executeQuery(query2);
	         res1.next();
	        
	    
	         Statement st3=con.createStatement();
	         if(res1.getInt(1)==0)
	         {
	        	 nametime=obj.getTime(); 
	        	 mainmsgtime=nametime;
	        	 String query3="insert into "+s+" values("+nametime+",'"+name+"')";
	        	 st3.executeUpdate(query3);
	          }
	         else
	         {
	        	 return false;
	         }
	         }
	         con.close();
	         
	     }
	     catch(Exception e)
	     {
	    	e.printStackTrace();
	     }
	     return true;
     }
     
     public void create_message_table(String s,String name)
     {
    	 try
	     {
	    	    Connection con = DriverManager.getConnection(url,user,pass);
  
	    	   
	    		 Statement st=con.createStatement();
		    	 String sq="create table if not exists "+s+"(milli BIGINT(255),message Text(600),name varchar(20))";
		         st.executeUpdate(sq);
		         con.close();	
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
     }
     
      public boolean nameCheck(String name,String s)
      {
    	  
    	  try
    	  {
    		  Connection  con = DriverManager.getConnection(url,user,pass);
    		  String query2="select count(*) from "+s+" where participants='"+name+"'";
    			
 	          Statement st2=con.createStatement();
 	          ResultSet res1=st2.executeQuery(query2);
 	         res1.next();
 	         if(res1.getInt(1) == 1)
	          {
	           return false;
	          }
    		  
    	  }
    	  catch(Exception e)
    	  {
    		  e.printStackTrace();
    	  }
    	  return true;
    	  
      }
      
}
