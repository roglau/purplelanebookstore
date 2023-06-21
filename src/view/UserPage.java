package view;

import java.util.Optional;

import controller.PageController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;
import model.User;

public class UserPage extends Application implements EventHandler<ActionEvent>{
	Stage curr;
	Scene scene;
	Pane pane;
	BorderPane container;
	MenuBar menuBar;
	Menu userMenu,productMenu, transactionMenu, promoMenu, staffMenu;
	MenuItem logoutItem,viewProductItem, viewCartItem, viewTransactionItem, hireStaffItem, manageProductItem, managePromoItem;
	
	public void init() {
		pane = new Pane();	
		container = new BorderPane();
		scene = new Scene(container,1200,800);
		
		menuBar=new MenuBar();
		
		userMenu=new Menu("User");
		productMenu=new Menu("Product");
		transactionMenu = new Menu("Transaction");
		promoMenu = new Menu("Promo");
		staffMenu = new Menu("Staff");
		
		logoutItem= new MenuItem("Logout");
		viewProductItem= new MenuItem("View all products");
		viewCartItem = new MenuItem("View cart");
		viewTransactionItem = new MenuItem("View transaction");
		hireStaffItem = new MenuItem("Hire new staff");
		manageProductItem = new MenuItem("Manage Product");
		managePromoItem = new MenuItem("Manage Promo");
	}
	
	void pos() {
		userMenu.getItems().add(logoutItem);
		menuBar.getMenus().add(userMenu);
		
		if(User.currUser.getRole().equals("customer")) {
			
			productMenu.getItems().add(viewProductItem);
			
			transactionMenu.getItems().add(viewCartItem);
			transactionMenu.getItems().add(viewTransactionItem);
			
			menuBar.getMenus().add(productMenu);
			menuBar.getMenus().add(transactionMenu);
		}else if(User.currUser.getRole().equals("admin")) {
			productMenu.getItems().add(manageProductItem);
			
			menuBar.getMenus().add(productMenu);
		}else if(User.currUser.getRole().equals("promotion")) {
			promoMenu.getItems().add(managePromoItem);
			
			menuBar.getMenus().add(promoMenu);
		}else {
			staffMenu.getItems().add(hireStaffItem);
			transactionMenu.getItems().add(viewTransactionItem);
			
			menuBar.getMenus().add(staffMenu);
			menuBar.getMenus().add(transactionMenu);
		}
		
		
		container.setTop(menuBar);
		container.setCenter(pane);
	}
	
	void addAction() {
		logoutItem.setOnAction(this);
		viewProductItem.setOnAction(this);
		viewCartItem.setOnAction(this);
		viewTransactionItem.setOnAction(this);
		hireStaffItem.setOnAction(this);
		manageProductItem.setOnAction(this);
		managePromoItem.setOnAction(this);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		curr=arg0;
		init();
		pos();
		addAction();
		arg0.setTitle("User Page");
		arg0.setScene(scene);
		arg0.show();
		
		arg0.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				Alert alert=new Alert(AlertType.CONFIRMATION,"Do you really want to close?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.CANCEL) {
					arg0.consume();
				}
				
			}
		});
	}
	public Window generateWindow(String title,int width,int height,Node node) {
		Window window=new Window(title);
		
		window.getRightIcons().add(new MinimizeIcon(window));
		window.getRightIcons().add(new CloseIcon(window));
		window.setPrefSize(width, height);
		window.setLayoutX(scene.getWidth()/2-width/2);
		window.setLayoutY(scene.getHeight()/2-height/2);
		window.getContentPane().getChildren().add(node);
		return window;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==logoutItem) {
			User.currUser = null;
			curr.close();
			PageController.loginPage();
		}else if(arg0.getSource()==viewProductItem) {
			pane.getChildren().clear();
			Window w = generateWindow("Products", 1000, 700, new ViewProductInternal());
			pane.getChildren().add(w);
		}else if(arg0.getSource() == viewCartItem) {
			pane.getChildren().clear();
			Window w = generateWindow("Carts", 1000, 700, new ViewCartInternal(this));
			pane.getChildren().add(w);
		}else if(arg0.getSource() == viewTransactionItem) {
			pane.getChildren().clear();
			Window w = generateWindow("Transactions", 1000, 700, new ViewTransactionInternal(this));
			pane.getChildren().add(w);
		}else if(arg0.getSource() == hireStaffItem) {
			pane.getChildren().clear();
			Window w = generateWindow("Hire New Staff", 1000, 700, new HireStaffInternal());
			pane.getChildren().add(w);
		}else if(arg0.getSource() == manageProductItem) {
			pane.getChildren().clear();
			Window w = generateWindow("Manage Product", 1000, 700, new ViewProductInternal());
			pane.getChildren().add(w);
		}else if(arg0.getSource() == managePromoItem) {
			pane.getChildren().clear();
			Window w = generateWindow("Manage Promo", 1000, 700, new ViewPromoInternal());
			pane.getChildren().add(w);
		}
	}

}
