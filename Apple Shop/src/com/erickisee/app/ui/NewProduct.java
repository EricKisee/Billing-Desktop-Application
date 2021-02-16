package com.erickisee.app.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.erickisee.app.Methods;
import com.erickisee.app.db.Database;
import com.erickisee.app.models.Category;
import com.erickisee.app.models.Product;
import com.erickisee.app.models.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class NewProduct extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private JTextField tfCategory;
	private JTextField tfImei;
	private JTextField tfCode;
	private JTextField tfPrice;
	private JList categoryList;
	private Vector <Category> categories = new Vector ();
	private Category selectedCategory;
	private User user;
	
	private void loadCategories () {
		Database db = new Database();
		categories = db.getCategories("");
		db.disconnect();
	}
	
	private Vector<String> listData (){
		Vector<String> data = new Vector<>();
		
		for(int i = 0 ; i<categories.size(); i++) {
			data.add(categories.elementAt(i).getName());
		}
		
		return data;
		
	}

	/**
	 * Create the panel.
	 */
	public NewProduct(User user) {
		this.user = user;
		loadCategories();

		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(117, 200, 100, 30);
		add(lblCategory);
		
		tfCategory = new JTextField();
		tfCategory.setEditable(false);
		tfCategory.setColumns(10);
		tfCategory.setBounds(266, 200, 200, 30);
		add(tfCategory);
		
		JLabel lblImei = new JLabel("IMEI Number");
		lblImei.setBounds(117, 241, 100, 30);
		add(lblImei);
		
		tfImei = new JTextField();
		tfImei.setColumns(10);
		tfImei.setBounds(266, 241, 200, 30);
		add(tfImei);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(117, 282, 100, 30);
		add(lblCode);
		
		tfCode = new JTextField();
		tfCode.setColumns(10);
		tfCode.setBounds(266, 282, 200, 30);
		add(tfCode);
		
		JPanel btnAdd = new JPanel();
		btnAdd.setLayout(null);
		btnAdd.setBackground(new Color(106, 117, 202));
		btnAdd.setBounds(211, 392, 180, 30);
		add(btnAdd);
		
		JLabel lblAddProduct = new JLabel("Add Product");
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setForeground(Color.WHITE);
		lblAddProduct.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAddProduct.setBounds(0, 0, 180, 30);
		btnAdd.add(lblAddProduct);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(117, 323, 100, 30);
		add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(266, 328, 200, 30);
		add(tfPrice);
		

		categoryList = new JList();
		categoryList.setVisibleRowCount(4);
		categoryList.setBackground(lightBlue);
		categoryList.setForeground(darkBlue);
		categoryList.setListData(listData());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(481, 200, 155, 160);
		add(scrollPane);
		scrollPane.setViewportView(categoryList);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(NewProduct.class.getResource("/com/erickisee/app/ui/images/products_b.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 626, 105);
		add(label);
		
		categoryList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int pos = categoryList.getSelectedIndex();
				selectedCategory = categories.elementAt(pos);
				tfCategory.setText(selectedCategory.getName());
				
			}
		});
		
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String code = tfCode.getText();
				String imei = tfImei.getText();
				String price = tfPrice.getText();
				String category = tfCategory.getText();
				
				if(code.isEmpty()||imei.isEmpty()||price.isEmpty()||category.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all fields.");
				}else {
					int userId = user.getId();;
					int categoryId = selectedCategory.getId();
					Product product = new Product();
					product.setCategoryId(categoryId);
					product.setCode(code);
					product.setDatetime(new Methods().dateTime());
					product.setImei(imei);
					product.setPrice(Integer.parseInt(price));
					product.setUserId(userId);
					
					Database database = new Database();
					if(database.saveProduct(product)) {
						JOptionPane.showMessageDialog(null, "Product added successfully.");
						tfCode.setText("");
						tfImei.setText("");
						tfPrice.setText("");
						tfCategory.setText("");
					}
					database.disconnect();
					
				}
			}
		});

	}
}
