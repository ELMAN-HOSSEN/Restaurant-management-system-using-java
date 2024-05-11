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


public class Sellspage extends JFrame implements ActionListener{
	
	JPanel panel;
	JTable table;
	DefaultTableModel model;
	Font font = new Font("arial",Font.BOLD,25);
	Homepage hp;
	ProductList products;
	
	public ProductManager(Homepage hp, ProductList products){
		
		super("Product Management");
		this.hp = hp;
		this.products = products;
		initialization();
		
		createTable();
		
		this.setVisible(true);
		
		
		
	}
	public void createTable(){
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("PRICE");
		model.addColumn("UNITS");
		
		table = new JTable(model);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 600, 300);
		table.setRowHeight(30);
		table.setBackground(new Color(64,128,128));
		table.setSelectionBackground(new Color(0, 0,255));
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50,220,600,300);
		
		ArrayList<Product> allproducts = products.getAll();
		
		for(int i=0;i<allproducts.size();i++){
			Product product = allproducts.get(i);
			model.addRow(new Object[]{product.getID(),product.getname(),product.getprice(),product.getunits()});
		}
		
		panel.add(scrollPane);
	}
	
	
	
	
	
	
	
	
	
}