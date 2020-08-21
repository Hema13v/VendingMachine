package com.assignment.vendingmachine.fixtures;

import com.assignment.vendingmachine.common.CoinEnum;
import com.assignment.vendingmachine.common.CoinStatus;
import com.assignment.vendingmachine.model.Coin;

public class CoinFixture {

	public static Coin createQuarterCoin() {
		return new Coin(CoinEnum.Quarter.getName(), CoinStatus.VALID);
	}

	public static Coin createNickleCoin() {
		return new Coin(CoinEnum.Nickle.getName(), CoinStatus.VALID);
	}

	public static Coin createCentCoin() {
		return new Coin(CoinEnum.Nickle.getName(), CoinStatus.VALID);
	}

	public static Coin createInvalidCoin() {
		return new Coin("Paise", CoinStatus.INVALID);
	}
}
