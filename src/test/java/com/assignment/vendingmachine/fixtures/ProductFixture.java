package com.assignment.vendingmachine.fixtures;

import com.assignment.vendingmachine.model.Product;

public class ProductFixture {

	public static Product createProductWithAllValues() {
		return new Product("Coke", 100, 10);
	}

}
