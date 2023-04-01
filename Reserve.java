package jdbcdemo;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;
import javax.swing.*;
import java.util.*;
import javax.swing.text.NumberFormatter;

public class Reserve extends JFrame{
    
    JLabel trainno,trainname,classtype,doj,from,to,title,passen;
    JLabel name,email,phone,gender;
    JTextField tf1,tf2,tf3,tf4,tf5,nmtf,emtf,pntf,df,jf2;
    JRadioButton jr1,jr2;
    JComboBox jc1,jc2,jc3;
    JButton sub,get,back;
    String str,str1;
    ButtonGroup bg;
    
    public Reserve() throws Exception
    {
        super("Online Reservation System");
        setLayout(null);
        
        Class.forName("org.sqlite.JDBC");

        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
        Connection con1=DriverManager.getConnection("jdbc:sqlite:C://sqlite//login.db");
        
        title=new JLabel("Journey Details");
        Font b=new Font("Serif",Font.BOLD|Font.ITALIC,35);
        title.setFont(b);
        title.setBounds(500,25,350,40);
        add(title);
        
        trainno=new JLabel("Train Number");
        Font f=new Font("Serif",Font.BOLD,25);
        trainno.setFont(f);
        trainno.setBounds(400,100,200,30);
        add(trainno);
        
        get=new JButton("Get");
        get.setBounds(800,105,75,30);
        add(get);
        
        tf1=new JTextField(20);
        tf1.setBounds(600,105,150,30);
        Font f1=new Font("Serif",Font.PLAIN,20);
        tf1.setFont(f1);
        tf1.requestFocus();
        add(tf1);
        
        trainname=new JLabel("Train Name");
        trainname.setFont(f);
        trainname.setBounds(400,150,200,30);
        add(trainname);
        
        tf2=new JTextField(20);
        tf2.setBounds(600,165,150,30);
        tf2.setFont(f1);
        tf2.requestFocus();
        add(tf2);
        
        get.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                try{
                    PreparedStatement stm=con1.prepareStatement("select name from jour where no=?");
                    
                    str=tf1.getText();
                    
                    stm.setString(1, str);
                
                    ResultSet res=stm.executeQuery();
                    
                    str1=res.getString("name");
                    
                    if(res.next())
                    {
                        tf2.setText(str1);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Entered train number is incorrect");
                    }
                }
                catch(Exception e)
                {
                JOptionPane.showMessageDialog(null, "error");
                }
            }
        });
        
        classtype=new JLabel("Class Type");
        classtype.setFont(f);
        classtype.setBounds(400,200,150,30);
        add(classtype);
        
        String cls[]={"Sleeper","2S","3-Tier AC","2-Tier AC"};
        jc1=new JComboBox(cls);
        jc1.setBounds(600,205,150,30);
        jc1.setFont(f1);
        add(jc1);
        
        doj=new JLabel("Date (dd/MM/yyyy)");
        doj.setFont(f);
        doj.setBounds(350,250,250,30);
        add(doj);
        
        df=new JTextField(20);
        df.setBounds(600,255,150,30);
        df.setFont(f1);
        df.requestFocus();
        add(df);
        
        from=new JLabel("From");
        from.setFont(f);
        from.setBounds(375,325,100,30);
        add(from);
        
        String fr[]={"Kavali","Kalikiri","Bengulur","Chennai","Vizag","Hyderabad"};
        jc2=new JComboBox(fr);
        jc2.setBounds(475,325,150,30);
        jc2.setFont(f1);
        add(jc2);
        
        to=new JLabel("To");
        to.setFont(f);
        to.setBounds(675,325,100,30);
        add(to);
        
        String to[]={"Kavali","Kalikiri","Bengulur","Chennai","Vizag","Hyderabad"};
        jc3=new JComboBox(to);
        jc3.setBounds(750,325,150,30);
        jc3.setFont(f1);
        add(jc3);
        
        passen=new JLabel("Passenger Details");
        passen.setFont(b);
        passen.setBounds(500,400,350,45);
        add(passen);
        
        name =new JLabel("Name");
        name.setBounds(400, 475, 100, 30);
        name.setFont(f);
        add(name);
        
        nmtf=new JTextField(40);
        nmtf.setFont(f1);
        nmtf.setBounds(500,475,100,30);
        nmtf.requestFocus();
        add(nmtf);
        
        email =new JLabel("Email");
        email.setBounds(650, 470, 100, 40);
        email.setFont(f);
        add(email);
        
        emtf=new JTextField(100);
        emtf.setFont(f1);
        emtf.setBounds(750,470,150,30);
        emtf.requestFocus();
        add(emtf);
        
        phone=new JLabel("Phone No");
        phone.setFont(f);
        phone.setBounds(350,550,150,30);
        add(phone);
        
        jf2=new JTextField(20);
        jf2.setColumns(50);
        jf2.setBounds(500, 550, 100, 30);
        jf2.setFont(f1);
        jf2.requestFocus();
        add(jf2);
        
        gender=new JLabel("Gender");
        gender.setFont(f);
        gender.setBounds(650,550,150,30);
        add(gender);
        
        jr1=new JRadioButton("Male");
        jr1.setActionCommand("Male");
        jr1.setBounds(775, 550, 100, 30);
        jr1.setFont(f1);
        
        jr2=new JRadioButton("Female");
        jr2.setActionCommand("Female");
        jr2.setBounds(875, 550, 150, 30);
        jr2.setFont(f1);
        
        bg=new ButtonGroup();
        bg.add(jr1);
        bg.add(jr2);
        
        add(jr1);
        add(jr2);
        
        sub=new JButton("Submit");
        sub.setBounds(550, 625, 150, 30);
        sub.setFont(f);
        add(sub);
        
        sub.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        try{
            Random r=new Random();
            Integer pnr=r.nextInt(10000);
            String tno=tf1.getText();
            String tname=tf2.getText();
            String ct=jc1.getSelectedItem().toString();
            String src=jc2.getSelectedItem().toString();
            String des=jc3.getSelectedItem().toString();
            String nm=nmtf.getText();
            String em=emtf.getText();
            String phn=jf2.getText();
            String date=df.getText();
                    
            String str="";
            if(e.getActionCommand().equals("Submit"))
            {
                str=bg.getSelection().getActionCommand();
            }
            if(tno.isEmpty()||tname.isEmpty()||ct.isEmpty()||nm.isEmpty()||em.isEmpty()|phn.isEmpty()||str.isEmpty()||date.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "enter all details");
            }
            else
            {
                String t="insert into ticket values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement st=con.prepareStatement(t);
                
                st.setInt(1, pnr);
                st.setString(2, tno);
                st.setString(3, tname);
                st.setString(4, ct);
                st.setString(5, src);
                st.setString(6, des);
                st.setString(7, nm);
                st.setString(8, em);
                st.setString(9, phn);
                st.setString(10, str);
                st.setString(11, date);
                try{
                int i=st.executeUpdate();
                if(i==1)
                {
                int sel=JOptionPane.showConfirmDialog(null, "Ticket successfully booked \n\n PNR No : "+pnr+
                        "\n\nClick 'OK' to see Journey details","ti",JOptionPane.OK_CANCEL_OPTION);
                if(sel==JOptionPane.OK_OPTION)
                {
                    Show s=new Show();
                    dispose();
                }
                else
                {
                    dispose();  
                }
                tf1.setText("");
                tf2.setText("");
                emtf.setText("");
                nmtf.setText("");
                jf2.setText("");
                }
                }        
                catch(Exception w)
                {   
                   JOptionPane.showMessageDialog(null, w);
                }
            }
        }
        catch(Exception w)
        {
            JOptionPane.showMessageDialog(null, "enter all details");
        }
        }
        });
        
        back=new JButton("Back");
        back.setBounds(20,20, 150, 30);
        back.setFont(f);
        add(back);
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent w)
            {
                Menu m=new Menu();
                dispose();
            }
        });
        
        setVisible(true);
        setSize(2000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

