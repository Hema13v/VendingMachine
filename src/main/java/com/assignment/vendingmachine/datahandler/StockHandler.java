package com.assignment.vendingmachine.datahandler;

import java.util.ArrayList;

import com.assignment.vendingmachine.model.Product;

public class StockHandler {

	public static ArrayList<Product> loadStock(ArrayList<Product> products) {
		products.add(new Product("Coke", 100, 10));
		products.add(new Product("Pepsi", 100, 10));
		products.add(new Product("Mazaa", 50, 10));
		products.add(new Product("Limca", 100, 0));
		products.add(new Product("DairyMilk", 25, 20));
		products.add(new Product("KitKat", 25, 20));
		products.add(new Product("FiveStar", 20, 20));
		products.add(new Product("Snickers", 20, 20));
		return products;
	}
}
