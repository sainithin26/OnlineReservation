package jdbcdemo;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Cancel extends JFrame
{
    JLabel pnr;
    JTextField tf;
    JButton cancel,exit,back;
    public Cancel()
    {
        super("Details");
        setLayout(null);
        setVisible(true);
        setSize(2000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pnr=new JLabel("Enter Your PNR number :");
        pnr.setBounds(350, 250, 350, 35);
        Font f=new Font("Serif",Font.BOLD,30);
        pnr.setFont(f);
        pnr.setForeground(Color.DARK_GRAY);
        add(pnr);
        
        tf=new JTextField(20);
        Font n=new Font("Serif",Font.PLAIN,25);
        tf.setFont(n);
        tf.setBounds(750, 250, 150, 35);
        add(tf);
        
        cancel=new JButton("Cancel");
        cancel.setFont(f);
        cancel.setBounds(400, 350, 150, 35);
        add(cancel);
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent ae)
            {
                try
                {
                    Class.forName("org.sqlite.JDBC");

                    Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
            
                    PreparedStatement stm1=con.prepareStatement("delete from ticket where pnr=?");
                    
                    String str=tf.getText();
                    stm1.setString(1, str);
                    
                    int i=stm1.executeUpdate();
                    if(i==1)
                    {
                        JOptionPane.showMessageDialog(null, "Your ticket is cancelled successfully");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Entered PNR is Incorrect !");
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        
        exit=new JButton("Exit");
        exit.setFont(f);
        exit.setBounds(650, 350, 150, 35);
        add(exit);
        
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try {
                    dispose();
                } 
                catch (Exception ex) { }
            }
        });
        
        back=new JButton("Back");
        back.setBounds(20, 20, 150, 30);
        back.setFont(f);
        add(back);
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                Menu m=new Menu();
                dispose();
            }
        });
    }
}
