package com.assignment.vendingmachine.processor;

import java.util.ArrayList;
import java.util.Scanner;

import com.assignment.vendingmachine.accessor.CoinAccessor;
import com.assignment.vendingmachine.model.Coin;

public class ProductProcessor {
	
	public static void processRequest() {
		
		System.out.println("Welcome to vending machine");
		
		System.out.println("Please enter your favorite product");
		
		Scanner sc = new Scanner(System.in);
		String product = sc.nextLine();
		
		System.out.println("Please enter coins separated by space");
		String coins = sc.nextLine();
		sc.close();
		String[] coinsArray = coins.split(" ");
		ArrayList<Coin> coinList = CoinAccessor.validateCoins(coinsArray);
		coinList.stream().forEach(coin -> System.out.println(coin.getName()+coin.getStatus()));
	}
	
	public static void main(String[] args) {
		processRequest();
	}

}
