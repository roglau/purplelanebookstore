package controller;

import java.util.Vector;

import javafx.util.Callback;
import model.Product;
import model.Transaction;
import model.TransactionDetail;
import view.ViewTransactionDetailInternal;
import view.ViewTransactionInternal;

public class TransactionController {
	
	public static void insertTransactionH(String payment, String card, String promo) {
		Transaction.insertTransaction(payment, card, promo);
	}
	
	public static void insertTransactionD() {
		int id = Transaction.getTransactionId();
		
		TransactionDetail.insertTransaction(id);
	}
	
	public static Vector<Transaction> getTransaction() {
		
		return Transaction.getTransaction();
	}
	
	public static Vector<Product>  getDetail(){
		
		return TransactionDetail.getDetail(ViewTransactionInternal.idSelected);
	}

	public static Vector<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return Transaction.getAllTransaction();
	}

	public static Vector<Transaction> getFiltered(String month, String year) {
		// TODO Auto-generated method stub
		return Transaction.getFiltered(month, year);
	}
	
}
