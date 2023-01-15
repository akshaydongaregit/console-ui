package com.sampleapp.tmart;

import java.util.List;
import java.util.Map;

import com.consoleui.app.Action;
import com.consoleui.app.App;
import com.consoleui.ui.Form;
import com.consoleui.ui.Table;
import com.sampleapp.tmart.data.Product;
import com.sampleapp.tmart.data.ProductDataHandler;

public class TMartApp extends App {

	private static App app = null;
	private static String loggedInUserId = null;
	
	public static void main(String[] args) {
		
		Action showLoginForm = Action.action("show-login-form", (data) -> {
			Form.withId("loginForm")
				.withName("Login To T-Mart")
				.withTextField("username", "Username")
				.withTextField("password", "Password")
				.on("submit", form -> {
						Map<String, Object> value = (Map<String, Object>) form.getValue();
						if (value.get("password").toString().contains("1")) {
							app.act("show-product-list");
						} else {
							form.addError("Incorrect Username/Password.Please try again.");
							form.show();
						}
				})
				.on("clear", form -> {
					app.act("show-login-form");
				}).show();
			
			
		});
		
		Form userCommonActionsForm = Form.withId("user-common-actions-form")
				.on("go to add to cart", (form)-> {
					TMartApp.app.act("go-to-add-to-cart-form");
				})
				.on("view cart", (form)-> {
					TMartApp.app.act("view-user-cart");
				})
				.on("see available products list", (form)-> {
					TMartApp.app.act("show-product-list");
				})
				.on("go to login", (form)-> {
					TMartApp.app.act("show-login-form");
				});
		
		app = App.withDefaultAction(showLoginForm)
				.withAction( Action.action("show-product-list", (data) -> {
					List<Product> products;
					if(data!=null)
						products = (List<Product>) data;
					else
						products = new ProductDataHandler().getProductList();
				
					Form.withId("product-list-form")
					.withName("Available Products")
					.withTable("products-list",new Table<Product>()
							.withColumn("Id", "id", 5)
							.withColumn("Name", "name", 15)
							.withColumn("Description", "desc", 30)
							.withData(products))
					.extendActionsFrom(userCommonActionsForm)
					.show();
				
					})
				  )
				.withAction(Action.action("go-to-add-to-cart-form", (data) -> {
					Form.withId("add-to-cart-form")
					.withName("Add to Cart")
					.withTextField("product-id", "Product Id")
					.on("add slected product to Cart", (form) -> {
						Map<String, Object> values = (Map<String, Object>) form.getValue();
						System.out.println("Added product "+values.get("product-id")+" to cart");
						
						Form.withId("add-to-cart-confirmation")
						.extendActionsFrom(userCommonActionsForm)
						.show();
					})
					.extendActionsFrom(userCommonActionsForm)
					.show();
				}));
		
		app.start();
		
	}
}
