package view;

import controller.CartController;
import controller.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import jfxtras.labs.scene.control.window.Window;
import model.Product;

public class ViewTransactionDetailInternal extends BorderPane{

	public ViewTransactionDetailInternal(UserPage u) {
		// TODO Auto-generated constructor stub
		TableView<Product> table = new TableView<>();
		ObservableList<Product> data= FXCollections.observableArrayList(TransactionController.getDetail());
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
		
		Button back = new Button("Back");
		back.setOnAction(e -> {
			if(e.getSource() == back) {
				u.pane.getChildren().clear();
				Window w = u.generateWindow("Transactions", 1000, 700, new ViewTransactionInternal(u));
				u.pane.getChildren().add(w);
			}
		});
		
		setTop(table);
		setCenter(back);
		BorderPane.setAlignment(table, Pos.CENTER);
	}
}
