package jdbcdemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Show extends JFrame{
    
    JLabel pnr,tno,tname,from,to,date,name,gen,mail,phn,cls;
    JLabel tnolb,tnmlb,frmlb,tolb,dtlb,nmlb,gnlb,mlb,pnlb,cllb;
    JTextField tf;
    JButton b,back;
    public Show()
    {
        super("Details");
        setLayout(null);
        setVisible(true);
        setSize(2000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pnr=new JLabel("Enter Your PNR number :");
        pnr.setBounds(350, 100, 350, 35);
        Font f=new Font("Serif",Font.BOLD,30);
        pnr.setFont(f);
        pnr.setForeground(Color.DARK_GRAY);
        add(pnr);
        
        tf=new JTextField(20);
        Font n=new Font("Serif",Font.PLAIN,25);
        tf.setFont(n);
        tf.setBounds(725, 100, 150, 35);
        add(tf);
        
        Font f1=new Font("Serif",Font.BOLD,25);
        Font b1=new Font("Aerial",Font.PLAIN,20);
        
        tno=new JLabel("");
        tno.setBounds(450, 200, 250, 35);
        tno.setFont(f1);
        add(tno);
        
        tnolb=new JLabel("");
        tnolb.setBounds(675, 200, 200, 50);
        tnolb.setFont(b1);
        add(tnolb);
        
        tname=new JLabel("");
        tname.setBounds(450, 250, 250, 30);
        tname.setFont(f1);
        add(tname);
        
        tnmlb=new JLabel("");
        tnmlb.setBounds(675, 250, 250, 30);
        tnmlb.setFont(b1);
        add(tnmlb);
        
        from=new JLabel("");
        from.setBounds(450, 300, 250, 30);
        from.setFont(f1);
        add(from);
        
        frmlb=new JLabel("");
        frmlb.setBounds(550, 300, 250, 30);
        frmlb.setFont(b1);
        add(frmlb);
        
        to=new JLabel("");
        to.setBounds(700, 300, 250, 30);
        to.setFont(f1);
        add(to);
        
        tolb=new JLabel("");
        tolb.setBounds(775, 300, 250, 30);
        tolb.setFont(b1);
        add(tolb);
        
        date=new JLabel("");
        date.setBounds(450, 350, 250, 30);
        date.setFont(f1);
        add(date);
        
        dtlb=new JLabel("");
        dtlb.setBounds(675, 350, 250, 30);
        dtlb.setFont(b1);
        add(dtlb);
        
        cls=new JLabel("");
        cls.setBounds(450, 400, 250, 30);
        cls.setFont(f1);
        add(cls);
        
        cllb=new JLabel("");
        cllb.setBounds(675, 400, 250, 30);
        cllb.setFont(b1);
        add(cllb);
        
        name=new JLabel("");
        name.setBounds(450, 450, 250, 30);
        name.setFont(f1);
        add(name);
        
        nmlb=new JLabel("");
        nmlb.setBounds(675, 450, 250, 30);
        nmlb.setFont(b1);
        add(nmlb);
        
        gen=new JLabel("");
        gen.setBounds(450, 500, 250, 30);
        gen.setFont(f1);
        add(gen);
        
        gnlb=new JLabel("");
        gnlb.setBounds(650, 500, 250, 30);
        gnlb.setFont(b1);
        add(gnlb);
        
        mail=new JLabel("");
        mail.setBounds(450, 550, 250, 30);
        mail.setFont(f1);
        add(mail);
        
        mlb=new JLabel("");
        mlb.setBounds(650, 550, 250, 30);
        mlb.setFont(b1);
        add(mlb);
        
        phn=new JLabel("");
        phn.setBounds(450, 600, 250, 30);
        phn.setFont(f1);
        add(phn);
        
        pnlb=new JLabel("");
        pnlb.setBounds(650, 600, 100, 30);
        pnlb.setFont(b1);
        add(pnlb);
        
        b=new JButton("Check");
        b.setBounds(925, 100, 150, 30);
        b.setFont(f1);
        add(b);
        
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    Class.forName("org.sqlite.JDBC");

                    Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
            
                    PreparedStatement stm1=con.prepareStatement("select * from ticket where pnr=?");
                    
                    String str=tf.getText();
                    stm1.setString(1, str);
                    
                    ResultSet res=stm1.executeQuery();
                    
                    if(res.next())
                    {
                        String s1=res.getString("tno");
                        tnolb.setText(s1);
                        tno.setText("Train Number");
                        
                        String s2=res.getString("tname");
                        tnmlb.setText(s2);
                        tname.setText("Train Name");
                        
                        String s4=res.getString("src");
                        frmlb.setText(s4);
                        from.setText("From  :");
                        
                        String s5=res.getString("des");
                        tolb.setText(s5);
                        to.setText("To  :");
                        
                        String s6=res.getString("date");
                        dtlb.setText(s6);
                        date.setText("Date Of Journey");
                        
                        String s3=res.getString("ct");
                        cllb.setText(s3);
                        cls.setText("Class Type");
                        
                        String s7=res.getString("nm");
                        nmlb.setText(s7);
                        name.setText("Passenger Name");
                        
                        String s8=res.getString("str");
                        gnlb.setText(s8);
                        gen.setText("Gender");
                        
                        String s9=res.getString("phn");
                        pnlb.setText(s9);
                        phn.setText("Phone Number");
                        
                        String s10=res.getString("em");
                        mlb.setText(s10);
                        mail.setText("Email");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "hi");
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        
        back=new JButton("Back");
        back.setBounds(20, 20, 150, 30);
        back.setFont(f1);
        add(back);
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try{
                Reserve m=new Reserve();
                dispose();
                }
                catch(Exception e){}
            }
        });
    }
}
