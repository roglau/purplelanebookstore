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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterPage extends Application implements EventHandler<ActionEvent>{
	Stage curr;
	Scene scene;
	BorderPane container, bottomContainer;
	FlowPane genderContainer;
	GridPane formcontainer;
	TextField emailTxt, nameTxt;
	RadioButton maleRb, femaleRb;
	ToggleGroup genderTg;
	PasswordField passTxt, confpassTxt;
	Button loginBtn, regisBtn;
	Label title,email,pass,regis, name, gender, confpass;

	public void init() {
		container = new BorderPane();
		scene = new Scene(container,500,400);
		bottomContainer = new BorderPane();
		formcontainer=new GridPane();

		title=new Label("Register Page");
		title.setFont(Font.font(null, FontWeight.BOLD,36));
		
		name = new Label("Name");
		email=new Label("Email");
		pass=new Label("Password");
		gender = new Label ("Gender");
		confpass = new Label("Confirm Password");
		
		nameTxt = new TextField();
		emailTxt=new TextField();
		passTxt=new PasswordField();
		confpassTxt = new PasswordField();
		
		nameTxt.setMaxWidth(150);
		emailTxt.setMaxWidth(150);
		passTxt.setMaxWidth(150);
		confpassTxt.setMaxWidth(150);
		
		genderContainer = new FlowPane();
		maleRb = new RadioButton("Male");
		femaleRb = new RadioButton("Female");
		genderTg = new ToggleGroup();
		
		
		loginBtn=new Button("Login");
	
		regisBtn = new Button("Register");
		
		loginBtn.setFont(new Font(14));
		regisBtn.setFont(new Font(14));
	}

	void pos() {
		formcontainer.add(name, 0, 0);
		formcontainer.add(nameTxt, 1, 0);
		formcontainer.add(email, 0, 1);
		formcontainer.add(emailTxt, 1, 1);
		formcontainer.add(pass, 0, 2);
		formcontainer.add(passTxt, 1, 2);
		formcontainer.add(confpass, 0, 3);
		formcontainer.add(confpassTxt, 1, 3);
		
		genderContainer.setMaxWidth(150);
		
		genderContainer.getChildren().add(maleRb);
		genderContainer.getChildren().add(femaleRb);
		maleRb.setToggleGroup(genderTg);
		femaleRb.setToggleGroup(genderTg);
		
		maleRb.setSelected(true);
		
		formcontainer.add(gender, 0, 4);
		formcontainer.add(genderContainer, 1, 4);
		
		genderContainer.setHgap(10);
		
		formcontainer.setAlignment(Pos.CENTER);
		
		container.setTop(title);
		container.setCenter(formcontainer);
		container.setBottom(bottomContainer);
		bottomContainer.setCenter(regisBtn);
		bottomContainer.setBottom(loginBtn);

		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(bottomContainer, Pos.CENTER);
		BorderPane.setAlignment(loginBtn, Pos.CENTER);
		BorderPane.setMargin(loginBtn, new Insets(10));
		
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
		arg0.setTitle("Register Page");
		arg0.setScene(scene);
		arg0.setResizable(false);
		arg0.show();
	}

	@Override
	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == regisBtn) {
			String name = nameTxt.getText();
			String email= emailTxt.getText();
			String pass= passTxt.getText();
			String confpass = confpassTxt.getText();
			String gender = ((RadioButton)genderTg.getSelectedToggle()).getText();
			
			boolean cek = ValidationController.validateRegis(name,email,pass,confpass,gender);
			
			if(cek) {
				curr.close();
				PageController.loginPage();
			}
			
		}
		else if(e.getSource() == loginBtn) {
			curr.close();
			PageController.loginPage();
		}
	}
}
