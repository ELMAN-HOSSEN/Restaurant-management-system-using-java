package GUI;
import Entity.*;
import EntityList.*;
import File.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfirmPage extends JFrame implements ActionListener
{	
	JPanel panel;
	JLabel idLabel;
	JButton home;
Homepage hp;
	//BoxList boxList;
	Font font = new Font("cambria", Font.PLAIN, 25);
	
	public ConfirmPage(Homepage hp,double total){
		super("ConfirmPage");
		this.hp = hp;
		//this.boxList = boxList;
        this.setSize(400, 300);
        this.setLocation(300, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		home = new JButton("home");
        home.setBounds(240, 220, 120, 30);
        home.setFont(font);
		home.setBackground(new Color(255,0,0));
		home.setForeground(new Color(255,255,255));
        home.addActionListener(this);
        this.add(home);
		
		idLabel = new JLabel("Total ammount : "+total);
			idLabel.setFont(font);
			idLabel.setBounds(50,20,300,30);
			this.add(idLabel);
			
			
		
		
		
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		 if( home== e.getSource()){
			hp.setVisible(true);
			this.dispose();	
		}
	}
	
	
}