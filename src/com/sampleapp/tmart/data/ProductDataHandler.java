package com.sampleapp.tmart.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductDataHandler {

	static List<Product> products = new ArrayList<Product>();

	public List<Product> getProductList() {
		return products;
	}

	public static Product getProductById(String id) {
		Product product = products.stream().filter(p -> p.getId().equals(id))
				.collect(Collectors.toList()).get(0);
		return product;
	}

	static {
		mock();
	}

	private static void mock() {
		products.add(new Product("1", "Nokia Mobile", "Nokia Android Phone"));
		products.add(new Product("2", "Hp Laptop", "HP Pavilion Laptop"));
		products.add(new Product("3", "Trimmer", "Trimmer"));
	}

}
