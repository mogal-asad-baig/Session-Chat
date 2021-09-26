package chatapp.backend;

import java.sql.*;

public class SecretCodeStore {

	public boolean storekey(String s)
	  {
	      Connection con=null;
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
	      String sql="insert into codestore values('"+s+"')";
	      
	    try
	    {
	        
	        con = DriverManager.getConnection(url,user,pass);

	        Statement st = con.createStatement();

	        int m = st.executeUpdate(sql);
	        if (m == 1)
	        {
	            con.close();
	            return true;
	        }
	        else
	        {
	            con.close();
	            return false;
	        }
	      
	    }
	    catch(Exception ex)
	    {
	        System.err.println(ex);
	    }
	      return true;
	  }  
}
