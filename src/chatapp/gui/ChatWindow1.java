package chatapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import javax.swing.*;

import chatapp.backend.*;

public class ChatWindow1 implements ActionListener{
	String admin;
	static String namestable;
	static String messagetable;
    long nametime;
	long msgtime;
	String name;
	public JFrame frame3;
	public JPanel panel1;
	public JPanel panel2;
	public JPanel panel3;
	public JTextArea data;
	public JButton start;

	
	public ChatWindow1()
	{
		
	}
	@SuppressWarnings("static-access")
	public ChatWindow1(String admin,String namestable,String messagetable,long nametime,String name,long msgtime)
	{
		   this.admin=admin;
		   this.namestable=namestable;
		   this.messagetable=messagetable;
		   this.nametime=nametime;
		   this.name=name;
		   this.msgtime=msgtime;
	}
	public void chatd()
	{
		   frame3=new JFrame("Session Chat");
		   frame3.setSize(900,600);
		   frame3.setResizable(false);
		   frame3.setLayout(new GridLayout(1,3));
		   frame3.setLocationRelativeTo(null);
		   frame3.setIconImage(Toolkit.getDefaultToolkit().getImage("d:/sessionchat/gui/OIP.jpg"));
		   frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
		   panel1=new JPanel();
		   panel1.setSize(300,600);
		   Participants1 obj=new Participants1(namestable,nametime,panel1,name,admin);
		   Thread t1=new Thread(obj);
		   t1.start();
		   
		   panel2=new JPanel();
		   panel2.setSize(300,600);
		   panel2.setBackground(Color.WHITE);
		   Chat1 obj1=new Chat1(frame3,panel2,messagetable,msgtime);
		   
		   Thread t2=new Thread(obj1);
		   t2.start();
		   
		   panel3=new JPanel();
		   panel3.setSize(300,600);
		   panel3.setLayout(new BorderLayout());
		   data=new JTextArea("Type Your message here");
		   data.setLineWrap(true);
		   data.setWrapStyleWord(true);

		    data.addFocusListener( new FocusListener() {
		        public void focusGained(FocusEvent fe){
		          data.setText("");
		        }
		        
				public void focusLost(FocusEvent e) {
					
					
				}
		       
		    });
		    
		   data.setFont(new Font("Monospaced",Font.BOLD,15));
		   panel3.add(data,BorderLayout.CENTER);
		   start=new JButton();
		   start.setActionCommand("Start");
		   start.setBackground(new Color(33,182,168));
		   start.setForeground(Color.WHITE);
		   start.setText("Send");
		   start.addActionListener(this);
		   panel3.add(start,BorderLayout.SOUTH);
		  
		   
		   frame3.add(panel1);
		   frame3.add(panel2);
		   frame3.add(panel3);
		   
		   frame3.setVisible(true);
		   
		   frame3.addWindowListener(new WindowListener() {

			    @Override
			    public void windowOpened(WindowEvent e) {
			    }

			    @Override
			    public void windowClosing(WindowEvent e) {

			    	TableDestroyer obj=new TableDestroyer();
			    	if(name.equals(admin))
			    	{
			    		obj.destory(namestable, messagetable);
			    		
			    	}
			    	else
			    	{
			    		obj.removename(namestable, name);
			    	}
			    }
			    @Override
			    public void windowClosed(WindowEvent e) {
			    	
			      
			    }

			    @Override
			    public void windowIconified(WindowEvent e) {
			    }

			    @Override
			    public void windowDeiconified(WindowEvent e) {
			    }

			    @Override
			    public void windowActivated(WindowEvent e) {
			    }

			    @Override
			    public void windowDeactivated(WindowEvent e) {
			    }

			});
		   
		   
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("Start"))
		{
		    String message="";
		   
		    int length=data.getText().length();
		    if(length<=600)
		    	message=data.getText();
		    else
		    	message=data.getText().substring(0,601);
		    
		    messagestore(message);
		}

	}
	public void messagestore(String message)
	{
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
		try
		{
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=con.createStatement();
			Date obj=new Date();
			long messagetime=obj.getTime();
			String query="insert into "+messagetable+" values("+messagetime+",'"+ message+"','"+name+"')";
			st.executeUpdate(query);
			data.setText("Type your message here");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
