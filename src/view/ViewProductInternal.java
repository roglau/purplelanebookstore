package view;

import controller.CartController;
import controller.ProductController;
import controller.ValidationController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Cart;
import model.Product;
import model.User;

public class ViewProductInternal extends BorderPane{
	
	TableView<Product> table = new TableView<>();
	
	public void refreshTable() {
		ObservableList<Product> data= ProductController.getAll();
		table.setItems(data);
	}
	
	public ViewProductInternal() {
		// TODO Auto-generated constructor stub
		
		ObservableList<Product> data= ProductController.getAll();
		table.setItems(data);
		
		TableColumn<Product, Integer> c1= new TableColumn<>("Id");
		c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		c1.setMinWidth(125);
		
		TableColumn<Product, String> c2= new TableColumn<>("Name");
		c2.setCellValueFactory(new PropertyValueFactory<>("name"));
		c2.setMinWidth(125);
		
		TableColumn<Product, String> c3= new TableColumn<>("Author");
		c3.setCellValueFactory(new PropertyValueFactory<>("author"));
		c3.setMinWidth(125);
		
		TableColumn<Product, Integer> c4= new TableColumn<>("Stock");
		c4.setCellValueFactory(new PropertyValueFactory<>("stock"));
		c4.setMinWidth(125);
		
		TableColumn<Product, Integer> c5= new TableColumn<>("Price");
		c5.setCellValueFactory(new PropertyValueFactory<>("price"));
		c5.setMinWidth(125);
		
		table.getColumns().setAll(c1,c2,c3,c4,c5);
		table.setMaxWidth(625);
		table.setMaxHeight(625);
		
		setTop(table);
		BorderPane.setAlignment(table, Pos.CENTER);
		
//		GridPane gridContainer = new GridPane();
		VBox v= new VBox();
		HBox h1= new HBox();
		HBox h2= new HBox();
		HBox h3= new HBox();
		HBox h4 = new HBox();
		HBox h5 = new HBox();
		HBox h6 = new HBox();
		
		Label idLbl = new Label("Id");
		Label nameLbl = new Label("Name");
		Label quantityLbl = new Label("Quantity");
		Label stockLbl = new Label("Stock");
		Label authorLbl = new Label("Author");
		Label priceLbl = new Label("Price");
		
		idLbl.setMinWidth(100);
		nameLbl.setMinWidth(100);
		quantityLbl.setMinWidth(100);
		stockLbl.setMinWidth(100);
		authorLbl.setMinWidth(100);
		priceLbl.setMinWidth(100);
		
		TextField idTF = new TextField();
		TextField nameTF = new TextField();
		TextField quantityTF = new TextField();
		TextField stockTF = new TextField();
		TextField authorTF = new TextField();
		TextField priceTF = new TextField();
		
		idTF.setMinWidth(125);
		nameTF.setMinWidth(125);
		quantityTF.setMinWidth(125);
		stockTF.setMinWidth(125);
		authorTF.setMinWidth(125);
		priceTF.setMinWidth(125);
		
		h1.getChildren().add(idLbl);
		h1.getChildren().add(idTF);
		h2.getChildren().add(nameLbl);
		h2.getChildren().add(nameTF);
		if(User.currUser.getRole().equals("customer")) {
			
			h3.getChildren().add(quantityLbl);
			h3.getChildren().add(quantityTF);
		}
		
		if(User.currUser.getRole().equals("admin")) {
			h3.getChildren().add(authorLbl);
			h3.getChildren().add(authorTF);
			
			h4.getChildren().add(priceLbl);
			h4.getChildren().add(priceTF);
			
			h5.getChildren().add(stockLbl);
			h5.getChildren().add(stockTF);
			
			h4.setAlignment(Pos.CENTER);
			h5.setAlignment(Pos.CENTER);
		}
		
		v.getChildren().add(h1);
		v.getChildren().add(h2);
		v.getChildren().add(h3);
		
		if(User.currUser.getRole().equals("admin")) {
			v.getChildren().add(h4);
			v.getChildren().add(h5);
		}
		
		setCenter(v);
		h1.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);
		v.setAlignment(Pos.CENTER);
		
		
		BorderPane.setAlignment(v, Pos.CENTER);
		
		table.setOnMouseClicked(e -> {
			 	TableSelectionModel<Product> select = table.getSelectionModel();
			 	select.setSelectionMode(SelectionMode.SINGLE);
			 	Product p = select.getSelectedItem();
			 	
			 	idTF.setText(Integer.valueOf(p.getId()).toString());
			 	nameTF.setText(p.getName());
			 	
			 	if(User.currUser.getRole().equals("admin")) {
			 		authorTF.setText(p.getAuthor());
			 		stockTF.setText(Integer.valueOf(p.getStock()).toString());
			 		priceTF.setText(Integer.valueOf(p.getPrice()).toString());
			 	}
			 	
		});
		
		Button addCart = new Button("Add to cart");
		
		addCart.setOnAction(e -> {
			if(e.getSource() == addCart) {
				if(ValidationController.validateInsertCart(idTF.getText() ,quantityTF.getText())) {
					CartController.insertCart(User.currUser.getId(),Integer.parseInt(idTF.getText()) ,Integer.parseInt(quantityTF.getText()));
					Alert alert = new Alert(AlertType.INFORMATION,"Product added");
					alert.showAndWait();
				}
				
				
			}
		});
		
		if(User.currUser.getRole().equals("customer")) {
			setBottom(addCart);			
		}else if(User.currUser.getRole().equals("admin")) {
			Button add = new Button("Add new product");
			Button update = new Button("Update product");
			Button delete = new Button("Delete product");
			
			h6.getChildren().add(add);
			h6.getChildren().add(update);
			h6.getChildren().add(delete);
			
			HBox.setMargin(add, new Insets(10));
			HBox.setMargin(update, new Insets(10));
			HBox.setMargin(delete, new Insets(10));
			
			
			h6.setAlignment(Pos.CENTER);
			
			v.getChildren().add(h6);
			
			add.setOnAction(e ->{
				if(e.getSource()==add) {
					if(ValidationController.validateInsertProduct(nameTF.getText(), authorTF.getText(),priceTF.getText(), stockTF.getText())) {
						Alert alert = new Alert(AlertType.INFORMATION,"Insert Success");
						alert.showAndWait();
						refreshTable();
					}
				}
			});
			
			update.setOnAction(e -> {
				if(e.getSource() == update) {
					if(ValidationController.validateUpdateProduct(idTF.getText(), nameTF.getText(), authorTF.getText(),priceTF.getText(), stockTF.getText())) {
						Alert alert = new Alert(AlertType.INFORMATION,"Update Success");
						alert.showAndWait();
						refreshTable();
					}
				}
			});
			
			delete.setOnAction(e -> {
				if(e.getSource() == delete) {
					if(ValidationController.validateDeleteProduct(idTF.getText())) {
						Alert alert = new Alert(AlertType.INFORMATION,"Delete Success");
						alert.showAndWait();
						refreshTable();
					}
				}
			});
		}
		
		
		BorderPane.setAlignment(addCart, Pos.CENTER);
	}
	
}
