package GUI;
import Entity.*;
import EntityList.*;
import File.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class TransactionHistory extends JFrame implements ActionListener{
	
	JPanel panel;
	
	JTable table;
	DefaultTableModel model;
	
	Font font = new Font("arial",Font.BOLD,25);
	
	Homepage hp;
	TransactionList transaction;
	JButton backBtn;
	
	public TransactionHistory(Homepage hp, TransactionList transaction){
		super("SELLs Management");
		this.hp = hp;
		this.transaction = transaction;
		initialization();
		
		
		

		ImageIcon backBtnimg = new ImageIcon("./RES/home.png");
		backBtn = new JButton("",  backBtnimg);
		backBtn.setOpaque(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);
		backBtn.setBounds(580, 400, 150, 100);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		createTable();
		
		this.setVisible(true);
	}
	
	
	public void initialization(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800,650);
		this.setLayout(null);
		this.setLocation(300, 20);
		this.setIconImage(new ImageIcon("./RES/logo.png").getImage());
		
		JLabel background = new JLabel(new ImageIcon("./RES/tbg.png"));
		background.setBounds(0,0,800,650);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,800,650);
		
		panel.setOpaque(false);
		
		this.add(panel);
		this.add(background);
	}
	
	public void createTable(){
		model = new DefaultTableModel();
		
		model.addColumn("NAME");
		model.addColumn("PRICE");
		model.addColumn("NUMBER");
		model.addColumn("IDS");
		
		
		table = new JTable(model);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setOpaque(false);
		
		table.setBounds(0, 0, 700, 300);
		table.setRowHeight(30);
		//table.setBackground(new Color(64,128,128));
		table.setSelectionBackground(new Color(0, 0,255));
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50,50,700,300);
		
		ArrayList<Transaction> alltransaction = transaction.getAll();
		
		for(int i=0;i<alltransaction.size();i++){
			Transaction Transaction = alltransaction.get(i);
			model.addRow(new Object[]{Transaction.getname(),Transaction.getprice(),Transaction.getnumb(),Transaction.getids()});
		}
		
		panel.add(scrollPane);
	}
	
	
	
	public void  actionPerformed(ActionEvent e){
		if(backBtn == e.getSource()){
			hp.setVisible(true);
			this.dispose();
		}
		
	}
}