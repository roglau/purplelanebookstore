package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Cart;
import model.Product;
import model.Promo;
import model.User;

public class ValidationController {
	
	public static boolean validateRegis(String name, String email, String pass, String confpass, String gender) {
		if(name.isEmpty()||email.isEmpty()||pass.isEmpty()||confpass.isEmpty()||gender.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		} else if(User.checkEmail(email)) {
			Alert alert = new Alert(AlertType.ERROR,"Email already used");
			alert.showAndWait();
			return false;
		} else if (!email.endsWith(".com")){
			Alert alert = new Alert(AlertType.ERROR,"Email must ends with \".com\"");
			alert.showAndWait();
			return false;
		} else if(!pass.equals(confpass)) {
			Alert alert = new Alert(AlertType.ERROR,"Password must be the same with Confirm password");
			alert.showAndWait();
			return false;
		}
		User.insertUser(name, email, pass, "customer", gender);
		Alert alert = new Alert(AlertType.INFORMATION,"Register Success");
		alert.showAndWait();
		return true;
	}
	
	public static boolean validateLogin(String email, String pass) {
		if(pass.isEmpty()||email.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		}
		User user = User.getUser(email, pass);
		if(user == null) {
			Alert alert = new Alert(AlertType.ERROR,"Wrong email and password");
			alert.showAndWait();
			return false;
		}
		
		User.currUser = user;
		
		return true;
	}
	
	public static boolean validateInsertCart(String productid, String quantity) {
		if(productid.isEmpty() || quantity.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		}
		
		
		if(!Product.checkProduct(Integer.parseInt(productid))) {
			Alert alert = new Alert(AlertType.ERROR,"Wrong productid");
			alert.showAndWait();
			return false;
		}
		int stock = Product.getStock(Integer.parseInt(productid));
		
		if(Integer.parseInt(quantity) < 1) {
			Alert alert = new Alert(AlertType.ERROR,"Quantity must more than 0");
			alert.showAndWait();
			return false;
		}
		
		if(Integer.parseInt(quantity) > stock) {
			Alert alert = new Alert(AlertType.ERROR,"Quantity must not exceed stock");
			alert.showAndWait();
			return false;
		}
		
		if(Cart.checkCart(User.currUser.getId(), Integer.parseInt(productid))) {
			int q = Cart.getQuantity(User.currUser.getId(), Integer.parseInt(productid));
			if(q + Integer.parseInt(quantity) > stock) {
				Alert alert = new Alert(AlertType.ERROR,"Quantity must not exceed stock");
				alert.showAndWait();
				return false;
			}
			
			CartController.updateUserCart(User.currUser.getId(), Integer.parseInt(productid), Integer.parseInt(quantity));
			Alert alert = new Alert(AlertType.INFORMATION,"Product added");
			alert.showAndWait();
			return false;
		}
		
		return true;
	}

	public static boolean validateUpdateCart(String productid, String quantity) {
		// TODO Auto-generated method stub
		if(productid.isEmpty() || quantity.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		}
		
		
		if(!Product.checkProduct(Integer.parseInt(productid))) {
			Alert alert = new Alert(AlertType.ERROR,"Wrong productid");
			alert.showAndWait();
			return false;
		}
		int stock = Product.getStock(Integer.parseInt(productid));
		
		if(Integer.parseInt(quantity) < 1) {
			Alert alert = new Alert(AlertType.ERROR,"Quantity must more than 0");
			alert.showAndWait();
			return false;
		}
		
		if(Integer.parseInt(quantity) > stock) {
			Alert alert = new Alert(AlertType.ERROR,"Quantity must not exceed stock");
			alert.showAndWait();
			return false;
		}
		
		return true;
	}

	public static boolean validateDeleteCart(String productid, String quantity) {

		if(productid.isEmpty() || quantity.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		}
		
		if(!Cart.checkCart(User.currUser.getId(), Integer.parseInt(productid))) {
			return false;
		}
		
		return true;	
	}

	public static boolean validateCheckout(String payment, String card, String promo) {
		
		if(payment.isEmpty() || card.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"Card number must be filled");
			alert.showAndWait();
			return false;
		}
		
		if(card.length() < 16 ) {
			Alert alert = new Alert(AlertType.ERROR,"Card number length must at least 16");
			alert.showAndWait();
			return false;
		}
		
		if(!card.matches("-?\\d+")) {
			Alert alert = new Alert(AlertType.ERROR,"Card number must be numeric");
			alert.showAndWait();
			return false;
		}
		
		if(!promo.isEmpty()) {
			if(!Promo.checkPromo(promo)) {
				Alert alert = new Alert(AlertType.ERROR,"Card number length must at least 16");
				alert.showAndWait();
				return false;
			}			
		}
		
		
		
		return true;
	}

	public static boolean validateFilter(String month, String year) {
		if(month.isEmpty()||year.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		}
		
		return true;
	}

	public static boolean validateHire(String name, String email, String pass, String confpass, String gender,String role) {
		// TODO Auto-generated method stub
		if(name.isEmpty()||email.isEmpty()||pass.isEmpty()||confpass.isEmpty()||gender.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		} else if (!email.endsWith(".com")){
			Alert alert = new Alert(AlertType.ERROR,"Email must ends with \".com\"");
			alert.showAndWait();
			return false;
		} else if(!pass.equals(confpass)) {
			Alert alert = new Alert(AlertType.ERROR,"Password must be the same with Confirm password");
			alert.showAndWait();
			return false;
		}
		
		User.insertUser(name, email, pass, role, gender);
		Alert alert = new Alert(AlertType.INFORMATION,"Hire Success");
		alert.showAndWait();
		return true;
	}

	public static boolean validateInsertProduct(String name, String author, String price, String stock) {
		// TODO Auto-generated method stub
		if(name.isEmpty() || author.isEmpty() || price.isEmpty() || stock.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		}
		
		if(!price.matches("-?\\d+") || !stock.matches("-?\\d+") ) {
			Alert alert = new Alert(AlertType.ERROR,"Price and stock must be numeric");
			alert.showAndWait();
			return false;
		}
		
		if(Integer.parseInt(price) < 1 || Integer.parseInt(stock) < 1) {
			Alert alert = new Alert(AlertType.ERROR,"Price and stock must be more than 0");
			alert.showAndWait();
			return false;
		}
		
		ProductController.insertProduct(name, author, price, stock);
		
		return true;
	}
	
	public static boolean validateUpdateProduct(String id, String name, String author, String price, String stock) {
		if(!Product.checkProduct(Integer.parseInt(id))) {
			Alert alert = new Alert(AlertType.ERROR,"Product id not found");
			alert.showAndWait();
			return false;
		}
		
		if(!price.matches("-?\\d+") || !stock.matches("-?\\d+") ) {
			Alert alert = new Alert(AlertType.ERROR,"Price and stock must be numeric");
			alert.showAndWait();
			return false;
		}
		
		if(Integer.parseInt(price) < 1 || Integer.parseInt(stock) < 1) {
			Alert alert = new Alert(AlertType.ERROR,"Price and stock must be more than 0");
			alert.showAndWait();
			return false;
		}
		
		ProductController.updateProduct(id, name, author, price, stock);
		
		return true;
	}
	
	public static boolean validateDeleteProduct(String id) {
		if(!Product.checkProduct(Integer.parseInt(id))) {
			Alert alert = new Alert(AlertType.ERROR,"Product id not found");
			alert.showAndWait();
			return false;
		}
		ProductController.deleteProduct(id);
		
		return true;
	}

	public static boolean validateDeletePromo(String id) {
		// TODO Auto-generated method stub
		if(!Promo.checkId(id)) {
			Alert alert = new Alert(AlertType.ERROR,"Promo id not found");
			alert.showAndWait();
			return false;
		}
		
		PromoController.deletePromo(id);
		
		return true;
	}

	public static boolean validateUpdatePromo(String id, String code, String discount, String note) {
		// TODO Auto-generated method stub
		if(!Promo.checkId(id)) {
			Alert alert = new Alert(AlertType.ERROR,"Promo id not found");
			alert.showAndWait();
			return false;
		}
		
		if(!discount.matches("-?\\d+")) {
			Alert alert = new Alert(AlertType.ERROR,"Discount must be numeric");
			alert.showAndWait();
			return false;
		}
		
		if(Integer.parseInt(discount) < 15000) {
			Alert alert = new Alert(AlertType.ERROR,"Discount must at least 15000");
			alert.showAndWait();
			return false;
		}
		
		PromoController.updatePromo(id, code, discount, note);
		
		return true;
	}

	public static boolean validateInsertPromo(String code, String discount, String note) {
		// TODO Auto-generated method stub
		if(code.isEmpty() || discount.isEmpty() || note.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR,"All field must be filled");
			alert.showAndWait();
			return false;
		}
		
		if(Promo.checkPromo(code)) {
			Alert alert = new Alert(AlertType.ERROR,"Code already exists");
			alert.showAndWait();
			return false;
		}
		
		if(!discount.matches("-?\\d+")) {
			Alert alert = new Alert(AlertType.ERROR,"Discount must be numeric");
			alert.showAndWait();
			return false;
		}
		
		if(Integer.parseInt(discount) < 15000) {
			Alert alert = new Alert(AlertType.ERROR,"Discount must at least 15000");
			alert.showAndWait();
			return false;
		}
		
		PromoController.insertPromo(code, discount, note);
		
		return true;
	}
	
}
