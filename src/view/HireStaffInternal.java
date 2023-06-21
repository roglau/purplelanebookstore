package view;

import controller.ValidationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HireStaffInternal extends BorderPane{

	public HireStaffInternal() {
		// TODO Auto-generated constructor stub
		TextField emailTxt, nameTxt;
		RadioButton maleRb, femaleRb;
		ToggleGroup genderTg;
		PasswordField passTxt, confpassTxt;
		ComboBox<String> roleBox = new ComboBox<>();
		
		Label email,pass,regis, name, gender, confpass, role;
		
		name = new Label("Name");
		email=new Label("Email");
		pass=new Label("Password");
		gender = new Label ("Gender");
		confpass = new Label("Confirm Password");
		role = new Label("Role");
		
		name.setMinWidth(150);
		email.setMinWidth(150);
		pass.setMinWidth(150);
		gender.setMinWidth(150);
		confpass.setMinWidth(150);
		role.setMinWidth(150);
		
		nameTxt = new TextField();
		emailTxt=new TextField();
		passTxt=new PasswordField();
		confpassTxt = new PasswordField();
		
		nameTxt.setMinWidth(150);
		emailTxt.setMinWidth(150);
		passTxt.setMinWidth(150);
		confpassTxt.setMinWidth(150);
		
		maleRb = new RadioButton("Male");
		femaleRb = new RadioButton("Female");
		genderTg = new ToggleGroup();
		maleRb.setToggleGroup(genderTg);
		femaleRb.setToggleGroup(genderTg);
		maleRb.setSelected(true);
		
		roleBox.getItems().add("promotion");
		roleBox.getItems().add("admin");
		roleBox.getSelectionModel().selectFirst();
		
		HBox h = new HBox();
		h.getChildren().add(maleRb);
		h.getChildren().add(femaleRb);
		h.setMinWidth(150);
		
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		HBox h4 = new HBox();
		HBox h5 = new HBox();
		HBox h6 = new HBox();
		HBox h7 = new HBox();
		
		h2.getChildren().add(name);
		h2.getChildren().add(nameTxt);
		
		h3.getChildren().add(email);
		h3.getChildren().add(emailTxt);
		
		h4.getChildren().add(pass);
		h4.getChildren().add(passTxt);
		
		h5.getChildren().add(confpass);
		h5.getChildren().add(confpassTxt);
		
		h6.getChildren().add(gender);
		h6.getChildren().add(h);
		
		h7.getChildren().add(role);
		h7.getChildren().add(roleBox);
		
		VBox v = new VBox();
		
		v.getChildren().add(h2);
		v.getChildren().add(h3);
		v.getChildren().add(h4);
		v.getChildren().add(h5);
		v.getChildren().add(h6);
		v.getChildren().add(h7);
		
		setCenter(v);
		
		h.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);
		h4.setAlignment(Pos.CENTER);
		h5.setAlignment(Pos.CENTER);
		h6.setAlignment(Pos.CENTER);
		h7.setAlignment(Pos.CENTER);
		v.setAlignment(Pos.CENTER);
		
		
		BorderPane.setAlignment(v, Pos.CENTER);
		
		
		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			if(e.getSource() == submit) {
				String names = nameTxt.getText();
				String emails= emailTxt.getText();
				String passs= passTxt.getText();
				String confpasss = confpassTxt.getText();
				String genders = ((RadioButton)genderTg.getSelectedToggle()).getText();
				String roles = roleBox.getValue();
				if(ValidationController.validateHire(names, emails, passs, confpasss, genders, roles)) {
					nameTxt.setText("");
					emailTxt.setText("");
					passTxt.setText("");
					confpassTxt.setText("");
					maleRb.setSelected(true);
					roleBox.getSelectionModel().selectFirst();
				}
			}
		});
		
		v.getChildren().add(submit);
		
		VBox.setMargin(submit	, new Insets(30));
	}
}
