package chatapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartWindow implements ActionListener{
	JFrame frame;
    JPanel panel;
    JLabel label;
    JFrame tempframe;
   public StartWindow()
  {
    screen();
  }
  void screen()
  {
    frame=new JFrame("SessionChat");
    frame.setLayout(new GridLayout(0,1));
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("d:/sessionchat/gui/OIP.jpg"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    panel=new JPanel();
    panel.setBackground(Color.white);
    panel.setBorder(BorderFactory.createEmptyBorder(150,150,150,150));
    panel.setLayout(new GridLayout(0,1,0,25));
    JButton start=new JButton("Start Session Chat");
    JButton join=new JButton(" Join Session Chat");
    start.setBackground(new Color(33,182,168));
    join.setBackground(new Color(33,182,168));
    start.setActionCommand("Start");
    join.setActionCommand("Join");

    start.addActionListener(this);
    join.addActionListener(this);

    label=new JLabel();
    label.setFont(new Font("Segoe UI Emoji",Font.PLAIN,20));
    int codepoint=Integer.parseInt("u+1F44B".substring(2),16);
    label.setText("Welcome to Session Chat "+new StringBuilder().appendCodePoint(codepoint).toString());

    panel.add(label);
    panel.add(start);
    panel.add(join);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
    
  }
  
  public void actionPerformed(ActionEvent event)
  {
      String actioncommand=event.getActionCommand();
      if(actioncommand.equals("Start"))
      {
       tempframe = new InitiateWindow().initiate();   
      }
      if(actioncommand.equals("Join"))
      {
         new JoinWindow().join(tempframe);
         frame.dispose();
      }
  }
}
