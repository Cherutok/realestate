
package realestate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.xml.transform.Result;


public class ViewHouseForm extends JPanel {
    Query query;
    JLabel connStatus = new JLabel("Connection");

    String[] columns = {"House_ID","House_Name","Location","Type","Rooms", "Price"};

    JButton nextRecord = new JButton("Next");
    JButton prevRecord =  new JButton("Prev");

    // fetch data
    ResultSet rs;

    public ViewHouseForm(Query newQuery){
        query = newQuery;

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

        rs = query.fetchData("houses","*");

        String[][] data = new String[query.getTableSize("houses")][6];
        try {
            int row = 0;
            while (rs.next()) {
                    for(int col = 0; col<6;col++){
                        data[row][col] = rs.getString(col+1);
                    }
                    row++;
                }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


        JTable resultTable = new JTable(data,columns);
        resultTable.setRowHeight(35);
        resultTable.setBounds(20,60,850,data.length*resultTable.getRowHeight());
        resultTable.setFont(new Font("Sans-Serif",Font.PLAIN,17));
        add(resultTable);

        // STYLES

        connStatus.setBounds(375,20,300,50);
        connStatus.setFont(new Font("San-Serif",Font.PLAIN,18));


        // add elements to panel
        add(connStatus);
        setSize(1000,850);
        setLayout(null);
        setVisible(true);
    }


}
