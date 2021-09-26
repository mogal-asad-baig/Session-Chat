package chatapp.gui;

import chatapp.backend.*;
import javax.swing.*;
import java.awt.*;


public class InitiateWindow {

	JFrame frame1;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JPanel panel1;
    public JFrame initiate()
    {
       frame1=new JFrame("Session Chat");
       frame1.setLayout(new GridLayout(0,1));
       frame1.setLocationRelativeTo(null);
       frame1.setResizable(false);
       frame1.setIconImage(Toolkit.getDefaultToolkit().getImage("d:/sessionchat/gui/OIP.jpg"));
       
       
        panel1=new JPanel();
        panel1.setBackground(Color.white);
        panel1.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        panel1.setLayout(new GridLayout(0,1,0,10));

        label1=new JLabel();
        label1.setFont(new Font("Calibri",Font.BOLD,20));
        label1.setText("Session Code");
       

        label2=new JLabel();
        label2.setFont(new Font("Arial Black",Font.BOLD,30));
        String secretcode=new SessionCodeGenerator().code();
        
        
         SecretCodeStore obj=new SecretCodeStore();
        obj.storekey(secretcode);
        label2.setText(secretcode);

        label3=new JLabel();
        label3.setFont(new Font("Calibri",Font.BOLD,15));
        label3.setText("To become admin join first in the meeting");
        
        
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);

        frame1.add(panel1);
        frame1.pack();
        frame1.setVisible(true);
        return frame1;
    }
}
