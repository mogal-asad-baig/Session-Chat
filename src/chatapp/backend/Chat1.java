package chatapp.backend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class Chat1 implements Runnable{
	
	public JPanel panel1;
	String messagetable;
	long msgtime;
	public ArrayList<Object> lin=new ArrayList<Object>();
 	public JList<Object> list;
 	public JScrollPane pane1;
 	static public boolean flag=true;
 	JScrollBar  bar;
 	public JFrame frame3;
 	
	public Chat1(JFrame frame3,JPanel panel1,String messagetable,long msgtime)
	{
		this.frame3=frame3;
		this.panel1=panel1;
		this.messagetable=messagetable;
		this.msgtime=msgtime;
	}

	@Override
	public void run() {
		while(true)
		{
		 getMessages();
		
		 try
		 {
		   Thread.sleep(600);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		}
		
	}

	@SuppressWarnings("unchecked")
	public void getMessages() {
		
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
	      String query="";
	
		    
	      try
	      {
	    	  Connection con=DriverManager.getConnection(url,user,pass);
			  Statement st=con.createStatement();
			  query="select * from "+messagetable+" where milli>="+msgtime;
			  ResultSet res=st.executeQuery(query);
			  String s="";
			  
			
				while(res.next())
				{
					s=res.getString(3)+": "+res.getString(2);

					lin.add(s);
					
				}
				
				list=new JList<Object>(lin.toArray());   
				lin.clear();
				 
				list.setFixedCellWidth(300);
			 
				list.setCellRenderer(new MyCellRenderer());
				list.setBackground(new Color(215,255,255));
				list.setForeground(Color.BLACK);
			    list.setFont(new Font("Monospaced",Font.BOLD,15));
			    panel1.removeAll();
			    UIManager.put("ScrollBar.width",6);
			    pane1=new JScrollPane(list);
			  
			    Dimension d=new Dimension(300,600);
			    pane1.setPreferredSize(d);
			    panel1.add(pane1);
			    panel1.revalidate();
			    panel1.repaint();
			   
//					bar=pane1.getVerticalScrollBar();
//				    bar.setValue(bar.getMaximum());
				
			    
	      }
	      catch(Exception e)
	      {
	    	  frame3.dispose();
	    	  JFrame close=new JFrame("Session Chat");
	    	  close.setLocationRelativeTo(null);
	   	      close.setResizable(false);
	   	      close.setIconImage(Toolkit.getDefaultToolkit().getImage("d:/sessionchat/gui/OIP.jpg"));
	   	      close.setBackground(Color.white);
	   	      JOptionPane.showMessageDialog(close,"chat session has been ended by the host");
	   	      close.setVisible(true);
	   	      close.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  System.exit(0);
	    	 e.printStackTrace();  
	      }
		
	}
	


}

@SuppressWarnings("rawtypes")
class MyCellRenderer implements ListCellRenderer {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	 
	

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		String s=value.toString();
		int length=s.length();
		int rows=0;
		rows=(length/30)+2;
		JTextArea renderer = new JTextArea(rows,30);
		
		renderer.setFont(new Font("Monospaced",Font.BOLD,15));
		renderer.setBackground(Color.WHITE);
		renderer.setText(s);
		renderer.setLineWrap(true);
		renderer.setWrapStyleWord(true);
		return renderer;
	}
   
	}
