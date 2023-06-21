package controller;

import javafx.collections.ObservableList;
import model.Cart;
import model.Product;
import model.User;

public class CartController {
	
	public static void insertCart(int userid, int productid, int quantity) {
		Cart.insertCart(userid, productid, quantity);
	}
	
	public static void updateUserCart(int userid, int productid, int quantity) {
		Cart.updateUserCart(userid, productid, quantity);
	}
	
	public static void updateCart(int userid, int productid, int quantity) {
		Cart.updateCart(userid, productid, quantity);
	}
	
	public static ObservableList<Product> getCart() {
		// TODO Auto-generated method stub
		return Cart.getCart(User.currUser.getId());
	}

	public static void deleteCart(int userid, int productid) {
		// TODO Auto-generated method stub
		Cart.deleteCart(userid,productid);
	}
	
	public static void emptyCart() {
		Cart.emptyCart(User.currUser.getId());
	}
}
