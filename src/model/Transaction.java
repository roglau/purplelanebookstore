package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;
import javafx.scene.control.Button;
import view.ViewTransactionInternal;

public class Transaction {
	
	private int transactionid, userid;
	private String paymenttype, cardnumber, promocode, date;
	private Button btn;
	
	public static void insertTransaction(String payment, String card, String promo) {
		PreparedStatement ps=Connect.getConnection().prepareStatement("insert into transactionheader (userid,paymenttype,cardnumber,promocode) values (?,?,?,?)");
		try {
			ps.setInt(1, User.currUser.getId());
			ps.setString(2, payment);
			ps.setString(3, card);
			ps.setString(4, promo);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public static int getTransactionId() {
		PreparedStatement ps=Connect.getConnection().prepareStatement("select transactionid from transactionheader ORDER BY transactionid DESC LIMIT 1");
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("transactionid");
				return id;
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Vector<Transaction> getTransaction() {
		// TODO Auto-generated method stub
		Vector<Transaction> th = new Vector<>();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from transactionheader where userid = ? ");
		try {
			ps.setInt(1, User.currUser.getId());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int tid=rs.getInt("transactionid");
				String payment = rs.getString("paymenttype");
				String card = rs.getString("cardnumber");
				String promo = rs.getString("promocode");
				String date = rs.getString("transactiondate");
				
				Transaction t = new Transaction(tid, User.currUser.getId(), payment, card, promo,date);
				th.add(t);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return th;
	}
	
	public Transaction(int transactionid, int userid, String paymenttype, String cardnumber, String promocode, String date) {
		super();
		this.transactionid = transactionid;
		this.userid = userid;
		this.paymenttype = paymenttype;
		this.cardnumber = cardnumber;
		this.promocode = promocode;
		this.date = date;
		this.btn = new Button("Detail");
		this.btn.setOnAction(e -> {
			if (e.getSource()== this.btn) {
				ViewTransactionInternal.idSelected = this.transactionid;
				ViewTransactionInternal.viewDetail();
			}
		});
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getPromocode() {
		return promocode;
	}
	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}

	public Button getBtn() {
		return btn;
	}

	public void setBtn(Button btn) {
		this.btn = btn;
	}

	public static Vector<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		Vector<Transaction> th = new Vector<>();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from transactionheader");
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int tid=rs.getInt("transactionid");
				String payment = rs.getString("paymenttype");
				String card = rs.getString("cardnumber");
				String promo = rs.getString("promocode");
				String date = rs.getString("transactiondate");
				
				Transaction t = new Transaction(tid, User.currUser.getId(), payment, card, promo,date);
				th.add(t);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return th;
	}

	public static Vector<Transaction> getFiltered(String month, String year) {
		// TODO Auto-generated method stub
		Vector<Transaction> th = new Vector<>();
		PreparedStatement ps=Connect.getConnection().prepareStatement("select * from transactionheader where month(transactiondate) = ? and year(transactiondate) = ?");
		try {
			ps.setString(1, month);
			ps.setString(2, year);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int tid=rs.getInt("transactionid");
				String payment = rs.getString("paymenttype");
				String card = rs.getString("cardnumber");
				String promo = rs.getString("promocode");
				String date = rs.getString("transactiondate");
				
				Transaction t = new Transaction(tid, User.currUser.getId(), payment, card, promo,date);
				th.add(t);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return th;
	}

	
	
	
	
}
