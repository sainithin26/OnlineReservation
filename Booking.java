
package jdbcdemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Booking extends JFrame
{
    JLabel title,login,pass,slash;
    TextField tf,ps;
    JButton b1,b2;
    String log,word;
    
    public Booking()
    {
        super("Online Reservation System");
        setLayout(null);
        
        title=new JLabel("ONLINE RESERVATION SYSTEM");
        Font f1=new Font("Serif",Font.BOLD,35);
        title.setFont(f1);
        title.setBounds(365, 100, 650,75);
        add(title);
        
        login=new JLabel("Login Id");
        Font f=new Font("Serif",Font.BOLD,25);
        login.setFont(f);
        login.setBounds(400, 250, 150, 50);
        add(login);
        
        pass=new JLabel("Password");
        pass.setFont(f);
        pass.setBounds(400, 350, 200,50);
        pass.requestFocus();
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
        
        b1=new JButton("Sign In");
        b1.setFont(f);
        b1.setBounds(400, 460, 150, 35);
        add(b1);
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try{
                if(check())
                {
                    
                    Menu m=new Menu();
                    dispose();
                } 
                }catch(Exception e){}
            }
        });
        
        slash=new JLabel("(OR)");
        slash.setFont(f);
        slash.setBounds(570, 450,100, 50);
        add(slash);
        
        b2=new JButton("Sign Up");
        b2.setFont(f);
        b2.setBounds(650, 460, 150, 35);
        add(b2);
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                Registration r=new Registration();
                dispose();
            }
        });
        
        setVisible(true);
        setSize(2000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public boolean check() throws Exception
    {
        try{
        Class.forName("org.sqlite.JDBC");

        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//login.db");

        PreparedStatement stm=con.prepareStatement("select * from login where id=? and pass=?");
        
        log=tf.getText();
        word=ps.getText();
        
        stm.setString(1, log);
        stm.setString(2, word);
        
        ResultSet res=stm.executeQuery();
        
        if(res.next())
        {
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Enter a valid username & password");
            tf.setText("");
            ps.setText("");
            return false;
        }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Something went wrong!");
            return false;
        }
    }
}
