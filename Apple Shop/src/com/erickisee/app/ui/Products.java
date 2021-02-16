package com.erickisee.app.ui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.Category;
import com.erickisee.app.models.Product;
import com.erickisee.app.models.User;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Products extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private User user ;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector <Product> products = new Vector<>();
	private JScrollPane scroll_table;

	/**
	 * Create the panel.
	 */
	public Products(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		table = new JTable();
	    scroll_table = new JScrollPane(table);  
		scroll_table.setBounds(10, 127, 626, 382);
		add(scroll_table);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Products.class.getResource("/com/erickisee/app/ui/images/products_b.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 626, 105);
		add(label);
		populateTable();
	}
	
	private void populateTable() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Date");
		tableModel.addColumn("Name");
		tableModel.addColumn("IMEI");
		tableModel.addColumn("Code");
		tableModel.addColumn("Price");
		
		Database db = new Database();
		products = db.getProducts("");
		for(int i =0; i<products.size();i++) {
			Product product = products.get(i);
			Category category = db.getCategory(product.getCategoryId());
			String date = product.getDatetime();
			String imei = product.getImei();
			String code = product.getCode();
			String name = category.getName();
			String price = product.getPrice()+"";
			tableModel.addRow(new Object[]{date,name,imei, code,price});
		}
		table.setModel(tableModel);	
		db.disconnect();
	}
}
