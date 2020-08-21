package com.assignment.vendingmachine.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.assignment.vendingmachine.accessor.CoinAccessor;
import com.assignment.vendingmachine.common.CoinStatus;
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
		
		if(coins.isEmpty()) {
			System.out.println("You did not insert coins, Please insert coins to buy product");
			return;
		}
		
		String[] coinsArray = coins.split(" ");
		ArrayList<Coin> coinList = CoinAccessor.mapCoins(coinsArray);
		
		List<Coin> invalidCoins = CoinAccessor.invalidCoins(coinList);
		if(invalidCoins.size() > 0 ) {
			System.out.println("Below coins are rejected, as they are invalid:");
			invalidCoins.stream().forEach(coin -> System.out.println(coin.getName()));
		}
		
		List<Coin> validCoins = CoinAccessor.validCoins(coinList);
		if(validCoins.size() > 0) {
			Integer totalAmount = CoinAccessor.totalAmountInCents(validCoins);
			System.out.println(totalAmount);
		} else {
			System.out.println("Insufficient amount, Please enter valid coins to buy product");
		}
		
	}
	
	public static void main(String[] args) {
		processRequest();
	}

}
