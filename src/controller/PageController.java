package controller;

import javafx.stage.Stage;
import view.LoginPage;
import view.RegisterPage;
import view.UserPage;

public class PageController {

	public static void firstPage() {
		// TODO Auto-generated method stub
		LoginPage.launch(LoginPage.class);
	}
	
	public static void loginPage() {
		Stage newst = new Stage();
		
		try {
			new LoginPage().start(newst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void regisPage() {
		Stage newst = new Stage();
		
		try {
			new RegisterPage().start(newst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void userPage() {
		// TODO Auto-generated method stub
		Stage newst = new Stage();
		
		try {
			new UserPage().start(newst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
