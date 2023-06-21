package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class TransactionDetail {
	private int transactionid, productid, quantity;

	public static void insertTransaction(int id) {
		// TODO Auto-generated method stub
		Vector<Product> products = Cart.getVectorCart(User.currUser.getId());
		
		for (Product p : products) {
			PreparedStatement ps=Connect.getConnection().prepareStatement("insert into transactiondetail values (?,?,?)");
			try {
				ps.setInt(1, id);
				ps.setInt(2, p.getId());
				ps.setInt(3, p.getStock());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}
	}
	
	public static Vector<Product> getDetail(int id){
		Vector<Product> products = new Vector<>();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from transactiondetail td join products p where td.productid = p.id and transactionid = ? ");
		try {
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int pid=rs.getInt("id");
				String name = rs.getString("name");
				String author =rs.getString("author");
				int price =rs.getInt("price");
				int q = rs.getInt("quantity");
				
				Product p = new Product(name, author, pid, price, q);
				products.add(p);

			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	public TransactionDetail(int transactionid, int productid, int quantity) {
		super();
		this.transactionid = transactionid;
		this.productid = productid;
		this.quantity = quantity;
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
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

	
	
	
	
}
