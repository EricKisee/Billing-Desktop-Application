package com.erickisee.app.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.User;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ApplicationUI extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private final JLabel lblNewLabel = new JLabel("Apple Shop");
	private User user;
	private JLayeredPane layeredPane ;
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	private JPanel panelLogin ;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationUI frame = new ApplicationUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void resetUser () {
		user = new User();
		user.setId(0);
		user.setRank("");
		user.setEmail("");
		user.setName("");
		user.setPassword("");
	}

	/**
	 * Create the frame.
	 */
	public ApplicationUI() {
		resetUser();
		init();
	}
	
	public void init() {

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		panel.setForeground(darkBlue);
		panel.setBackground(white);
		contentPane.add(panel, "name_783073632291400");
		panel.setLayout(null);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(new Home());
			}
		});
		lblNewLabel.setForeground(darkBlue);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 157, 65);
		
		panel.add(lblNewLabel);
		
		
		JLabel lblNewLabel_2 = new JLabel("Make Sale");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(new NewSale(user));
			}
		});
		lblNewLabel_2.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(72, 204, 157, 30);
		panel.add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 204, 30, 30);
		panel.add(label);
		
		JLabel lblMakeTrustSale = new JLabel("Make Trust Sale");
		lblMakeTrustSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(user.getRank().equalsIgnoreCase("Admin")) {
					
				switchPanels(new NewTrustSale(user));
				}else {
					JOptionPane.showMessageDialog(null, "Action not allowed.");
				}
			}
			
		});
		lblMakeTrustSale.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblMakeTrustSale.setBounds(72, 245, 157, 30);
		panel.add(lblMakeTrustSale);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(10, 245, 30, 30);
		panel.add(label_2);
		
		JLabel lblTrustSales = new JLabel("Trust Sales");
		lblTrustSales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(new TrustSales(user));
			}
		});
		lblTrustSales.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblTrustSales.setBounds(72, 286, 157, 30);
		panel.add(lblTrustSales);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(10, 286, 30, 30);
		panel.add(label_4);
		
		JLabel lblAddMerchant = new JLabel("Add User");
		lblAddMerchant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(user.getRank().equalsIgnoreCase("Admin")) {
				switchPanels(new Register(user));
					
				}else {
					JOptionPane.showMessageDialog(null, "Action not allowed.");
				}
			}
		});
		lblAddMerchant.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAddMerchant.setBounds(72, 327, 157, 30);
		panel.add(lblAddMerchant);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(10, 327, 30, 30);
		panel.add(label_6);
		
		JLabel lblAddMerchant_1 = new JLabel("Add Merchant");
		lblAddMerchant_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(user.getRank().equalsIgnoreCase("Admin")) {
				switchPanels(new NewMerchant(user));
					
				}else {
					JOptionPane.showMessageDialog(null, "Action not allowed.");
				}
			}
			
		});
		lblAddMerchant_1.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAddMerchant_1.setBounds(72, 368, 157, 30);
		panel.add(lblAddMerchant_1);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(10, 368, 30, 30);
		panel.add(label_8);
		
		JLabel lblAddProductCategory = new JLabel("Add Product Category");
		lblAddProductCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(user.getRank().equalsIgnoreCase("Admin")) {
				switchPanels(new NewCategory(user));
					
				}else {
					JOptionPane.showMessageDialog(null, "Action not allowed.");
				}
			}
		});
		lblAddProductCategory.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAddProductCategory.setBounds(72, 409, 157, 30);
		panel.add(lblAddProductCategory);
		
		JLabel lblAddProduct = new JLabel("Add Product");
		lblAddProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(user.getRank().equalsIgnoreCase("Admin")) {
				switchPanels(new NewProduct(user));
					
				}else {
					JOptionPane.showMessageDialog(null, "Action not allowed.");
				}
				
			}
		});
		lblAddProduct.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAddProduct.setBounds(72, 450, 157, 30);
		panel.add(lblAddProduct);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(10, 409, 30, 30);
		panel.add(label_3);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ApplicationUI.class.getResource("/com/erickisee/app/ui/images/user.png")));
		lblNewLabel_3.setBounds(10, 52, 129, 128);
		panel.add(lblNewLabel_3);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(238, 0, 636, 551);
		panel.add(layeredPane);
		
		panelLogin = new JPanel();
		panelLogin.setBounds(0, 0, 636, 551);
		layeredPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel label_1 = new JLabel("Email");
		label_1.setBounds(168, 226, 100, 30);
		panelLogin.add(label_1);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(317, 226, 200, 30);
		panelLogin.add(tfEmail);
		
		JLabel label_5 = new JLabel("Password");
		label_5.setBounds(168, 267, 100, 30);
		panelLogin.add(label_5);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(317, 267, 200, 30);
		panelLogin.add(tfPassword);
		
		JPanel btnLogin = new JPanel();
		btnLogin.setLayout(null);
		btnLogin.setBackground(new Color(106, 117, 202));
		btnLogin.setBounds(262, 324, 180, 30);
		panelLogin.add(btnLogin);
		
		
		
		JLabel label_7 = new JLabel("Login");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label_7.setBounds(0, 0, 180, 30);
		btnLogin.add(label_7);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setBounds(136, 94, 93, 20);
		panel.add(lblUsername);
		lblUsername.setVisible(false);
		
		JPanel logout = new JPanel();
		logout.setLayout(null);
		logout.setBackground(new Color(106, 117, 202));
		logout.setBounds(136, 125, 91, 30);
		panel.add(logout);
		logout.setVisible(false);
		
		JLabel btnLogout = new JLabel("Logout");
		btnLogout.setHorizontalAlignment(SwingConstants.CENTER);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logout.setVisible(false);
				lblUsername.setVisible(false);
				lblUsername.setText("");
				logout();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnLogout.setBounds(0, 0, 91, 30);
		logout.add(btnLogout);
		
		JLabel btnHome = new JLabel("");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(new Home());
			}
		});
		btnHome.setIcon(new ImageIcon(ApplicationUI.class.getResource("/com/erickisee/app/ui/images/home.png")));
		btnHome.setBackground(Color.LIGHT_GRAY);
		btnHome.setBounds(165, 11, 64, 65);
		panel.add(btnHome);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email = tfEmail.getText();
				String password = tfPassword.getText();
				
				if(email.isEmpty()||password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fill all fields.");
				}
				else {
					Database db = new Database();
					int userId = db.login(email,password);
					if (userId>0) {
						tfEmail.setText("");
						tfPassword.setText("");
						user = db.getUser(userId);
						logout.setVisible(true);
						lblUsername.setVisible(true);
						lblUsername.setText(user.getName());
						switchPanels(new Home());
					}else {
						JOptionPane.showMessageDialog(null, "Login Failed.");
					}
					db.disconnect();
				}
			}
		});
	}
	
	
	private void switchPanels(JPanel panel) {
		if(user.getId()==0) {
			JOptionPane.showMessageDialog(null, "Please login first");
		}else {
			layeredPane.removeAll();
			layeredPane.add(panel);
			layeredPane.repaint();
			layeredPane.revalidate();
		}
	}
	
	private void logout() {
		switchPanels(panelLogin);
		resetUser();
		
	}
	
	
	public class Home extends JPanel {
		
		private Color lightBlue = new Color (239,242,248);
		private Color darkBlue = new Color (106,117,202);
		private Color white = new Color (255,255,255);

		/**
		 * Create the panel.
		 */
		public Home() {
			setBackground(lightBlue);
			setBounds(0, 0, 646, 561);
			setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(Color.WHITE);
			panel.setBounds(50, 50, 150, 200);
			add(panel);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBackground(new Color(106, 117, 202));
			panel_1.setBounds(0, 150, 150, 50);
			panel.add(panel_1);
			
			JLabel lblProducts = new JLabel("Products");
			lblProducts.setForeground(Color.WHITE);
			lblProducts.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblProducts.setBounds(10, 0, 83, 50);
			lblProducts.setHorizontalAlignment(SwingConstants.CENTER);
			panel_1.add(lblProducts);
			
			JLabel lblNewProduct = new JLabel("+");
			lblNewProduct.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewProduct.setBounds(103, 0, 47, 50);
			panel_1.add(lblNewProduct);
			
			JLabel lblProductImage = new JLabel("");
			lblProductImage.setIcon(new ImageIcon(Home.class.getResource("/com/erickisee/app/ui/images/products.png")));
			lblProductImage.setHorizontalTextPosition(JLabel.CENTER);
			lblProductImage.setBounds(0, 0, 150, 200);
			panel.add(lblProductImage);
			
			lblProducts.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Products(user));
				}});
			lblProductImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Products(user));
				}});
			lblNewProduct.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new NewProduct(user));
				}});
			
			JPanel panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBackground(Color.WHITE);
			panel_2.setBounds(250, 50, 150, 200);
			add(panel_2);
			
			JPanel panel_3 = new JPanel();
			panel_3.setLayout(null);
			panel_3.setBackground(new Color(106, 117, 202));
			panel_3.setBounds(0, 150, 150, 50);
			panel_2.add(panel_3);
			
			JLabel lblCategories = new JLabel("Categories");
			lblCategories.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategories.setForeground(Color.WHITE);
			lblCategories.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblCategories.setBounds(10, 0, 83, 50);
			panel_3.add(lblCategories);
			
			JLabel lblNewCategory = new JLabel("+");
			lblNewCategory.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewCategory.setBounds(103, 0, 47, 50);
			panel_3.add(lblNewCategory);
			
			JLabel lblCategoriesImage = new JLabel("");
			lblCategoriesImage.setIcon(new ImageIcon(Home.class.getResource("/com/erickisee/app/ui/images/6.png")));
			lblCategoriesImage.setBounds(0, 0, 150, 200);
			panel_2.add(lblCategoriesImage);
			
			lblCategories.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Categories(user));
				}});
			lblCategoriesImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Categories(user));
				}});
			lblNewCategory.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if(user.getRank().equalsIgnoreCase("Admin")) {
						
					switchPanels(new NewCategory(user));
					}else {
						JOptionPane.showMessageDialog(null, "Action not allowed.");
					}
				}});
			
			JPanel panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.setBackground(Color.WHITE);
			panel_4.setBounds(450, 50, 150, 200);
			add(panel_4);
			
			JPanel panel_5 = new JPanel();
			panel_5.setLayout(null);
			panel_5.setBackground(new Color(106, 117, 202));
			panel_5.setBounds(0, 150, 150, 50);
			panel_4.add(panel_5);
			
			JLabel lblMerchants = new JLabel("Merchants");
			lblMerchants.setHorizontalAlignment(SwingConstants.CENTER);
			lblMerchants.setForeground(Color.WHITE);
			lblMerchants.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblMerchants.setBounds(10, 0, 83, 50);
			panel_5.add(lblMerchants);
			
			JLabel lblNewMerchant = new JLabel("+");
			lblNewMerchant.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewMerchant.setBounds(103, 0, 47, 50);
			panel_5.add(lblNewMerchant);
			
			JLabel lblMerchantImage = new JLabel("");
			lblMerchantImage.setIcon(new ImageIcon(Home.class.getResource("/com/erickisee/app/ui/images/merchants.png")));
			lblMerchantImage.setBounds(0, 0, 150, 200);
			panel_4.add(lblMerchantImage);
			
			lblMerchants.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Merchants(user));
				}});
			lblMerchantImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Merchants(user));
				}});
			lblNewMerchant.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if(user.getRank().equalsIgnoreCase("Admin")) {
						
					switchPanels(new NewMerchant(user));
					}else {
						JOptionPane.showMessageDialog(null, "Action not allowed.");
					}
				}});
			
			JPanel panel_6 = new JPanel();
			panel_6.setLayout(null);
			panel_6.setBackground(Color.WHITE);
			panel_6.setBounds(450, 325, 150, 200);
			add(panel_6);
			
			JPanel panel_7 = new JPanel();
			panel_7.setLayout(null);
			panel_7.setBackground(new Color(106, 117, 202));
			panel_7.setBounds(0, 150, 150, 50);
			panel_6.add(panel_7);
			
			JLabel lblUsers = new JLabel("Users");
			lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsers.setForeground(Color.WHITE);
			lblUsers.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblUsers.setBounds(10, 0, 83, 50);
			panel_7.add(lblUsers);
			
			JLabel lblNewUser = new JLabel("+");
			lblNewUser.setBounds(103, 0, 47, 50);
			lblNewUser.setHorizontalAlignment(SwingConstants.CENTER);
			panel_7.add(lblNewUser);
			
			JLabel lblUsersImage = new JLabel("");
			lblUsersImage.setIcon(new ImageIcon(Home.class.getResource("/com/erickisee/app/ui/images/users.png")));
			lblUsersImage.setBounds(0, 0, 150, 200);
			panel_6.add(lblUsersImage);
			
			lblUsers.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Users(user));
				}});
			lblUsersImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Users(user));
				}});
			lblNewUser.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(user.getRank().equalsIgnoreCase("Admin")) {
						
					switchPanels(new Register(user));
					}else {
						JOptionPane.showMessageDialog(null, "Action not allowed.");
					}
				}});
			
			JPanel panel_8 = new JPanel();
			panel_8.setLayout(null);
			panel_8.setBackground(Color.WHITE);
			panel_8.setBounds(250, 325, 150, 200);
			add(panel_8);
			
			JPanel panel_9 = new JPanel();
			panel_9.setLayout(null);
			panel_9.setBackground(new Color(106, 117, 202));
			panel_9.setBounds(0, 150, 150, 50);
			panel_8.add(panel_9);
			
			JLabel lblTrustSales = new JLabel("Trust Sales");
			lblTrustSales.setHorizontalAlignment(SwingConstants.CENTER);
			lblTrustSales.setForeground(Color.WHITE);
			lblTrustSales.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblTrustSales.setBounds(10, 0, 83, 50);
			panel_9.add(lblTrustSales);
			
			JLabel lblNewTrustSale = new JLabel("+");
			lblNewTrustSale.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewTrustSale.setBounds(103, 0, 47, 50);
			panel_9.add(lblNewTrustSale);
			
			JLabel lblTrustSalesImage = new JLabel("");
			lblTrustSalesImage.setIcon(new ImageIcon(Home.class.getResource("/com/erickisee/app/ui/images/trustsales.png")));
			lblTrustSalesImage.setBounds(0, 0, 150, 200);
			panel_8.add(lblTrustSalesImage);

			lblTrustSales.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new TrustSales(user));
				}});
			lblTrustSalesImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new TrustSales(user));
				}});
			lblNewTrustSale.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(user.getRank().equalsIgnoreCase("Admin")) {
						
					switchPanels(new NewTrustSale(user));
					}else {
						JOptionPane.showMessageDialog(null, "Action not allowed.");
					}
				}});
			
			
			JPanel panel_10 = new JPanel();
			panel_10.setLayout(null);
			panel_10.setBackground(Color.WHITE);
			panel_10.setBounds(50, 325, 150, 200);
			add(panel_10);
			
			JPanel panel_11 = new JPanel();
			panel_11.setLayout(null);
			panel_11.setBackground(new Color(106, 117, 202));
			panel_11.setBounds(0, 150, 150, 50);
			panel_10.add(panel_11);
			
			JLabel lblSales = new JLabel("Sales");
			lblSales.setHorizontalAlignment(SwingConstants.CENTER);
			lblSales.setForeground(Color.WHITE);
			lblSales.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblSales.setBounds(10, 0, 83, 50);
			panel_11.add(lblSales);
			
			JLabel lblNewSale = new JLabel("+");
			lblNewSale.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewSale.setBounds(103, 0, 47, 50);
			panel_11.add(lblNewSale);
			
			JLabel lblSalesImage = new JLabel("");
			lblSalesImage.setIcon(new ImageIcon(Home.class.getResource("/com/erickisee/app/ui/images/sales.png")));
			lblSalesImage.setBounds(0, 0, 150, 200);
			panel_10.add(lblSalesImage);
			

			lblSales.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Sales(user));
				}});
			lblSalesImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new Sales(user));
				}});
			lblNewSale.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switchPanels(new NewSale(user));
				}});

		}
	}
}
