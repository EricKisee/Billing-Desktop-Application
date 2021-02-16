package com.erickisee.app.ui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.Customer;
import com.erickisee.app.models.Merchant;
import com.erickisee.app.models.Product;
import com.erickisee.app.models.Sale;
import com.erickisee.app.models.User;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class Sales extends JPanel {

	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private User user ;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector <Sale> sales = new Vector<>();
	private JScrollPane scroll_table;

	/**
	 * Create the panel.
	 */
	public Sales(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		table = new JTable();
		
	    scroll_table = new JScrollPane(table);  
	    scroll_table.setBounds(10, 127, 626, 382);
		add(scroll_table);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(106, 117, 202));
		panel.setBounds(456, 520, 180, 30);
		add(panel);
		
		JLabel btnSum = new JLabel("Sum");
		btnSum.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSum.setForeground(Color.WHITE);
		btnSum.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnSum.setBounds(0, 0, 180, 30);
		panel.add(btnSum);
		
		JLabel lblSum = new JLabel("0");
		lblSum.setBounds(270, 520, 180, 30);
		add(lblSum);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Sales.class.getResource("/com/erickisee/app/ui/images/sales_b.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 626, 105);
		add(label);
		btnSum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int [] selectedRows = table.getSelectedRows();
				double sum = 0;
				for(int i=0;i<selectedRows.length;i++) {
					sum+=sales.elementAt(selectedRows[i]).getAmount();
				}
				lblSum.setText("ksh "+sum);
			}
		});
		
		populateTable();
	}
	
	private void populateTable() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Date Time");
		tableModel.addColumn("Product");
		tableModel.addColumn("Merchant");
		tableModel.addColumn("Customer");
		tableModel.addColumn("User");
		tableModel.addColumn("Amount");
		
		Database db = new Database();
		sales = db.getSales();
		for(int i =0; i<sales.size();i++) {
			Sale sale = sales.get(i);
			Merchant merchant = db.getMerchant(sale.getMerchantId());
			Customer customer = db.getCustomer(sale.getCustomerId());
			User user = db.getUser(sale.getUserId());
			Product product = db.getProduct(sale.getProductId());
			String date = sale.getDatetime();
			String productName = db.getCategory(product.getCategoryId()).getName();
			String merchantName = merchant.getName();
			String customerName = customer.getName();
			String userName = user.getName();
			String amount = sale.getAmount()+"";
			tableModel.addRow(new Object[]{date, productName, merchantName, customerName, userName, amount});
		}
		table.setModel(tableModel);	
		db.disconnect();
	}
}
