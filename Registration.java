package jdbcdemo;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Registration extends JFrame
{
    JLabel title,login,pass;
    TextField tf,ps;
    JButton b1,b2;
    String log,word;
    
    public Registration()
    {
        super("Online Reservation System");
        setLayout(null);
        
        title=new JLabel("ONLINE RESERVATION SYSTEM");
        Font f1=new Font("Serif",Font.BOLD,35);
        title.setFont(f1);
        title.setBounds(365, 100, 650,75);
        add(title);
        
        login=new JLabel("Enter Id");
        Font f=new Font("Serif",Font.BOLD,25);
        login.setFont(f);
        login.setBounds(400, 250, 150, 50);
        add(login);
        
        pass=new JLabel("Enter Password");
        pass.setFont(f);
        pass.setBounds(400, 350, 200,50);
        add(pass);
        
        tf=new TextField(25);
        tf.setFont(f);
        tf.setBounds(600,260, 200,35);
        add(tf);
        
        ps=new TextField(25);
        ps.setEchoChar('*');
        ps.setFont(f);
        ps.setBounds(600,360, 200, 35);
        add(ps);
        
        b1=new JButton("Sign Up");
        b1.setFont(f);
        b1.setBounds(550, 460, 150, 35);
        add(b1);
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try{
                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                } 
                }catch(Exception e){}
            }
        });
        
        b2=new JButton("Back");
        b2.setFont(f);
        b2.setBounds(20, 20, 150, 30);
        add(b2);
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                Booking b=new Booking();
                dispose();
            }
        });
        
        setVisible(true);
        setSize(2000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public boolean check() throws Exception
    {
        Class.forName("org.sqlite.JDBC");

        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//login.db");

        PreparedStatement stm1=con.prepareStatement("insert into login values(?,?)");
        
        log=tf.getText();
        word=ps.getText();
        
        stm1.setString(1, log);
        stm1.setString(2, word);
        
        stm1.executeUpdate();
        
        PreparedStatement stm2=con.prepareStatement("select * from login where id=? and pass=?");
        
        stm2.setString(1, log);
        stm2.setString(2, word);
        
        ResultSet res2=stm2.executeQuery();
        
        if(res2.next())
        {
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Create an Account to login");
            return false;
        }
        
    }
}
