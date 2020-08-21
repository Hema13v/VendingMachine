package com.assignment.vendingmachine.accessor;

import java.util.ArrayList;
import java.util.stream.Stream;

import com.assignment.vendingmachine.common.CoinEnum;
import com.assignment.vendingmachine.model.Coin;

public class CoinAccessor {
	
	private static final String VALID = "VALID";
	private static final String INVALID = "INVALID";

	public static ArrayList<Coin> validateCoins(String[] coinsArray) {
		ArrayList<Coin> coins = new ArrayList<Coin>();
		for(String coin: coinsArray) {
			if(isValidCoin(coin)) {
				coins.add(new Coin(coin, VALID));
			} else {
				coins.add(new Coin(coin, INVALID));
			}
		}
		return coins;
	}
	
	public static boolean isValidCoin(String value) {
		return Stream.of(CoinEnum.values()).anyMatch(coin -> coin.getName().equals(value));
	}
}
