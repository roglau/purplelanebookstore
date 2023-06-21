package view;

import controller.PageController;
import controller.ValidationController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPage extends Application implements EventHandler<ActionEvent>{
	Scene scene;
	BorderPane container, bottomContainer;
	GridPane formcontainer;
	TextField emailTxt;
	PasswordField passTxt;
	Button loginBtn, regisBtn;
	Label title,email,pass,regis;
	Stage curr;

	public void init() {
		
		
		container = new BorderPane();
		scene = new Scene(container,500,400);
		bottomContainer = new BorderPane();
		formcontainer=new GridPane();

		title=new Label("Login Page");
		title.setFont(Font.font(null, FontWeight.BOLD,36));
		
		email=new Label("Email");
		pass=new Label("Password");

		emailTxt=new TextField();
		passTxt=new PasswordField();
		emailTxt.setMaxWidth(150);
		passTxt.setMaxWidth(150);
		
		loginBtn=new Button("Login");
	
		regisBtn = new Button("Register");
		
		loginBtn.setFont(new Font(15));
		regisBtn.setFont(new Font(15));
	}

	void pos() {
		formcontainer.add(email, 0, 0);
		formcontainer.add(emailTxt, 1, 0);
		formcontainer.add(pass, 0, 1);
		formcontainer.add(passTxt, 1, 1);
		formcontainer.setAlignment(Pos.CENTER);
		
		container.setTop(title);
		container.setCenter(formcontainer);
		container.setBottom(bottomContainer);
		bottomContainer.setCenter(loginBtn);
		bottomContainer.setBottom(regisBtn);

		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(bottomContainer, Pos.CENTER);
		BorderPane.setAlignment(regisBtn, Pos.CENTER);
		BorderPane.setMargin(regisBtn, new Insets(10));
		
		
		formcontainer.setVgap(20);
		formcontainer.setHgap(20);
		container.setPadding(new Insets(10));

	}

	public void addAction() {
		loginBtn.setOnAction(this); 
		regisBtn.setOnAction(this);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		curr = arg0;
		init();
		pos();
		addAction();
		arg0.setTitle("Login Page");
		arg0.setScene(scene);
		arg0.setResizable(false);
		arg0.show();
	}

	@Override
	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginBtn) {
			String email= emailTxt.getText();
			String pass= passTxt.getText();

			boolean cek = ValidationController.validateLogin(email,pass);
			
			if(cek) {
				curr.close();
				PageController.userPage();
			}
		}
		else if(e.getSource() == regisBtn) {
			curr.close();
			PageController.regisPage();
		}
	}

}
