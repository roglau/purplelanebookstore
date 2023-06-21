package controller;

import javafx.collections.ObservableList;
import model.Promo;

public class PromoController {

	public static ObservableList<Promo> getAll() {
		// TODO Auto-generated method stub
		return Promo.getPromos();
	}

	public static void insertPromo(String code, String discount, String note) {
		// TODO Auto-generated method stub
		Promo.insertPromo(code, discount, note);
	}

	public static void deletePromo(String id) {
		// TODO Auto-generated method stub
		Promo.deletePromo(id);
	}

	public static void updatePromo(String id, String code, String discount, String note) {
		// TODO Auto-generated method stub
		Promo.updatePromo(id, code, discount, note);
	}
	
}
