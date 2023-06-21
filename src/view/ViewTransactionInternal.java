package view;

import controller.CartController;
import controller.ProductController;
import controller.TransactionController;
import controller.ValidationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jfxtras.labs.scene.control.window.Window;
import model.Product;
import model.Transaction;
import model.User;

public class ViewTransactionInternal extends BorderPane {
	
	public static int idSelected;
	public static UserPage u;
	
	TableView<Transaction> table = new TableView<>();
	
	public void refreshTable(String month, String year) {
		ObservableList<Transaction> data = FXCollections.observableArrayList(TransactionController.getFiltered(month, year)); 
		table.setItems(data);
	}
	
	public ViewTransactionInternal(UserPage userPage) {
		// TODO Auto-generated constructor stub
		u = userPage;
		ObservableList<Transaction> data = null; 
		
		Button filter = new Button("Filter");
		Spinner<Integer> month = new Spinner<>(1,12,1);
		Spinner<Integer> year = new Spinner<>(2000,2023,1);
		
//		TextField month = new TextField();
//		TextField year = new TextField();
//		
//		month.setPromptText("Month ex. 1 2");
//		year.setPromptText("Year ex. 2022 2023");
		
		VBox v = new VBox();
		HBox h = new HBox();
		
		h.getChildren().add(month);
		h.getChildren().add(year);
		
		v.getChildren().add(h);
		v.getChildren().add(filter);
		
		v.setAlignment(Pos.CENTER);
		h.setAlignment(Pos.CENTER);
		
		HBox.setMargin(month, new Insets(5));
		HBox.setMargin(year, new Insets(5));
		
		VBox.setMargin(filter, new Insets(10));
		
		if(User.currUser.getRole().equals("customer")) {
			data = FXCollections.observableArrayList(TransactionController.getTransaction());
		}else if(User.currUser.getRole().equals("manager")) {
			data = FXCollections.observableArrayList(TransactionController.getAllTransaction());
			filter.setOnAction(e ->{
				if(e.getSource()==filter) {
					if(ValidationController.validateFilter(Integer.toString(month.getValue()), Integer.toString(year.getValue()))) {
						refreshTable(Integer.toString(month.getValue()), Integer.toString(year.getValue()));
					}
				}
			});
			setBottom(v);
			
		}
		
		table.setItems(data);
		
		TableColumn<Transaction, Integer> c1= new TableColumn<>("TransactionID");
		c1.setCellValueFactory(new PropertyValueFactory<>("transactionid"));
		c1.setMinWidth(150);
		
		TableColumn<Transaction, String> c2= new TableColumn<>("Payment Type");
		c2.setCellValueFactory(new PropertyValueFactory<>("paymenttype"));
		c2.setMinWidth(150);
		
		TableColumn<Transaction, String> c3= new TableColumn<>("Card Number");
		c3.setCellValueFactory(new PropertyValueFactory<>("cardnumber"));
		c3.setMinWidth(150);
		
		TableColumn<Transaction, String> c4= new TableColumn<>("Promo Code");
		c4.setCellValueFactory(new PropertyValueFactory<>("promocode"));
		c4.setMinWidth(125);
		
		TableColumn<Transaction, String> c5= new TableColumn<>("Transaction Date");
		c5.setCellValueFactory(new PropertyValueFactory<>("date"));
		c5.setMinWidth(175);
		
		TableColumn<Transaction, Button> c6= new TableColumn<>("Detail");
		c6.setCellValueFactory(new PropertyValueFactory<>("btn"));
		c6.setMinWidth(50);
		
		if(User.currUser.getRole().equals("manager")) {
			TableColumn<Transaction, String> c= new TableColumn<>("UserID");
			c.setCellValueFactory(new PropertyValueFactory<>("userid"));
			c.setMinWidth(40);
			table.getColumns().setAll(c,c1,c2,c3,c4,c5,c6);
			table.setMaxWidth(950);
			table.setMaxHeight(950);
		}else {
			table.getColumns().setAll(c1,c2,c3,c4,c5,c6);
			table.setMaxWidth(850);
			table.setMaxHeight(850);
		}
		
		setCenter(table);
		
		
		BorderPane.setAlignment(table, Pos.CENTER);
		
	}
	
	public static void viewDetail() {
		u.pane.getChildren().clear();
		Window w = u.generateWindow("Transaction Details", 1000, 700, new ViewTransactionDetailInternal(u));
		u.pane.getChildren().add(w);
	}
}
