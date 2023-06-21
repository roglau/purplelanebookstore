package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Promo {
	private int id, discount;
	private String code, note;
	
	public static void insertPromo(String code, String discount, String note) {
		// TODO Auto-generated method stub
		PreparedStatement ps=Connect.getConnection().prepareStatement("insert into promos (code, discount, note) values (?,?,?)");
		try {
			ps.setString(1, code);
			ps.setInt(2, Integer.parseInt(discount));
			ps.setString(3, note);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void updatePromo(String id, String code, String discount, String note) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("update promos set code = ?, discount = ?,  note = ? where id = ?");
		try {
			ps.setString(1, code);
			ps.setInt(2, Integer.parseInt(discount));
			ps.setString(3, note);
			ps.setInt(4, Integer.parseInt(id));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void deletePromo(String id) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("delete from promos where id = ?");
		try {
			ps.setInt(1, Integer.parseInt(id));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static boolean checkId(String id) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from promos where id = ?");
		try {
			ps.setString(1, id);
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
	
	public static boolean checkPromo(String code) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from promos where code = ?");
		try {
			ps.setString(1, code);
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
	
	public Promo(int id, int discount, String code, String note) {
		super();
		this.id = id;
		this.discount = discount;
		this.code = code;
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public static ObservableList<Promo> getPromos() {
		// TODO Auto-generated method stub
		ObservableList<Promo> promos = FXCollections.observableArrayList();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from promos");
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String code = rs.getString("code");
				int discount =rs.getInt("discount");
				String note =rs.getString("note");
				
				Promo p = new Promo(id, discount, code, note);
				promos.add(p);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return promos;

	}
	
}
