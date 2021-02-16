package com.erickisee.app.ui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.Category;
import com.erickisee.app.models.User;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Categories extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private User user ;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector <Category> categories = new Vector<>();
	private JScrollPane scroll_table;

	/**
	 * Create the panel.
	 */
	public Categories(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		table = new JTable();
	    scroll_table = new JScrollPane(table);  
	    scroll_table.setBounds(10, 122, 626, 382);
		add(scroll_table);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Categories.class.getResource("/com/erickisee/app/ui/images/categories_b.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 626, 105);
		add(label);
		populateTable();
	}
	
	private void populateTable() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Name");
		tableModel.addColumn("Description");
		
		Database db = new Database();
		categories = db.getCategories("");
		for(int i =0; i<categories.size();i++) {
			Category category = categories.get(i);
			String description = category.getDescription();
			String name = category.getName();;
			tableModel.addRow(new Object[]{name, description});
		}
		table.setModel(tableModel);	
		db.disconnect();
	}
}
