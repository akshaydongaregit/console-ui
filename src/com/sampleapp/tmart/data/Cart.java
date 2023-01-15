package com.sampleapp.tmart.data;

public class Cart {

	private String cartId;
	private String userId;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Cart(String cartId, String userId) {
		super();
		this.cartId = cartId;
		this.userId = userId;
	}
	
	
}
