package view;

import controller.CartController;
import controller.ProductController;
import controller.TransactionController;
import controller.ValidationController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;
import model.Cart;
import model.Product;
import model.User;

public class ViewCartInternal extends BorderPane{
	TableView<Product> table = new TableView<>();
	
	public void refreshTable() {
		ObservableList<Product> data= CartController.getCart();
		table.setItems(data);
	}
	
	public ViewCartInternal(UserPage userPage) {
		Pane pane = new Pane();
		
		ObservableList<Product> data= CartController.getCart();
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
		
		TableColumn<Product, Integer> c4= new TableColumn<>("Quantity");
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
		
		Label idLbl = new Label("Id");
		Label nameLbl = new Label("Name");
		Label quantityLbl = new Label("Quantity");
		
		Label payment = new Label("Payment Type");
		Label card = new Label("Card Number");
		Label promo = new Label("Promo Code");
		
		payment.setMinWidth(150);
		card.setMinWidth(150);
		promo.setMinWidth(150);
		
		idLbl.setMinWidth(100);
		nameLbl.setMinWidth(100);
		quantityLbl.setMinWidth(100);
		
		TextField idTF = new TextField();
		TextField nameTF = new TextField();
		TextField quantityTF = new TextField();
		TextField cardTF = new TextField();
		TextField promoTF = new TextField();

		idTF.setMinWidth(125);
		nameTF.setMinWidth(125);
		quantityTF.setMinWidth(125);
		cardTF.setMinWidth(125);
		promoTF.setMinWidth(125);
		
		ComboBox<String> box = new ComboBox<>();	
		
		box.getItems().add("Debit");
		box.getItems().add("Credit");
		box.getSelectionModel().selectFirst();
		
		box.setPrefWidth(125);
		
		h1.getChildren().add(idLbl);
		h1.getChildren().add(idTF);
		h2.getChildren().add(nameLbl);
		h2.getChildren().add(nameTF);
		h3.getChildren().add(quantityLbl);
		h3.getChildren().add(quantityTF);
		
		v.getChildren().add(h1);
		v.getChildren().add(h2);
		v.getChildren().add(h3);
		
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
			 	quantityTF.setText(Integer.valueOf(p.getStock()).toString());
			 	
		});
		
		Button update = new Button("Update product");
		Button delete = new Button("Delete product");
		Button checkout = new Button("Checkout cart");
		
		update.setOnAction(e -> {
			if(e.getSource() == update) {
				if(ValidationController.validateUpdateCart(idTF.getText() ,quantityTF.getText())) {
					CartController.updateCart(User.currUser.getId(), Integer.parseInt(idTF.getText()), Integer.parseInt(quantityTF.getText()));
					Alert alert = new Alert(AlertType.INFORMATION,"Update Success");
					alert.showAndWait();
					refreshTable();
					
				}
			}
		});
		
		delete.setOnAction(e -> {
			if(e.getSource()== delete) {
				if(ValidationController.validateDeleteCart(idTF.getText() ,quantityTF.getText())) {
					CartController.deleteCart(User.currUser.getId(), Integer.parseInt(idTF.getText()));
					Alert alert = new Alert(AlertType.INFORMATION,"Delete Success");
					alert.showAndWait();
					refreshTable();
				}
			}
		});
		
		VBox v2 = new VBox();
		HBox h4 = new HBox();
		h4.getChildren().add(update);
		h4.getChildren().add(delete);
		
		v2.getChildren().add(h4);
		v2.getChildren().add(checkout);
		
		v2.setAlignment(Pos.CENTER);
		h4.setAlignment(Pos.CENTER);
		HBox.setMargin(update, new Insets(10));
		HBox.setMargin(delete, new Insets(10));
		setBottom(v2);
		BorderPane.setAlignment(h4, Pos.CENTER);
		
		Button process = new Button("Process Checkout");
		BorderPane.setAlignment(process, Pos.CENTER);
		
		checkout.setOnAction(e -> {
			if(e.getSource() == checkout) {
				if(data.size() == 0) {
					Alert alert = new Alert(AlertType.ERROR,"Cart is empty");
					alert.showAndWait();
				}else {
					h1.getChildren().clear();
					h2.getChildren().clear();
					h3.getChildren().clear();
					
					h1.getChildren().add(payment);
					h1.getChildren().add(box);
					h2.getChildren().add(card);
					h2.getChildren().add(cardTF);
					h3.getChildren().add(promo);
					h3.getChildren().add(promoTF);
					
					h1.setAlignment(Pos.CENTER);
					h2.setAlignment(Pos.CENTER);
					h3.setAlignment(Pos.CENTER);
					v.setAlignment(Pos.CENTER);
					
					BorderPane.setAlignment(v, Pos.CENTER);
					setBottom(process);
				}
			}
		});
		
		process.setOnAction(e -> {
			if(e.getSource() == process) {
				if(ValidationController.validateCheckout(box.getValue() ,cardTF.getText() , promoTF.getText() )){
					ProductController.updateCart();
					TransactionController.insertTransactionH(box.getValue() ,cardTF.getText() , promoTF.getText());
					TransactionController.insertTransactionD();
					Cart.emptyCart(User.currUser.getId());
					
					Alert alert = new Alert(AlertType.INFORMATION,"Checkout Succes");
					alert.showAndWait();
					
					userPage.pane.getChildren().clear();
					Window w = userPage.generateWindow("Transactions", 1000, 700, new ViewTransactionInternal(userPage));
					userPage.pane.getChildren().add(w);
				}
			}
		});
		
	}
	
}
