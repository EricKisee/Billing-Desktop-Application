package com.erickisee.app.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.erickisee.app.Methods;
import com.erickisee.app.db.Database;
import com.erickisee.app.models.Category;
import com.erickisee.app.models.Merchant;
import com.erickisee.app.models.Product;
import com.erickisee.app.models.TrustSale;
import com.erickisee.app.models.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TrustSales extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private JTable table;
	private DefaultTableModel tableModel;
	Vector <TrustSale> trustSales = new Vector<>();
	private User user ;
	private JScrollPane scroll_table;
	private JLabel label;

	/**
	 * Create the panel.
	 */
	public TrustSales(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		table = new JTable();
	    scroll_table = new JScrollPane(table);  
	    scroll_table.setBounds(10, 127, 626, 382);
		add(scroll_table);
		populateTable();
		
		JPanel btnEdit = new JPanel();
		
		btnEdit.setLayout(null);
		btnEdit.setBackground(new Color(106, 117, 202));
		btnEdit.setBounds(456, 520, 180, 30);
		add(btnEdit);
		
		JLabel lblReturn = new JLabel("Return");
		lblReturn.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturn.setForeground(Color.WHITE);
		lblReturn.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblReturn.setBounds(0, 0, 180, 30);
		btnEdit.add(lblReturn);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(TrustSales.class.getResource("/com/erickisee/app/ui/images/trustsales_b.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 626, 105);
		add(label);
		
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				if(i<0) {
					//no item selected
				}else {
					if(user.getRank().equalsIgnoreCase("Admin")) {
						TrustSale ts = trustSales.elementAt(i);
						if(ts.getStatus()==1) {
							JOptionPane.showMessageDialog(null, "Iten has already been sold");
						}else if(ts.getStatus()==-1) {
							JOptionPane.showMessageDialog(null, "Iten has already been returned");
						}else {
							ts.setStatus(-1);
							Database db = new Database();
							db.updateTrustSale(ts);
							JOptionPane.showMessageDialog(null, "Item has been returned");
							populateTable();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Action not allowed.");
					}
					
				}
				System.out.print(i);
			}
		});

	}
	
	private void populateTable() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Date");
		tableModel.addColumn("Merchant");
		tableModel.addColumn("Product");
		tableModel.addColumn("Price");
		tableModel.addColumn("Comment");
		tableModel.addColumn("Staff");
		tableModel.addColumn("Status");
		Database db = new Database();
		trustSales = db.getTrustSales();
		for(int i=0;i<trustSales.size();i++) {
			TrustSale trustSale = trustSales.elementAt(i);
			Merchant merchant = db.getMerchant(trustSale.getMerchantId());
			Product product = db.getProduct(trustSale.getProductId());
			Category category = db.getCategory(product.getCategoryId());
			User user = db.getUser(trustSale.getUserId());
			String date=trustSale.getDatetime();
			String merchantName=merchant.getName();
			String productName=category.getName();
			String price=product.getPrice()+""; 
			String comment=trustSale.getComment()+"";
			String staff=user.getName();
			String status=new Methods().trustSaleStatus(trustSale.getStatus());
			tableModel.addRow(new Object[]{date,merchantName,productName,price,comment,staff,status});
		}
		table.setModel(tableModel);	
		db.disconnect();
	}
}
