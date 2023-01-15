package com.sampleapp.tmart.data;

import java.util.ArrayList;
import java.util.List;

public class CartDataHandler {

	private static List<Cart> carts;

	private static List<CartProductsDetails> cartsProductDetailsList;

	private static List<Product> cartsProducts;

	
	static {
		mock();
	}
	
	private static void mock() {
		carts = new ArrayList<>();
		carts.add(new Cart("cart1","1"));
		carts.add(new Cart("cart2","2"));
		
		cartsProductDetailsList = new ArrayList<>();
		cartsProductDetailsList.add(new CartProductsDetails("cart1", "1"));
		cartsProductDetailsList.add(new CartProductsDetails("cart1", "2"));
		cartsProductDetailsList.add(new CartProductsDetails("cart2", "1"));
		
		cartsProducts = new ArrayList<>();
		
	}
	
	private List<Product> getCartProductsByUserId(String userId) {
		
		List<Product> products = null;
		//Cart cart = 
		return products;
	}
	
	public List<Product> getcartProducts() {
		return cartsProducts;
	}
	
	public void addProductToCart(Product product) {
		this.cartsProducts.add(product);
	}

}
