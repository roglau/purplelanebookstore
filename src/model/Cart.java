package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
	
	private int userid, productid, quantity;
	
	public static int getQuantity(int userid, int productid) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from carts where userid = ? and productid = ?");
		try {
			ps.setInt(1, userid);
			ps.setInt(2,productid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int q = rs.getInt("quantity");
				return q;
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	public static boolean checkCart(int userid, int productid) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from carts where userid = ? and productid = ?");
		try {
			ps.setInt(1, userid);
			ps.setInt(2,productid);
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
	
	public static void insertCart(int userid, int productid, int quantity) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("insert into carts (userid, productid, quantity) values (?,?,?)");
		try {
			ps.setInt(1, userid);
			ps.setInt(2, productid);
			ps.setInt(3, quantity);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void updateUserCart(int userid, int productid, int quantity) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("update carts set quantity = quantity + ? where userid = ? and productid = ?");
		try {
			ps.setInt(1, quantity);
			ps.setInt(2, userid);
			ps.setInt(3, productid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public static ObservableList<Product> getCart(int userid) {
		ObservableList<Product> products = FXCollections.observableArrayList();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from carts c join products p on c.productid = p.id where userid = ? ");
		try {
			ps.setInt(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name = rs.getString("name");
				String author =rs.getString("author");
				int price =rs.getInt("price");
				int q = rs.getInt("quantity");
				
				Product p = new Product(name, author, id, price, q);
				products.add(p);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	public static Vector<Product> getVectorCart(int userid) {
		Vector<Product> products = new Vector<Product>();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from carts c join products p on c.productid = p.id where userid = ? ");
		try {
			ps.setInt(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name = rs.getString("name");
				String author =rs.getString("author");
				int price =rs.getInt("price");
				int q = rs.getInt("quantity");
				
				Product p = new Product(name, author, id, price, q);
				products.add(p);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	public Cart(int userid, int productid, int quantity) {
		super();
		this.userid = userid;
		this.productid = productid;
		this.quantity = quantity;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static void updateCart(int userid, int productid, int quantity) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("update carts set quantity = ? where userid = ? and productid = ?");
		try {
			ps.setInt(1, quantity);
			ps.setInt(2, userid);
			ps.setInt(3, productid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void deleteCart(int userid, int productid) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("delete from carts where userid = ? and productid = ?");
		try {
			ps.setInt(1, userid);
			ps.setInt(2, productid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	public static void emptyCart(int userid) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("delete from carts where userid = ?");
		try {
			ps.setInt(1, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}



	
	
	
	
}
