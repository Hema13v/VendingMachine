package com.assignment.vendingmachine.datahandler;

import java.util.HashMap;
import java.util.List;

import com.assignment.vendingmachine.common.CoinEnum;
import com.assignment.vendingmachine.model.Coin;

public class CashBoxHandler {

	public static HashMap<String, Integer> loadCoins(HashMap<String, Integer> cashBox) {
		cashBox.put(CoinEnum.Cent.getName(), 10);
		cashBox.put(CoinEnum.Nickle.getName(), 20);
		cashBox.put(CoinEnum.Dime.getName(), 20);
		cashBox.put(CoinEnum.Quarter.getName(), 50);
		cashBox.put(CoinEnum.HalfDollar.getName(), 10);
		return cashBox;
	}

	public static HashMap<String, Integer> updateCashBox(HashMap<String, Integer> cashBox,
			List<Coin> coinsToBeAdded) {
		for (Coin coin : coinsToBeAdded) {
			if (cashBox.containsKey(coin.getName())) {
				String key = coin.getName();
				cashBox.put(key, cashBox.get(key) + 1);
			}
		}
		return cashBox;
	}
	
	public static Integer numberOfCoins(Integer returnAmount, CoinEnum coin) {
		return returnAmount/coin.getCents();
	}

}
