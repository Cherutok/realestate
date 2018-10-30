package realestate;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HousePanel extends JPanel 
{
    Query query;
    JPanel leftpanel = new JPanel();
    JPanel rightpanel = new JPanel();
    JPanel viewpanel = new JPanel();
    JPanel newpanel = new JPanel();
    JPanel panel = new JPanel();

    
    JLabel label = new JLabel("OUTPUT WILL BE SHOWN HERE");
    JButton viewButton = new JButton("View properties");
    JButton newButton = new JButton("List New Property");
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back");

    CardLayout cl = new CardLayout();


    public HousePanel(Query newQuery) {
        query = newQuery;
        NewHouseForm newhf = new NewHouseForm(query);
        ViewHouseForm viewhf = new ViewHouseForm(query);
        label.setBounds(320, 5, 640, 780);
        newpanel.setBounds(320, 5, 640, 820);
        viewpanel.setBounds(320, 5, 640, 820);
        rightpanel.setBounds(320, 5, 900, 820);
        leftpanel.setBounds(5, 5, 300, 450);
        viewButton.setBounds(35, 30, 200, 50);
        newButton.setBounds(35, 130, 200, 50);
        back.setBounds(35, 230, 200, 50);
        exit.setBounds(35, 330, 200, 50);

        rightpanel.setLayout(cl);
        cl.show(rightpanel, "0");

        panel.add(label);


        leftpanel.setLayout(null);
        viewpanel.setLayout(null);
        newpanel.setLayout(null);
        
        newButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                cl.show(rightpanel, "2");
            }
        });
        viewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                cl.show(rightpanel, "1");
            }
        });

        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                cl.show(rightpanel, "0");
            }
        });
        viewpanel.setBorder(new MatteBorder(2, 2, 2, 2,Color.LIGHT_GRAY));
        newpanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
        leftpanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
        rightpanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        leftpanel.add(newButton);
        leftpanel.add(viewButton);
        leftpanel.add(back);
        leftpanel.add(exit);

        viewpanel.add(viewhf);
        newpanel.add(newhf);
        
                     
        rightpanel.add(panel, "0");
        rightpanel.add(viewpanel, "1");
        rightpanel.add(newpanel, "2");
        add(leftpanel);
        add(rightpanel);
        setVisible(true);
        setLayout(null);
        setBounds(10,0,1600,880);
        
    }
}

