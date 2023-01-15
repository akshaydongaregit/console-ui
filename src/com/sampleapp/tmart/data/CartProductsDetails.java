package com.sampleapp.tmart.data;

public class CartProductsDetails {

	private String cartId;
	private String productId;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public CartProductsDetails(String cartId, String productId) {
		super();
		this.cartId = cartId;
		this.productId = productId;
	}
	
	
}
