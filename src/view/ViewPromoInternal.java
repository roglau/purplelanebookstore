package view;

import controller.ProductController;
import controller.PromoController;
import controller.ValidationController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Product;
import model.Promo;
import model.User;

public class ViewPromoInternal extends BorderPane{

	TableView<Promo> table = new TableView<>();
	
	public void refreshTable() {
		ObservableList<Promo> data= PromoController.getAll();
		table.setItems(data);
	}
	
	public ViewPromoInternal() {
		// TODO Auto-generated constructor stub
		refreshTable();
		TableColumn<Promo, Integer> c1= new TableColumn<>("Id");
		c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		c1.setMinWidth(150);
		
		TableColumn<Promo, String> c2= new TableColumn<>("Code");
		c2.setCellValueFactory(new PropertyValueFactory<>("code"));
		c2.setMinWidth(150);
		
		TableColumn<Promo, Integer> c3= new TableColumn<>("Discount");
		c3.setCellValueFactory(new PropertyValueFactory<>("discount"));
		c3.setMinWidth(150);
		
		TableColumn<Promo, String> c4= new TableColumn<>("Note");
		c4.setCellValueFactory(new PropertyValueFactory<>("note"));
		c4.setMinWidth(200);
		
		table.getColumns().setAll(c1,c2,c3,c4);
		table.setMaxWidth(650);
		table.setMaxHeight(650);
		
		
		setTop(table);
		BorderPane.setAlignment(table, Pos.CENTER);
		
		VBox v= new VBox();
		HBox h1= new HBox();
		HBox h2= new HBox();
		HBox h3= new HBox();
		HBox h4 = new HBox();
		HBox h6 = new HBox();

		Label idLbl = new Label("Id");
		Label codeLbl = new Label("Code");
		Label discountLbl = new Label("Discount");
		Label noteLbl = new Label("Note");
		
		idLbl.setMinWidth(100);
		codeLbl.setMinWidth(100);
		discountLbl.setMinWidth(100);
		noteLbl.setMinWidth(100);
		
		TextField idTF = new TextField();
		TextField codeTF = new TextField();
		TextField discountTF = new TextField();
		TextField noteTF = new TextField();
		
		idTF.setMinWidth(125);
		codeTF.setMinWidth(125);
		discountTF.setMinWidth(125);
		noteTF.setMinWidth(125);
		
		h1.getChildren().add(idLbl);
		h1.getChildren().add(idTF);
		h2.getChildren().add(codeLbl);
		h2.getChildren().add(codeTF);
		h3.getChildren().add(discountLbl);
		h3.getChildren().add(discountTF);
		h4.getChildren().add(noteLbl);
		h4.getChildren().add(noteTF);
		
		v.getChildren().add(h1);
		v.getChildren().add(h2);
		v.getChildren().add(h3);
		v.getChildren().add(h4);
		
		setCenter(v);
		h1.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);
		h4.setAlignment(Pos.CENTER);
		v.setAlignment(Pos.CENTER);
		
		BorderPane.setAlignment(v, Pos.CENTER);
		
		table.setOnMouseClicked(e -> {
			 	TableSelectionModel<Promo> select = table.getSelectionModel();
			 	select.setSelectionMode(SelectionMode.SINGLE);
			 	Promo p = select.getSelectedItem();
			 	
			 	idTF.setText(Integer.valueOf(p.getId()).toString());
			 	codeTF.setText(p.getCode());
			 	discountTF.setText(Integer.valueOf(p.getDiscount()).toString());
			 	noteTF.setText(p.getNote());
			 	
		});
		
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
				if(ValidationController.validateInsertPromo(codeTF.getText(), discountTF.getText(),noteTF.getText())) {
					Alert alert = new Alert(AlertType.INFORMATION,"Insert Success");
					alert.showAndWait();
					refreshTable();
				}
			}
		});
		
		update.setOnAction(e -> {
			if(e.getSource() == update) {
				if(ValidationController.validateUpdatePromo(idTF.getText(), codeTF.getText(), discountTF.getText(),noteTF.getText())) {
					Alert alert = new Alert(AlertType.INFORMATION,"Update Success");
					alert.showAndWait();
					refreshTable();
				}
			}
		});
		
		delete.setOnAction(e -> {
			if(e.getSource() == delete) {
				if(ValidationController.validateDeletePromo(idTF.getText())) {
					Alert alert = new Alert(AlertType.INFORMATION,"Delete Success");
					alert.showAndWait();
					refreshTable();
				}
			}
		});
		
	}
}
