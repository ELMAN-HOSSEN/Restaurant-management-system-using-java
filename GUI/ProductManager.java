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

public class ProductManager extends JFrame implements ActionListener {

	JPanel panel;
	JLabel idLabel, modelLabel, rentLabel, unitsLabel;
	JTextField idField, modelField, rentField, unitsField;
	JButton addBtn, backBtn, updateBtn, deleteBtn;
	JTable table;
	DefaultTableModel model;

	Font font = new Font("arial", Font.BOLD, 25);

	Homepage hp;
	ProductList products;
	ArrayList<Product> allproducts;

	public ProductManager(Homepage hp, ProductList products) {
		super("FOODs Management");
		this.hp = hp;
		this.products = products;
		allproducts = products.getAll();
		initialization();

		// =================//
		idLabel = new JLabel("ImgID");
		idLabel.setFont(font);
		idLabel.setForeground(Color.WHITE);
		idLabel.setBounds(50, 20, 100, 30);
		panel.add(idLabel);

		idField = new JTextField();
		idField.setFont(font);
		idField.setBounds(130, 20, 200, 30);
		panel.add(idField);

		// ======================//
		// =================//
		modelLabel = new JLabel("Name");
		modelLabel.setFont(font);
		modelLabel.setForeground(Color.WHITE);
		modelLabel.setBounds(50, 70, 100, 30);
		panel.add(modelLabel);

		modelField = new JTextField();
		modelField.setFont(font);
		modelField.setBounds(130, 70, 200, 30);
		panel.add(modelField);

		// ======================//
		// =================//
		rentLabel = new JLabel("Price");
		rentLabel.setFont(font);
		rentLabel.setForeground(Color.WHITE);
		rentLabel.setBounds(50, 120, 100, 30);
		panel.add(rentLabel);

		rentField = new JTextField();
		rentField.setFont(font);
		rentField.setBounds(130, 120, 200, 30);
		panel.add(rentField);

		// ======================//
		// ======================//
		// =================//
		unitsLabel = new JLabel("UNITS");
		unitsLabel.setFont(font);
		unitsLabel.setForeground(Color.WHITE);
		;
		unitsLabel.setBounds(50, 170, 100, 30);
		panel.add(unitsLabel);

		unitsField = new JTextField();
		unitsField.setFont(font);
		unitsField.setBounds(130, 170, 200, 30);
		panel.add(unitsField);

		// ======================//
		ImageIcon addimg = new ImageIcon("./RES/add.png");
		addBtn = new JButton("", addimg);
		addBtn.setOpaque(false);
		addBtn.setContentAreaFilled(false);
		addBtn.setBorderPainted(false);
		addBtn.setBounds(100, 550, 100, 50);
		addBtn.setFont(font);
		addBtn.addActionListener(this);

		panel.add(addBtn);

		ImageIcon deleteimg = new ImageIcon("./RES/delete.png");
		deleteBtn = new JButton("", deleteimg);
		deleteBtn.setOpaque(false);
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setBorderPainted(false);
		deleteBtn.setBounds(220, 550, 100, 50);
		deleteBtn.setFont(font);

		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);

		ImageIcon updateimg = new ImageIcon("./RES/update.png");
		updateBtn = new JButton("", updateimg);
		updateBtn.setOpaque(false);
		updateBtn.setContentAreaFilled(false);
		updateBtn.setBorderPainted(false);
		updateBtn.setBounds(340, 550, 100, 50);
		updateBtn.setFont(font);

		updateBtn.addActionListener(this);
		panel.add(updateBtn);

		ImageIcon backBtnimg = new ImageIcon("./RES/home.png");
		backBtn = new JButton("",  backBtnimg);
		backBtn.setOpaque(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);
		backBtn.setBounds(610, 520, 150, 100);
		
		backBtn.addActionListener(this);
		panel.add(backBtn);

		createTable();

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
		model.addColumn("IMAGE");
		model.addColumn("NAME");
		model.addColumn("PRICE");
		model.addColumn("UNITS");

		table = new JTable(model) {
			public Class getColumnClass(int column) {
				return (column == 0) ? Icon.class : Object.class;
			}
		};
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 600, 300);
		table.setRowHeight(50);
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(0, 0, 255));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 220, 600, 300);

		for (int i = 0; i < allproducts.size(); i++) {
			Product product = allproducts.get(i);
			String n = "./RES/fp/" + product.getID() + ".png";
			ImageIcon icon = new ImageIcon(n);

			model.addRow(new Object[] { icon, product.getname(), product.getprice(), product.getunits() });
		}

		panel.add(scrollPane);
	}

	public void updateFile() {
		int rows = model.getRowCount();
		String allLines = "";
		for (int i = 0; i < rows; i++) {
			Product product = allproducts.get(i);
			String id = "" + product.getID();
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

	public void actionPerformed(ActionEvent e) {
		if (addBtn == e.getSource()) {
			if (!idField.getText().isEmpty() &&
					!modelField.getText().isEmpty() &&
					!rentField.getText().isEmpty()) {

				model.addRow(new Object[] { idField.getText(), modelField.getText(), rentField.getText(),
						unitsField.getText() });

				int id = Integer.parseInt(idField.getText());
				String productModel = modelField.getText();
				double price = Double.parseDouble(rentField.getText());

				int units = Integer.parseInt(rentField.getText());
				Product c = new Product(id, productModel, price, units);
				products.insert(c);
			} else {
				JOptionPane.showMessageDialog(this, "Enter All Details", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		} else if (deleteBtn == e.getSource()) {
			int rows[] = table.getSelectedRows();
			if (rows != null) {
				Arrays.sort(rows);

				for (int i = rows.length - 1; i >= 0; i--) {

					String value = table.getModel().getValueAt(rows[i],0).toString();
					String intValue = value.replaceAll("[^0-9]", "");
					products.removeByID(Integer.parseInt(intValue.toString()));

					model.removeRow(rows[i]);
				}
				updateFile();
			}
		} else if (updateBtn == e.getSource()) {
			updateFile();
		}

		else if (backBtn == e.getSource()) {
			hp.setVisible(true);
			this.dispose();
		}

	}
}