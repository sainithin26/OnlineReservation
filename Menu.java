package jdbcdemo;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Menu extends JFrame
{
    JLabel title;
    JButton book,show,cancel,exit;
    public Menu()
    {
        super("Booking...");
        setLayout(null);
        setVisible(true);
        setSize(2000,2000);
        
        book=new JButton("Book Ticket");
        Font f=new Font("Serif",Font.BOLD,25);
        book.setBounds(400, 200, 250, 75);
        book.setFont(f);
        add(book);
        book.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try {
                    Reserve r=new Reserve();
                    dispose();
                } catch (Exception ex) {
                    
                }
            }
        });
        
        show=new JButton("Cancel Ticket");
        show.setBounds(800, 200, 250, 75);
        show.setFont(f);
        add(show);
        show.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                Cancel s=new Cancel();
                dispose();
            }
        });
    }
}
