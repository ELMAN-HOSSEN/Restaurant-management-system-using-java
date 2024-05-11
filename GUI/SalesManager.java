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

public class SalesManager extends JFrame implements ActionListener {
	public double total;
	String userName;
	String numb;
	String ids=" ";
	JPanel panel;
	JLabel idLabel, modelLabel, rentLabel, unitsLabel;
	JTextField idField, modelField, rentField, unitsField;
	JButton selectBtn, backBtn, confirmBtn, deleteBtn;
	JTable table;
	JTable table2;
	DefaultTableModel model;
	DefaultTableModel model2;

	Font font = new Font("arial", Font.BOLD, 25);

	Homepage hp;
	ProductList products, confirnproducts;

	public SalesManager(Homepage hp, ProductList products, String userName) {
		super("Sales Management");
		this.hp = hp;
		this.userName = userName;
		this.products = products;
		initialization();

		// =================//
		selectBtn = new JButton("Select");
		selectBtn.setBounds(270, 20, 150, 30);
		selectBtn.setFont(font);
		selectBtn.setBackground(new Color(0, 0, 0));
		selectBtn.setForeground(new Color(255, 255, 255));
		selectBtn.addActionListener(this);
		panel.add(selectBtn);

		deleteBtn = new JButton("Undo");
		deleteBtn.setBounds(470, 20, 150, 30);
		deleteBtn.setFont(font);
		deleteBtn.setBackground(new Color(0, 0, 0));
		deleteBtn.setForeground(new Color(255, 255, 255));
		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);

		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(20, 550, 170, 30);
		confirmBtn.setFont(font);
		confirmBtn.setBackground(new Color(255,255,255));
		confirmBtn.setForeground(new Color(0, 0, 0));
		confirmBtn.addActionListener(this);
		panel.add(confirmBtn);

		// ..............
		unitsLabel = new JLabel("UNIT");
		unitsLabel.setFont(font);
		unitsLabel.setForeground(new Color(0,0,0));
		;
		unitsLabel.setBounds(30, 20, 100, 30);
		panel.add(unitsLabel);

		unitsField = new JTextField();
		unitsField.setFont(font);
		unitsField.setBounds(130, 20, 100, 30);
		panel.add(unitsField);

		// .........

		backBtn = new JButton("Home");
		backBtn.setBounds(550, 550, 150, 30);
		backBtn.setFont(font);
		backBtn.setBackground(new Color(255,255,255));
		backBtn.addActionListener(this);
		panel.add(backBtn);

		createTable();
		createTable2();

		this.setVisible(true);
	}

	public void initialization() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 650);
		this.setLayout(null);
		this.setLocation(300, 20);
		this.setIconImage(new ImageIcon("./RES/logo.png").getImage());

		JLabel background = new JLabel(new ImageIcon("./RES/hpbg.png"));
		background.setBounds(0, 0, 800, 650);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 800, 650);

		panel.setOpaque(false);

		this.add(panel);
		this.add(background);
	}

	public void createTable() {
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("PRICE");
		model.addColumn("UNITS");

		table = new JTable(model);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 600, 250);
		table.setRowHeight(30);
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(0, 0, 255));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 60, 600, 250);

		ArrayList<Product> allproducts = products.getAll();

		for (int i = 0; i < allproducts.size(); i++) {
			Product product = allproducts.get(i);
			model.addRow(new Object[] { product.getID(), product.getname(), product.getprice(), product.getunits() });
		}

		panel.add(scrollPane);
	}

	// ............//
	public void createTable2() {
		model2 = new DefaultTableModel();
		model2.addColumn("ID");
		model2.addColumn("NAME");
		model2.addColumn("PRICE");
		model2.addColumn("UNITS");

		table2 = new JTable(model2);
		table2.setFont(font);
		table2.getTableHeader().setFont(font);
		table2.setBounds(0, 0, 600, 200);
		table2.setRowHeight(30);
		table2.setBackground(new Color(64, 128, 128));
		table2.setSelectionBackground(new Color(255, 0, 0));

		JScrollPane scrollPane = new JScrollPane(table2);
		scrollPane.setBounds(20, 340, 600, 200);
		ArrayList<Product> allproducts = products.getAll();

		panel.add(scrollPane);
	}
	// ..........//

	// get number #######
	public void getNumber() {

		numb = JOptionPane.showInputDialog(this, "Enter Customar Number");

		if (!numb.isEmpty()) {

			calculation();
			updateFile();
			transactionFile();
			ConfirmPage cm2 = new ConfirmPage(hp, total);
			this.setVisible(false);

		} else {

			JOptionPane.showMessageDialog(this, "Enter Number first", "Warning", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void calculation() {

		int s = table2.getRowCount();

		for (int i = 0; i < s; i++) {

			total = Double.parseDouble(model2.getValueAt(i, 2).toString())
					* Integer.parseInt(model2.getValueAt(i, 3).toString());

		}

	}

	public void minusUnits(int i) {
		int un = Integer.parseInt(unitsField.getText());
		int tun = Integer.parseInt(model.getValueAt(i, 3).toString());
		tun = tun - un;
		model.setValueAt(tun, i, 3);
	}

	public void updateFile() {
		int rows = model.getRowCount();
		String allLines = "";
		for (int i = 0; i < rows; i++) {
			String id = table.getModel().getValueAt(i, 0).toString();
			String name = table.getModel().getValueAt(i, 1).toString();
			String price = table.getModel().getValueAt(i, 2).toString();
			String units = table.getModel().getValueAt(i, 3).toString();

			String line = "";
			if (i < rows - 1) {
				line = id + ";" + name + ";" + price + ";" + units + "\n";
			} else {
				line = id + ";" + name + ";" + price + ";" + units;
			}
			allLines += line;
		}
		FileIO.writeInFile(allLines, "./File/products.txt", false);
	}

	public void transactionFile() {

		if (total > 0) {
			FileIO.registerTransaction("./File/transaction.txt", userName, total, numb,ids);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (selectBtn == e.getSource()) {
			if (!unitsField.getText().isEmpty()) {

				ArrayList<Product> allproducts = products.getAll();
				Product product = allproducts.get(table.getSelectedRow());
				minusUnits(table.getSelectedRow());
				model2.addRow(
						new Object[] { product.getID(), product.getname(), product.getprice(), unitsField.getText() });
				ids += product.getID() + ",";
			} else {
				JOptionPane.showMessageDialog(this, "Enter All Details", "Warning", JOptionPane.WARNING_MESSAGE);
			}

		} else if (deleteBtn == e.getSource()) {
			int rows[] = table2.getSelectedRows();
			if (rows != null) {
				Arrays.sort(rows);
				for (int i = rows.length - 1; i >= 0; i--) {

					model2.removeRow(rows[i]);
				}
				updateFile();
			}
		} else if (backBtn == e.getSource()) {
			hp.setVisible(true);
			this.dispose();
		} else if (confirmBtn == e.getSource()) {
			getNumber();

		}

	}
}
