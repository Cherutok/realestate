package realestate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;



public class NewHouseForm extends JPanel
{
    Query query;
    JLabel connStatus = new JLabel("Connection:");
    JLabel queryStatus = new JLabel();
    JLabel IDlabel =  new JLabel("House ID:");
    JLabel NameLabel = new  JLabel("House Name:");
    JLabel locationlabel= new JLabel("Location:");
    JLabel typelabel= new JLabel("Type:");
    JLabel roomlabel = new JLabel("Room");
    JLabel pricelabel = new JLabel("Price");

    JTextField houseID = new JTextField();
    JTextField houseName = new JTextField();
    JTextField location = new JTextField();
    JTextField type = new JTextField();
    JTextField room = new JTextField();
    JTextField price = new JTextField(); 
    JButton submit = new JButton("List Property");

    public NewHouseForm(Query newQuery)
    {
        query = newQuery;
        
        houseID.setText(""+(query.getTableSize("houses")+1));
        try {
            if (!query.conn.isClosed()){
                connStatus.setText("DB Connection: OK");
                connStatus.setForeground(new Color(12, 140, 1));
            }
        }
        catch(SQLException e)
        {
            connStatus.setText("Connection: DB Offline");
            connStatus.setForeground(new Color(109, 1, 1));
            //print to log
        }
        JPanel inputForm = new JPanel();
        IDlabel.setBounds(  30,100,320,50);
        IDlabel.setFont(new Font("San-Serif",Font.PLAIN,24));
        houseID.setBounds(250,100,320,50);
        houseID.setFont(new Font("San-Serif",Font.PLAIN,24));

        NameLabel.setBounds(30,160,320,50);
        NameLabel.setFont(new Font("San-Serif",Font.PLAIN,24));
        houseName.setBounds(250,160,320,50);
        houseName.setFont(new Font("San-Serif",Font.PLAIN,24));

        locationlabel.setBounds( 30,220,320,50);
        locationlabel.setFont(new Font("San-Serif",Font.PLAIN,24));
        location.setBounds(250,220,320,50);
        location.setFont(new Font("San-Serif",Font.PLAIN,24));

        typelabel.setBounds( 30,280,320,50);
        typelabel.setFont(new Font("San-Serif",Font.PLAIN,24));
        type.setBounds(250,280,320,50);
        type.setFont(new Font("San-Serif",Font.PLAIN,24));

        roomlabel.setBounds( 30,340,320,50);
        roomlabel.setFont(new Font("San-Serif",Font.PLAIN,24));
        room.setBounds(250,340,320,50);
        room.setFont(new Font("San-Serif",Font.PLAIN,24));
        
        pricelabel.setBounds( 30,420,320,50);
        pricelabel.setFont(new Font("San-Serif",Font.PLAIN,24));
        price.setBounds(250,420,320,50);
        price.setFont(new Font("San-Serif",Font.PLAIN,24));

        submit.setBounds(250,500,320,60);
        submit.setFont(new Font("San-Serif",Font.PLAIN,20));

        connStatus.setBounds(310,20,300,50);
        connStatus.setFont(new Font("San-Serif",Font.PLAIN,18));

        queryStatus.setBounds(310,580,300,60);
        queryStatus.setFont(new Font("San-Serif",Font.PLAIN,18));

        add(IDlabel);add(houseID);
        add(NameLabel);add(houseName);
        add(locationlabel);add(location);
        add(typelabel);add(type);
        add(roomlabel);add(room);
        add(pricelabel); add(price);
        add(submit);
        add(connStatus);add(queryStatus);

        submit.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
                formSubmittion();
            }
        }
        );

        setSize(600,500);
        setLayout(null);
        add(inputForm);
        setSize(800,700);
        setVisible(true);
        
    }

    public void formSubmittion()
    {
        try{

            String newID = houseID.getText();
            String newName = houseName.getText();
            String newLocation = location.getText();
            String newType = type.getText();
            String newRooms = room.getText();
            String newPrice = price.getText();
            String[] values={newID,newName,newLocation,newType,newRooms, newPrice};
            
            if(query.newHouse(values)){
                queryStatus.setText("Insertion Successful");
                queryStatus.setForeground(new Color(12, 140, 1));
                
                houseID.setText(""+(query.getTableSize("houses")+1));
                houseName.setText("");
                location.setText("");
                type.setText("");
                room.setText("");
                price.setText("");

            }
            else
            {
                queryStatus.setText("Insertion Failed");
                queryStatus.setForeground(new Color(109, 1, 1));
            }
        }
        catch(NumberFormatException ex)
        {

        }
    }
}
