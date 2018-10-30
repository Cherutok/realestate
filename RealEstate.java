
package realestate;

import java.sql.*;
import javax.swing.*;

public class RealEstate
{

    public static void main(String[] args)
    {
        
        Connection conn = new Configuration().newConnection();
        
        Query query = new Query(conn);

        HousePanel hp = new HousePanel(query);
        
        JFrame frame = new JFrame("Real Estate");
        
        frame.setBounds(10,10, 1300, 1200);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.add(hp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximizedBounds(frame.getBounds());
    }
}
