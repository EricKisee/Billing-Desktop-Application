package com.erickisee.app.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.erickisee.app.Methods;
import com.erickisee.app.db.Database;
import com.erickisee.app.models.Category;
import com.erickisee.app.models.Merchant;
import com.erickisee.app.models.Product;
import com.erickisee.app.models.TrustSale;
import com.erickisee.app.models.User;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.ImageIcon;

public class NewTrustSale extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private JTextField tfMerchant;
	private JTextField tfProduct;
	private JTextField tfComment;
	private JList listProducts ;
	private JList listMerchants ;
	private Vector<Product> products;
	private Vector<Merchant> merchants;
	private Vector<Product> filteredProducts;
	private Vector<Merchant> filteredMerchants;
	private Product selectedProduct;
	private Merchant selectedMerchant;
	private JScrollPane scrollPaneProducts;
	private JScrollPane scrollPaneMerchants;
	private User user;
	private JLabel label_1;
	

	private void loadData() {
		Database database = new Database();
		products = database.getProducts("");
		merchants = database.getMerchants("");
		filteredProducts = products;
		filteredMerchants = merchants;
		
		database.disconnect();
		
	}
	
	private boolean productIsAlreadySold(Product product) {
		boolean result = false;
		Database db = new Database();
		result = db.isProductSold(product.getId());
		db.disconnect();
		return result;
	}
	
	private boolean productHasPendingTrustSale(Product product) {
		boolean result = false;
		Database database = new Database();
		Vector<TrustSale> trustSales = database.getTrustSales(product);
		if(trustSales.size()>0) {
			TrustSale ts = trustSales.lastElement();
			if (ts.getStatus()==0) {
				result = true;
			}
		}
		return result;
	}
	
	private boolean productIsAvailable() {
		boolean result = false;
		return result;
	}
	
	private void searchProducts (String input) {
		if (!products.isEmpty()) {
			listProducts.setListData(searchResultProducts(input));
			listProducts.repaint();
			scrollPaneProducts.setVisible(true);
		}else {
			scrollPaneProducts.setVisible(false);
		}
	}
	private Vector <String> searchResultProducts (String input) {
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

	private Vector <String> searchResultMerchants (String input) {
		Vector<String> result = new Vector<>();
		filteredMerchants = new Vector<>();
		for(int i = 0; i< merchants.size();i++) {
			Merchant merchant = merchants.elementAt(i);
			if(merchant.getName().toLowerCase().contains(input.toLowerCase())) {
				filteredMerchants.add(merchant);
				result.add(merchant.getName()+" "+merchant.getEmail()+" "+merchant.getPhoneNumber());
			}
			
		}
		return result;
	}
	
	private void searchMerchants (String input) {
		if (!merchants.isEmpty()) {
			listMerchants.setListData(searchResultMerchants(input));
			listMerchants.repaint();
			scrollPaneMerchants.setVisible(true);
		}else {
			scrollPaneMerchants.setVisible(false);
		}
	}
	
	
	/**
	 * Create the panel.
	 */
	public NewTrustSale(User user) {
		this.user = user;
		loadData();
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Merchant");
		lblNewLabel.setBounds(84, 159, 100, 30);
		add(lblNewLabel);
		
		tfMerchant = new JTextField();
		tfMerchant.setBounds(84, 200, 200, 30);
		add(tfMerchant);
		tfMerchant.setColumns(10);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setBounds(367, 159, 100, 30);
		add(lblProduct);
		
		tfProduct = new JTextField();
		tfProduct.setColumns(10);
		tfProduct.setBounds(367, 203, 200, 30);
		add(tfProduct);
		
		JLabel lblComment = new JLabel("Comment");
		lblComment.setBounds(84, 350, 100, 30);
		add(lblComment);
		
		tfComment = new JTextField();
		tfComment.setColumns(10);
		tfComment.setBounds(236, 350, 331, 30);
		add(tfComment);
		
		JPanel btnProcess = new JPanel();
		
		btnProcess.setLayout(null);
		btnProcess.setBackground(new Color(106, 117, 202));
		btnProcess.setBounds(236, 412, 180, 30);
		add(btnProcess);
		
		JLabel label = new JLabel("Process");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label.setBounds(0, 0, 180, 30);
		btnProcess.add(label);
		

		listProducts = new JList();
		listMerchants = new JList();
		
		
		scrollPaneProducts = new JScrollPane();
		scrollPaneProducts.setBounds(367, 237, 200, 102);
		scrollPaneProducts.setViewportView(listProducts);
		add(scrollPaneProducts);
		
		scrollPaneMerchants = new JScrollPane();
		scrollPaneMerchants.setBounds(84, 237, 200, 102);
		scrollPaneMerchants.setViewportView(listMerchants);
		add(scrollPaneMerchants);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(NewTrustSale.class.getResource("/com/erickisee/app/ui/images/trustsales_b.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 11, 626, 105);
		add(label_1);
		
		searchMerchants("");
		searchProducts("");
		
		btnProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(listProducts.getSelectedIndex()==-1||listMerchants.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(null, "Select product and merchant.");
				}else {
					if (productIsAlreadySold(selectedProduct)) {
						JOptionPane.showMessageDialog(null, "Selected product has already been sold");
					}else if(productHasPendingTrustSale(selectedProduct)) {
						JOptionPane.showMessageDialog(null, "Selected product has a pending trust sale");
					}
					else {
						TrustSale trustSale = new TrustSale();
						trustSale.setComment(tfComment.getText());
						trustSale.setDatetime(new Methods().dateTime());
						trustSale.setMerchantId(selectedMerchant.getId());
						trustSale.setProductId(selectedProduct.getId());
						trustSale.setStatus(0);
						trustSale.setUserId(user.getId());
						
						Database db = new Database();
						if(db.saveTrustSale(trustSale))
							JOptionPane.showMessageDialog(null, "TrustSale Created.");
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
				if(!(pos<0)) {
				selectedProduct = filteredProducts.elementAt(pos);
				}
			}
		});
		listMerchants.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int pos = listMerchants.getSelectedIndex();
				if(!(pos<0)) {
				selectedMerchant = filteredMerchants.elementAt(pos);
				}
			}
		});
			tfMerchant.getDocument().addDocumentListener(new DocumentListener() {
						
				@Override
				public void removeUpdate(DocumentEvent e) {
					searchMerchants(tfMerchant.getText());
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					searchMerchants(tfMerchant.getText());
					
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					searchMerchants(tfMerchant.getText());
				}
			});

			tfProduct.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					searchProducts(tfProduct.getText());
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					searchProducts(tfProduct.getText());
					
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					searchProducts(tfProduct.getText());
				}
			});


	}
}
