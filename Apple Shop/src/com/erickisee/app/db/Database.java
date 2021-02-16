package com.erickisee.app.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.erickisee.app.models.Category;
import com.erickisee.app.models.Customer;
import com.erickisee.app.models.Merchant;
import com.erickisee.app.models.Product;
import com.erickisee.app.models.Sale;
import com.erickisee.app.models.TrustSale;
import com.erickisee.app.models.User;

public class Database {
	
	private Connection connection;
	
	public Database () {
		if(connect()) {
			createTables();
		}
	}
	
	public boolean connect () {
	
		boolean res = false;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/test;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "");
			createTables();
			res = true;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return res;
	}
	
	private void createTables() {
		try {
			DatabaseMetaData dmd = connection.getMetaData();
			ResultSet set1 = dmd.getTables(null, null, "User", null);
			if(set1.next()) {
				//table exists. do nothing
			} else {
				String createTable = "create table User ("
						+ "id INT PRIMARY KEY AUTO_INCREMENT,"
						+ "name varchar2(100),"
						+ "email varchar2(100),"
						+ "password varchar2(100),"
						+ "rank varchar2(20))";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
				
				User user = new User();
				user.setEmail("admin@admin.com");
				user.setName("Administator");
				user.setPassword("admin2020");
				user.setRank("admin");
				saveUser(user);
			}
			ResultSet set2 = dmd.getTables(null, null, "TrustSale", null);
			if(set2.next()) {
				//table exists. do nothing
			} else {
				String createTable = "create table TrustSale ("
						+ "id INT PRIMARY KEY AUTO_INCREMENT,"
						+ "datetime varchar2(100),"
						+ "merchantId INT,"
						+ "productId INT,"
						+ "userId INT,"
						+ "status varchar2(20),"
						+ "comment varchar2(100))";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
			}
			ResultSet set3 = dmd.getTables(null, null, "Sale", null);
			if(set3.next()) {
				//table exists. do nothing
			} else {
				String createTable = "create table  Sale ("
						+ "id INT PRIMARY KEY AUTO_INCREMENT,"
						+ "datetime varchar2(100),"
						+ "merchantId INT,"
						+ "productId INT,"
						+ "userId INT,"
						+ "customerId INT,"
						+ "amount INT)";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
			}
			ResultSet set4 = dmd.getTables(null, null, "Product", null);
			if(set4.next()) {
				//table exists. do nothing
			} else {
				String createTable = "create table  Product ("
						+ "id INT PRIMARY KEY AUTO_INCREMENT,"
						+ "datetime varchar2(100),"
						+ "code varchar2(100),"
						+ "imei varchar2(100),"
						+ "price INT,"
						+ "categoryId INT,"
						+ "userId INT)";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
			}
			ResultSet set5 = dmd.getTables(null, null, "Merchant", null);
			if(set5.next()) {
				//table exists. do nothing
			} else {
				String createTable = "create table  Merchant ("
						+ "id INT PRIMARY KEY AUTO_INCREMENT,"
						+ "email varchar2(100),"
						+ "phoneNumber varchar2(100),"
						+ "name varchar2(100))";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
			}
			ResultSet set6 = dmd.getTables(null, null, "Customer", null);
			if(set6.next()) {
				//table exists. do nothing
			} else {
				String createTable = "create table  Customer ("
						+ "id INT PRIMARY KEY AUTO_INCREMENT,"
						+ "purchases INT,"
						+ "email varchar2(100),"
						+ "phoneNumber varchar2(100),"
						+ "name varchar2(100))";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
			}
			ResultSet set7 = dmd.getTables(null, null, "Category", null);
			if(set7.next()) {
				//table exists. do nothing
			} else {
				String createTable = "create table  Category ("
						+ "id INT PRIMARY KEY AUTO_INCREMENT,"
						+ "description varchar2(100),"
						+ "name varchar2(100))";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
			}
		}catch(Exception e) {}
	}
	
	public void disconnect() {
		try {
			if(!connection.equals(null))
				if(!connection.isClosed())
					connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean saveUser(User user) {
		
		boolean result = false;
		String insertUser = "insert into user (name, email, password, rank) values"
			+ "(?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertUser);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getRank());
			int res = statement.executeUpdate();
			if(res>0) 
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;
	}
	
	public boolean saveMerchant (Merchant merchant) {
		boolean result = false;
		String insertMerchant = "insert into merchant (name, email, phoneNumber) values"
				+ "(?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertMerchant);
			statement.setString(1, merchant.getName());
			statement.setString(2, merchant.getEmail());
			statement.setString(3, merchant.getPhoneNumber());
			int res = statement.executeUpdate();
			if(res>0) 
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean saveCategory (Category category) {
		boolean result = false;
		String insertCategory = "insert into category (name, description) values"
				+ "(?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertCategory);
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			int res = statement.executeUpdate();
			if(res>0) 
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean delete(User user) {
		boolean result = false;
		String sql = "delete from user where id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			int res = statement.executeUpdate();
			if (res>0) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean saveProduct (Product product) {
		boolean result = false;
		String insertProduct = "insert into product (datetime, code, imei, price, categoryId, userId) values"
				+ "(?,?,?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertProduct);
			statement.setString(1, product.getDatetime());
			statement.setString(2, product.getCode());
			statement.setString(3, product.getImei());
			statement.setInt(4, product.getPrice());
			statement.setInt(5, product.getCategoryId());
			statement.setInt(6, product.getUserId());
			int res = statement.executeUpdate();
			if(res>0) 
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean saveSale (Sale sale) {
		boolean result = false;
		String insertProduct = "insert into sale (datetime, merchantId, productId, userId, customerId, amount) values"
				+ "(?,?,?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertProduct);
			statement.setString(1, sale.getDatetime());
			statement.setInt(2, sale.getMerchantId());
			statement.setInt(3, sale.getProductId());
			statement.setInt(4, sale.getUserId());
			statement.setInt(5, sale.getCustomerId());
			statement.setInt(6, sale.getAmount());
			int res = statement.executeUpdate();
			if(res>0) 
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean saveTrustSale (TrustSale trustSale) {
		boolean result = false;
		String insertTrustSale = "insert into trustSale (datetime, merchantId, productId, userId, status, comment) values"
				+ "(?,?,?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertTrustSale);
			statement.setString(1, trustSale.getDatetime());
			statement.setInt(2, trustSale.getMerchantId());
			statement.setInt(3, trustSale.getProductId());
			statement.setInt(4, trustSale.getUserId());
			statement.setInt(5, trustSale.getStatus());
			statement.setString(6, trustSale.getComment());
			int res = statement.executeUpdate();
			if(res>0) 
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int login (String email , String password) {
		int result = 0;
		
		String validate  = "select * from user where email=? and password=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(validate);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet res = statement.executeQuery();
			if(res.next())
				result = res.getInt("id");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public boolean customerExists(Customer customer){
		boolean result = false;
		String validate = "select * from customer where phoneNumber=?";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(validate);
			statement.setString(1,customer.getPhoneNumber());
			ResultSet res = statement.executeQuery();
			if(res.next())
				result = true;
		}catch (SQLException e) {e.printStackTrace();}
		return result;
	}
	
	
	
	public boolean createCustomer(Customer customer) {
		boolean result = false;
		if(customerExists(customer)) {
			result=false;
		}else {
			String insertCustomer = "insert into customer (email, phoneNumber, name , purchases) values"
					+ "(?,?,?,?)";
			try {
				PreparedStatement statement = connection.prepareStatement(insertCustomer);
				statement.setString(1, customer.getEmail());
				statement.setString(2, customer.getPhoneNumber());
				statement.setString(3, customer.getName());
				statement.setInt(4, customer.getPurchases());
				int res = statement.executeUpdate();
				if(res>0) {
					result=true;
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private User getUserFromResultSet(ResultSet res) throws SQLException {
		User user = new User();
		user.setId(res.getInt("id"));
		user.setEmail(res.getString("email"));
		user.setName(res.getString("name"));
		user.setRank(res.getString("rank"));
		user.setPassword(res.getString("rank"));
		return user;
	}
	
	public User getUser (int id) {
		User user = new User();
		String validate  = "select * from user where id=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(validate);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				user = getUserFromResultSet(res);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return user;
	}
	
	public Category getCategory(int id){
		Category category = new Category ();
		
		String search = "select * from category where id=?";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				category = getCategoryFromResult(res);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return category;
	}
	
	private Category getCategoryFromResult(ResultSet res) throws SQLException  {
		Category category = new Category();
		category.setId(res.getInt("id"));
		category.setDescription(res.getString("description"));
		category.setName(res.getString("name"));
		return category;
	}
	
	public Vector<Category> getCategories (String query){
		Vector <Category> categories = new Vector<>();
		String search = "select * from category where name like '%"+query+"%'";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);

			ResultSet res = statement.executeQuery();
			while(res.next()) {
				categories.add(getCategoryFromResult(res));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return categories;
	}
	
	public Vector<Sale> getSales(){
		Vector <Sale> sales = new Vector<>();
		String search = "select * from sale";
		try {
			PreparedStatement statement = connection.prepareStatement(search);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				sales.add(getSaleFromResult(res));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sales;
	}
	
	private Sale getSaleFromResult(ResultSet res) throws SQLException {
		Sale sale = new Sale();
		sale.setAmount(res.getInt("amount"));
		sale.setCustomerId(res.getInt("customerId"));
		sale.setDatetime(res.getString("datetime"));
		sale.setId(res.getInt("id"));
		sale.setMerchantId(res.getInt("merchantId"));
		sale.setProductId(res.getInt("productId"));
		sale.setUserId(res.getInt("userId"));
		return sale;
	}

	public Product getProduct(int id){
		Product product = new Product ();
		
		String search = "select * from product where id=?";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				product = getProductFromResultSet(res);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return product;
	}
	
	private Product getProductFromResultSet (ResultSet res) throws SQLException {
		Product product = new Product ();
		product.setId(res.getInt("id"));
		product.setCategoryId((res.getInt("categoryId")));
		product.setCode(res.getString("code"));
		product.setDatetime(res.getString("datetime"));
		product.setId(res.getInt("id"));
		product.setImei(res.getString("imei"));
		product.setPrice(res.getInt("price"));
		product.setUserId(res.getInt("userId"));
		return product;
		
	}
	
	public Vector<Product> getProducts(String query){
		Vector<Product> products = new Vector<>();
		
		String search = "select * from product where imei like '%"+query+"%' ";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				products.add(getProductFromResultSet(res));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return products;
	}


	public Merchant getMerchant(int id){
		Merchant merchant = new Merchant ();
		
		String search = "select * from merchant where id=?";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				merchant = getMerchantFromResultSet(res);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return merchant;
	}
	
	private Merchant getMerchantFromResultSet (ResultSet res) throws SQLException  {
		Merchant merchant = new Merchant();
		merchant.setId(res.getInt("id"));
		merchant.setPhoneNumber(res.getString("phoneNumber"));
		merchant.setName(res.getString("name"));
		merchant.setEmail(res.getString("email"));
		return merchant;
	}
	
	public Vector<Merchant> getMerchants(String query){
		Vector<Merchant> merchant = new Vector<>();
		
		String search = "select * from merchant where name like '%"+query+"%' ";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				merchant.add(getMerchantFromResultSet(res));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return merchant;
	}


	public TrustSale getTrustSale(int id){
		TrustSale trustSale = new TrustSale ();
		
		String search = "select * from trustSale where id=?";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				trustSale = getTrustSaleFromResultSet(res);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return trustSale;
	}
	
	private TrustSale getTrustSaleFromResultSet(ResultSet res) throws SQLException {
		TrustSale trustSale = new TrustSale();
		trustSale.setId(res.getInt("id"));
		trustSale.setMerchantId(res.getInt("merchantId"));
		trustSale.setProductId(res.getInt("productId"));
		trustSale.setUserId(res.getInt("UserId"));
		trustSale.setDatetime((res.getString("datetime")));
		trustSale.setComment((res.getString("comment")));
		trustSale.setStatus((res.getInt("status")));
		return trustSale;
	}
	
	public Vector<TrustSale> getTrustSales(Product product){
		Vector<TrustSale> trustSales = new Vector<>();
		
		String search = "select * from trustSale where productId = ? ";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			statement.setInt(1, product.getId());
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				trustSales.add(getTrustSaleFromResultSet(res));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return trustSales;
	}
	
	public Vector<TrustSale> getTrustSales(){
		Vector<TrustSale> trustSales = new Vector<>();
		
		String search = "select * from trustSale";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				trustSales.add(getTrustSaleFromResultSet(res));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return trustSales;
	}
	
	public Vector <User> getUsers(){
		Vector<User> users = new Vector<>();
		String sql = "select * from user";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				users.add(getUserFromResultSet(res));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	
	public boolean updateTrustSale (TrustSale trustSale) {
		boolean result = false;
		String search  = "update trustSale set (status) = (?) where id = (?)";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			statement.setInt(1, trustSale.getStatus());
			statement.setInt(2, trustSale.getId());
			int res = statement.executeUpdate();
			if(res>0) 
				result = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
	
	public boolean updateCustomer (Customer customer) {
		boolean result = false;
		String sql = "update customer set purchases = ? where id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customer.getPurchases());
			statement.setInt(2, customer.getId());
			int res = statement.executeUpdate();
			if(res>0) {
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Customer getCustomer(Customer customer) {
		Customer result = new Customer();
		String validate  = "select * from customer where phoneNumber=? ";
		try {
			PreparedStatement statement = connection.prepareStatement(validate);
			statement.setString(1, customer.getPhoneNumber());
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				result.setEmail(res.getString("email"));
				result.setId(res.getInt("id"));
				result.setPurchases(res.getInt("purchases"));
				result.setName(res.getString("name"));
				result.setPhoneNumber(res.getString("phoneNumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public Customer getCustomer(int id) {
		Customer result = new Customer();
		String validate  = "select * from customer where id=? ";
		try {
			PreparedStatement statement = connection.prepareStatement(validate);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				result.setEmail(res.getString("email"));
				result.setId(res.getInt("id"));
				result.setPurchases(res.getInt("purchases"));
				result.setName(res.getString("name"));
				result.setPhoneNumber(res.getString("phoneNumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	

	public boolean isProductSold(int productId) {
		boolean result = false;
		String search = "select * from sale where productId=?";
		PreparedStatement statement ;
		try {
			statement = connection.prepareStatement(search);
			statement.setInt(1, productId);
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				result = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
	
}