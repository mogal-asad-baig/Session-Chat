package chatapp.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import chatapp.backend.*;


public class JoinWindow implements ActionListener{
	static JFrame frame2;
	static JTextField field1;
	static JTextField field2;
	static JPanel panel2;
	JLabel label4;
	JLabel label5;
	JButton join;
	boolean check;
	public JFrame frame;

	
   public void join(JFrame frame)
   {
	   this.frame=frame;
	   frame2=new JFrame("Session Chat");
	   frame2.setLayout(new GridLayout(0,1));
	   frame2.setLocationRelativeTo(null);
	   frame2.setResizable(false);
	   frame2.setIconImage(Toolkit.getDefaultToolkit().getImage("d:/sessionchat/gui/OIP.jpg"));
	   frame2.setBackground(Color.white);
	   frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   panel2=new JPanel();
	   panel2.setBorder(BorderFactory.createEmptyBorder(200,200,200,200));
	   panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
	   panel2.setBackground(Color.white);
	   
	   Dimension d=new Dimension(10,8);
	   Dimension d1=new Dimension(10,9);
	   
	   Box.Filler fil=new Box.Filler(d, d, d1);
	   
	   label4=new JLabel();
       label4.setFont(new Font("Calibri",Font.BOLD,20));
       label4.setText("Session Code");
      
	   label5=new JLabel();
       label5.setFont(new Font("Calibri",Font.BOLD,20));
       label5.setText("Name");
      
	   
	   field1=new JTextField(16);
	   field1.setBackground(Color.white);
	   field1.setFont(new Font("SansSerif",Font.PLAIN,20));
	   field1.setForeground(new Color(33,182,168));
	   
	   
	   field2=new JTextField(16); 
	   field2.setFont(new Font("SansSerif",Font.PLAIN,20));
	   field2.setForeground(new Color(33,182,168));
	   
	   
	   join = new JButton("Join Chat");
	   join.setBackground(new Color(33,182,168));
	   join.setActionCommand("join");
	   join.addActionListener(this);
	   
	   
	 
	   panel2.add(label4);
	   panel2.add(field1);
	   panel2.add(label5);
	   panel2.add(field2);
	   panel2.add(fil);
	  
	   panel2.add(join);
	   
	   frame2.add(panel2);
	   frame2.pack();
	   frame2.setVisible(true);
   }
   
@Override
public void actionPerformed(ActionEvent e) {
	String actionCommand=e.getActionCommand();
	String code="";
	String name="";
	if(actionCommand=="join")
	  {
	    code=field1.getText();
	    name=field2.getText();
	    check=new CodeValidator().validate(code);
	    if(check==false)
	     {
	        JOptionPane.showMessageDialog(frame2,"Chat Session doesn't exist");
	        
	         field1.setText("");
	         field2.setText("");
	     }
	    else if(name.length()>20)
	    {
	    	 JOptionPane.showMessageDialog(frame2,"Name can't be greater than 20 letters");
	    }
	    else if(name.length()<2)
	    {
	    	JOptionPane.showMessageDialog(frame2,"Name can't be less than 2 letters");
	    }
	    else
	    {
	    	TableCreator obj=new TableCreator();
	    	
	        boolean flag=obj.create_name_table(code, name);
	        obj.create_message_table(code,name);
	        if(!flag)
		    {
		    	JOptionPane.showMessageDialog(frame2,"you are already present in the meeting");
		    }
	        else
	        {
	        ChatWindow1 obj1=new ChatWindow1(obj.admin,code+"names",code,obj.mainnametime,name,obj.mainmsgtime);
	        obj1.chatd();
	        frame2.dispose();
	        if(frame!=null)
	        frame.dispose();
	        }
	    }
      }
	}
	
	
}

