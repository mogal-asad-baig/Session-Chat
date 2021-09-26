package chatapp.backend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class Participants1 implements Runnable{
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
     String namestable;
 	 long nametime;
 	 String admin="";
 	 String name="";
 	 public JPanel panel1;
 	 static int flag=0;
 	 public ArrayList<Object> lin=new ArrayList<Object>();
 	 public JList<Object> list;
 	public JScrollPane pane1;
 	public boolean flag1;
	 public Participants1(String namestable,long nametime,JPanel panel1,String name,String admin)
 	 {
 		 this.namestable=namestable;
 		 this.nametime=nametime;
 		 this.panel1=panel1;
 		 this.admin=admin;
 		 this.name=name;
 		
 	 }
	 
	 public void getParticipants()
		{

		 try
		 {
			Connection  con = DriverManager.getConnection(url,user,pass);
			Statement st=con.createStatement();
			String quer="select participants from "+namestable+" where milli>="+nametime;
			ResultSet res=st.executeQuery(quer);
			String temp="";
			while(res.next())
			{
				if(flag==0)
				{
					lin.add("Code: "+namestable.substring(0,namestable.length()-5));
					lin.add("PARTICIPANTS");
					flag=1;
				}
				temp=res.getString(1);
				lin.add(temp);
			}
			list=new JList<Object>(lin.toArray());   
			lin.clear();

			lin.add("Code: "+namestable.substring(0,namestable.length()-5));
			lin.add("PARTICIPANTS");
			list.setFixedCellWidth(300);
	        list.setFixedCellHeight(30);
	        list.setBackground(new Color(33,182,168));
	        list.setForeground(Color.WHITE);
	        list.setFont(new Font("Monospaced",Font.BOLD,15));
	        
	        panel1.removeAll();
	        pane1=new JScrollPane(list);
	        pane1.getVerticalScrollBar().setIgnoreRepaint(true);
	        Dimension d=new Dimension(300,600);
	        pane1.setPreferredSize(d);
	        panel1.add(pane1);
	        panel1.revalidate();
	        panel1.repaint();
	        
	        
	        
		 }
		 catch(Exception e)
		 {
			 
		 }
		}
	 public void run() {
			while(true)
			{
			 getParticipants();
			 try
			 {
			   Thread.sleep(600);
			 }
			 catch(Exception e)
			 {
				 
			 }
			}
			
		}
}
