package com.erickisee.app.ui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.Merchant;
import com.erickisee.app.models.User;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Merchants extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private User user ;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector <Merchant> merchants = new Vector<>();

	private JScrollPane scroll_table;
	/**
	 * Create the panel.
	 */
	public Merchants(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		table = new JTable();
	    scroll_table = new JScrollPane(table);  
	    scroll_table.setBounds(10, 123, 626, 382);
		add(scroll_table);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Merchants.class.getResource("/com/erickisee/app/ui/images/merchants_b.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 626, 105);
		add(label);
		populateTable();
	}
	
	private void populateTable() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Name");
		tableModel.addColumn("Email");
		tableModel.addColumn("Phone Number");
		
		Database db = new Database();
		merchants = db.getMerchants("");
		for(int i =0; i<merchants.size();i++) {
			Merchant merchant = merchants.get(i);
			String email = merchant.getEmail();
			String name = merchant.getName();;
			String phone = merchant.getPhoneNumber();;
			tableModel.addRow(new Object[]{name, email, phone});
		}
		table.setModel(tableModel);	
		db.disconnect();
	}

}
