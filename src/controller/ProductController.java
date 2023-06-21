package controller;

import javafx.collections.ObservableList;
import model.Product;

public class ProductController {

	public static ObservableList<Product> getAll() {
		// TODO Auto-generated method stub
		return Product.getProducts();
	}
	
	public static void updateCart() {
		Product.updateProduct();
	}

	public static void insertProduct(String name, String author, String price, String stock) {
		// TODO Auto-generated method stub
		Product.insertProduct(name, author, price, stock);
	}

	public static void updateProduct(String id, String name, String author, String price, String stock) {
		// TODO Auto-generated method stub
		Product.updProduct(id, name, author, price, stock);
	}

	public static void deleteProduct(String id) {
		// TODO Auto-generated method stub
		Product.delProduct(id);
	}
	
	
	
}
