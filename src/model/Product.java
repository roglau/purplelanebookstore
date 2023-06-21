package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
	private String name,author;
	private int id,price,stock;
	
	public static void updProduct(String productid, String name, String author, String price, String stock) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("update products set name = ?, author = ?, price = ?, stock = ? where id = ?");
		try {
			ps.setString(1, name);
			ps.setString(2, author);
			ps.setInt(3, Integer.parseInt(price));
			ps.setInt(4, Integer.parseInt(stock));
			ps.setInt(5, Integer.parseInt(productid));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void delProduct(String productid) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("delete from products where id = ?");
		try {
			ps.setInt(1, Integer.parseInt(productid));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void updateProduct() {
		Vector<Product> products = Cart.getVectorCart(User.currUser.getId());
		
		for (Product p : products) {
			PreparedStatement ps=Connect.getConnection().prepareStatement("update products set stock = stock - ? where id = ?");
			try {
				ps.setInt(1, p.getStock());
				ps.setInt(2, p.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}
		
	}
	
	public static ObservableList<Product> getProducts(){
		ObservableList<Product> products = FXCollections.observableArrayList();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from products");
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name = rs.getString("name");
				String author =rs.getString("author");
				int price =rs.getInt("price");
				int stock = rs.getInt("stock");
				
				Product p = new Product(name, author, id, price, stock);
				products.add(p);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	public static boolean checkProduct(int productid) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from products where id = ?");
		try {
			ps.setInt(1,productid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				return true;
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static int getStock(int productid) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from products where id = ?");
		try {
			ps.setInt(1,productid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int stock = rs.getInt("stock");
				return stock;
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public Product(String name, String author, int id, int price, int stock) {
		super();
		this.name = name;
		this.author = author;
		this.id = id;
		this.price = price;
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public static void insertProduct(String name, String author, String price, String stock) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("insert into products (name, author, price, stock) values (?,?,?,?)");
		try {
			ps.setString(1, name);
			ps.setString(2, author);
			ps.setInt(3, Integer.parseInt(price));
			ps.setInt(4, Integer.parseInt(stock));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}
