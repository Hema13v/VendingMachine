package com.assignment.vendingmachine.accessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.assignment.vendingmachine.common.CoinEnum;
import com.assignment.vendingmachine.common.CoinStatus;
import com.assignment.vendingmachine.model.Coin;

public class CoinAccessor {

	public static ArrayList<Coin> mapCoins(String[] coinsArray) {
		ArrayList<Coin> coins = new ArrayList<Coin>();
		for(String coin: coinsArray) {
			if(isValidCoin(coin)) {
				coins.add(new Coin(coin, CoinStatus.VALID));
			} else {
				coins.add(new Coin(coin, CoinStatus.INVALID));
			}
		}
		return coins;
	}
	
	public static List<Coin> validCoins(List<Coin> coins) {
		return coins.stream().filter(coin -> CoinStatus.VALID.equals(coin.getStatus())).collect(Collectors.toList());
	}
	
	public static List<Coin> invalidCoins(List<Coin> coins) {
		return coins.stream().filter(coin -> CoinStatus.INVALID.equals(coin.getStatus())).collect(Collectors.toList());
	}
	
	public static Integer totalAmountInCents(List<Coin> validCoins) {
		return validCoins.stream()
				.map(coin -> getCoinsValueinCents(coin.getName()))
				.reduce(0, (first, second) -> first + second);
	}
	
	private static boolean isValidCoin(String value) {
		return Stream.of(CoinEnum.values()).anyMatch(coin -> coin.getName().equals(value));
	}
	
	private static Integer getCoinsValueinCents(String value) {
		switch(value) {
			case "Cent":
				return CoinEnum.Cent.getCents();
			case "Nickle":
				return CoinEnum.Nickle.getCents();
			case "Dime":
				return CoinEnum.Dime.getCents();
			case "Quarter":
				return CoinEnum.Quarter.getCents();
			case "HalfDollar":
				return CoinEnum.HalfDollar.getCents();
			default:
				return 0;
		}
	}
}
