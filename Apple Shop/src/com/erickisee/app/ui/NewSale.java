package com.erickisee.app.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.erickisee.app.Methods;
import com.erickisee.app.db.Database;
import com.erickisee.app.models.Category;
import com.erickisee.app.models.Customer;
import com.erickisee.app.models.Merchant;
import com.erickisee.app.models.Product;
import com.erickisee.app.models.Sale;
import com.erickisee.app.models.TrustSale;
import com.erickisee.app.models.User;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class NewSale extends JPanel {

	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private JTextField tfSearch;
	private JTextField tfName;
	private JTextField tfCategory;
	private JTextField tfImei;
	private JTextField tfCode;
	private JTextField tfMerchant;
	private JTextField tfCustomer;
	private JTextField tfPhoneNumber;
	private JTextField tfAmount;
	private JPanel btnProcess;
	private JLabel lblNewLabel_1;
	private JList listProducts;
	private Vector<Product> products;
	private Vector<Product> filteredProducts;
	private Product selectedProduct;
	private JScrollPane scrollPane_1;
	private User user;
	private boolean productHasTrustSale = false;
	private TrustSale selectedProductTrustSale = new TrustSale();
	private JLabel label;
	
	
	private boolean productIsAlreadySold(int productId) {
		boolean result = false;
		Database db = new Database();
		result = db.isProductSold(productId);
		db.disconnect();
		return result;
	}
	
	private void loadProducts() {
		Database database = new Database();
		products = database.getProducts("");
		database.disconnect();
		
	}
	
	private void searchProducts (String input) {
		if (!products.isEmpty()) {
			listProducts.setListData(searchResult(input));
			listProducts.repaint();
			scrollPane_1.setVisible(true);
		}else {
			scrollPane_1.setVisible(false);
		}
	}
	
	private Vector searchResult (String input) {
		Vector<String> result = new Vector<>();
		filteredProducts = new Vector<>();
		for(int i = 0; i< products.size();i++) {
			Product product = products.elementAt(i);
			if(product.getImei().contains(input)) {
				filteredProducts.add(product);
				Database database = new Database ();
				Category category = database.getCategory(product.getCategoryId());
				String productName = category.getName();
				String productImei = product.getImei();
				String productCode = product.getCode()+"";
				result.add(productName+" "+productImei+" "+productCode);
				database.disconnect();
			}
			
		}
		return result;
	}

	/**
	 * Create the panel.
	 * 
	 */
	public NewSale(User user) {
		this.user = user;
		loadProducts();
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		tfSearch = new JTextField();
		
		tfSearch.setText("Search IMEI");
		tfSearch.setForeground(darkBlue);
		tfSearch.setBounds(50, 242, 530, 30);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblNewLabel.setBounds(50, 283, 125, 30);
		add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setBounds(185, 286, 125, 25);
		add(tfName);
		tfName.setColumns(10);
		
		JLabel lblProductCategory = new JLabel("Product Category");
		lblProductCategory.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblProductCategory.setBounds(320, 283, 125, 30);
		add(lblProductCategory);
		
		tfCategory = new JTextField();
		tfCategory.setEditable(false);
		tfCategory.setColumns(10);
		tfCategory.setBounds(455, 286, 125, 25);
		add(tfCategory);
		
		JLabel lblProductImei = new JLabel("Product IMEI");
		lblProductImei.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblProductImei.setBounds(50, 324, 125, 30);
		add(lblProductImei);
		
		tfImei = new JTextField();
		tfImei.setEditable(false);
		tfImei.setColumns(10);
		tfImei.setBounds(185, 327, 125, 25);
		add(tfImei);
		
		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblProductCode.setBounds(320, 324, 125, 30);
		add(lblProductCode);
		
		tfCode = new JTextField();
		tfCode.setEditable(false);
		tfCode.setColumns(10);
		tfCode.setBounds(455, 327, 125, 25);
		add(tfCode);
		
		JLabel lblMerchant = new JLabel("Merchant");
		lblMerchant.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblMerchant.setBounds(50, 365, 125, 30);
		add(lblMerchant);
		
		tfMerchant = new JTextField();
		tfMerchant.setEditable(false);
		tfMerchant.setColumns(10);
		tfMerchant.setBounds(185, 368, 125, 25);
		add(tfMerchant);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblCustomer.setBounds(320, 365, 125, 30);
		add(lblCustomer);
		
		tfCustomer = new JTextField();
		tfCustomer.setColumns(10);
		tfCustomer.setBounds(455, 368, 125, 25);
		add(tfCustomer);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblPhoneNumber.setBounds(50, 406, 125, 30);
		add(lblPhoneNumber);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(185, 409, 125, 25);
		add(tfPhoneNumber);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAmount.setBounds(320, 406, 125, 30);
		add(lblAmount);
		
		tfAmount = new JTextField();
		tfAmount.setColumns(10);
		tfAmount.setBounds(455, 409, 125, 25);
		add(tfAmount);
		
		btnProcess = new JPanel();
		btnProcess.setBackground(darkBlue);
		btnProcess.setBounds(224, 478, 180, 30);
		add(btnProcess);
		btnProcess.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Process");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(white);
		lblNewLabel_1.setBounds(0, 0, 180, 30);
		btnProcess.add(lblNewLabel_1);
		
		listProducts = new JList();
		listProducts.setVisibleRowCount(4);
		listProducts.setBackground(lightBlue);
		listProducts.setForeground(darkBlue);
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 127, 530, 114);
		scrollPane_1.setViewportView(listProducts);
		add(scrollPane_1);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(NewSale.class.getResource("/com/erickisee/app/ui/images/sales_b.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 626, 105);
		add(label);
		scrollPane_1.setVisible(false);
		

		btnProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String amount = tfAmount.getText();
				String customerName = tfCustomer.getText();
				String phone = tfPhoneNumber.getText();
				
				if(tfName.getText().isEmpty()||amount.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fill all requried fields");
					
				}else {
					if(productIsAlreadySold(selectedProduct.getId())) {
						JOptionPane.showMessageDialog(null, "This  product is already sold.");
					}else {
						Database db = new Database();
						Customer customer = new Customer();
						
						
						if(!phone.isEmpty()) {
							customer.setName(customerName);
							customer.setPhoneNumber(phone);
							if(db.customerExists(customer)) {
								customer = db.getCustomer(customer);
								customer.setPurchases(customer.getPurchases()+1);
								db.updateCustomer(customer);
							}else {
								customer.setEmail("");
								customer.setPurchases(1);
								db.createCustomer(customer);
								customer= db.getCustomer(customer);
							}
						}
						
						
						Sale sale = new Sale();
						sale.setAmount(Integer.parseInt(amount));
						sale.setCustomerId(customer.getId());
						sale.setDatetime(new Methods().dateTime());
						sale.setMerchantId(selectedProductTrustSale.getMerchantId());
						sale.setProductId(selectedProduct.getId());
						sale.setUserId(user.getId());
						if(db.saveSale(sale)) {
							JOptionPane.showMessageDialog(null, "Sale Recorded.");
						}else {
							
						}
						if(productHasTrustSale) {
							int status = selectedProductTrustSale.getStatus();
							if(status==0) {
								selectedProductTrustSale.setStatus(1);
								db.updateTrustSale(selectedProductTrustSale);
							}
						}
						db.disconnect();
					}
				}
				
			}
		});
		
		listProducts.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int pos = listProducts.getSelectedIndex();
				if (!(pos<0)) {
					selectedProduct = filteredProducts.elementAt(pos);
					Database database = new Database();
					Category category = database.getCategory(selectedProduct.getCategoryId());
					tfName.setText(category.getName());
					tfCategory.setText(category.getName());
					tfImei.setText(selectedProduct.getImei());
					tfCode.setText(selectedProduct.getCode());
					
					if(database.getTrustSales(selectedProduct).size()>0) {
						
						selectedProductTrustSale = database.getTrustSales(selectedProduct).lastElement();
						if (selectedProductTrustSale.getStatus()==0) {
							Merchant merchant =  database.getMerchant(selectedProductTrustSale.getMerchantId());
							String merchantName =merchant.getName();
							tfMerchant.setText(merchantName);
							tfPhoneNumber.setText(merchant.getPhoneNumber());
							tfCustomer.setText(merchantName);
							productHasTrustSale = true;
						}
					}else {
						productHasTrustSale=false;
						tfMerchant.setText("");
						tfPhoneNumber.setText("");
						tfCustomer.setText("");
					}
					
					database.disconnect();
				}else {
					tfMerchant.setText("");
					tfPhoneNumber.setText("");
					tfCustomer.setText("");
					tfMerchant.setText("");
					tfPhoneNumber.setText("");
					tfCustomer.setText("");
					
				}
				
			}
		});
		tfSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String searchString = tfSearch.getText();
				if(searchString.equals("Search IMEI"))
					tfSearch.setText("");
			}
			
		});
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				searchProducts(tfSearch.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchProducts(tfSearch.getText());
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				searchProducts(tfSearch.getText());
			}
		});

	}
}
