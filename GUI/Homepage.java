package GUI;

import Entity.*;
import EntityList.*;
import File.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Homepage extends JFrame implements ActionListener {
	JButton btnproductManager, logout, transactionHistory, employManagement, sellManager;
	// BoxList boxList;
	Font font = new Font("cambria", Font.PLAIN, 20);
	LoginPage login;
	String userName;
	ProductList products;
	TransactionList transaction;
	

	public Homepage(LoginPage login, String userName) {
		super("Homepage");
		this.login = login;
		this.userName = userName;
		// this.boxList = boxList;
		this.setSize(800, 650);
		this.setLocation(300, 20);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(0, 0, 0));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		ImageIcon productManagerim = new ImageIcon("./RES/productManager.png");
		btnproductManager = new JButton("", productManagerim);

		btnproductManager.setOpaque(false);
		btnproductManager.setContentAreaFilled(false);
		btnproductManager.setBorderPainted(false);
		btnproductManager.setBounds(140, 100, 200, 150);
		btnproductManager.setFont(font);
		btnproductManager.setBackground(new Color(237, 139, 7));
		btnproductManager.addActionListener(this);
		this.add(btnproductManager);

		ImageIcon sellManagerim = new ImageIcon("./RES/sellManager.png");
		sellManager = new JButton("", sellManagerim);

		sellManager.setOpaque(false);
		sellManager.setContentAreaFilled(false);
		sellManager.setBorderPainted(false);
		sellManager.setBounds(400, 100, 200, 150);
		sellManager.setFont(font);
		sellManager.setBackground(new Color(237, 139, 7));
		sellManager.addActionListener(this);
		this.add(sellManager);

		ImageIcon transactionHistoryim = new ImageIcon("./RES/transactionHistory.png");
		transactionHistory = new JButton("", transactionHistoryim);
		transactionHistory.setOpaque(false);
		transactionHistory.setContentAreaFilled(false);
		transactionHistory.setBorderPainted(false);
		transactionHistory.setBounds(270, 260, 200, 150);
		transactionHistory.setFont(font);
		transactionHistory.setBackground(new Color(237, 139, 7));
		transactionHistory.addActionListener(this);
		this.add(transactionHistory);

		 
		ImageIcon logoutim = new ImageIcon("./RES/logout.png");
		logout = new JButton("", logoutim);
		logout.setOpaque(false);
		logout.setContentAreaFilled(false);
		logout.setBorderPainted(false);
		logout.setBounds(550, 550, 150, 50);
		logout.setFont(font);
		logout.setBackground(new Color(255, 0, 0));
		logout.setForeground(new Color(255, 255, 255));
		logout.addActionListener(this);
		this.add(logout);

		JLabel background = new JLabel(new ImageIcon("./RES/hpbg.png"));
		background.setBounds(0, 0, 800, 650);
		this.add(background);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (btnproductManager == e.getSource()) {
			products = new ProductList();
			FileIO.loadProductsFromFile(products, "./File/products.txt");
			ProductManager cm = new ProductManager(this, products);
			this.setVisible(false);
		}
		if (logout == e.getSource()) {
			int option = JOptionPane.showConfirmDialog(this, "You will be Logged Out?");
			if (option == JOptionPane.YES_OPTION) {
				this.dispose();
				login.setVisible(true);
			}
		}
		if (sellManager == e.getSource()) {
			products = new ProductList();
			FileIO.loadProductsFromFile(products, "./File/products.txt");
			SalesManager cm = new SalesManager(this, products, userName);
			this.setVisible(false);

		}
		
		if (transactionHistory == e.getSource()) {
			transaction = new TransactionList();
			FileIO.loadTransactionFromFile(transaction, "./File/transaction.txt");
			TransactionHistory cm = new TransactionHistory(this, transaction);
			this.setVisible(false);
		}

	}
	// .............//

}