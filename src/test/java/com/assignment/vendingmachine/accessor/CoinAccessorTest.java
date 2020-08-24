package com.assignment.vendingmachine.accessor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.assignment.vendingmachine.model.Coin;

public class CoinAccessorTest {

	@Test
	public void shouldHaveValidCoins() {
		String[] coins = new String[] { "Nickle", "Cent" };
		ArrayList<Coin> coinList = CoinAccessor.mapCoins(coins);
		List<Coin> validCoins = CoinAccessor.validCoins(coinList);
		assertEquals(coinList.size(), 2);
		assertEquals(validCoins.size(), 2);
	}

	@Test
	public void shouldHaveInvalidCoins() {
		String[] coins = new String[] { "Paisa", "Cen" };
		ArrayList<Coin> coinList = CoinAccessor.mapCoins(coins);
		List<Coin> invalidCoins = CoinAccessor.invalidCoins(coinList);
		assertEquals(coinList.size(), 2);
		assertEquals(invalidCoins.size(), 2);
	}

	@Test
	public void shouldCalculateTotalAmountsInCentProperly() {
		String[] coins = new String[] { "Nickle", "Cent" };
		ArrayList<Coin> coinList = CoinAccessor.mapCoins(coins);
		List<Coin> validCoins = CoinAccessor.validCoins(coinList);
		Integer totalAmount = CoinAccessor.totalAmountInCents(validCoins);
		assertEquals(totalAmount, Integer.valueOf(6));
	}

	@Test
	public void shouldCalculateTotalAmountsInCentProperly_with_multiple_coins_of_same_type() {
		String[] coins = new String[] { "Nickle", "Nickle", "Dime", "Dime", "HalfDollar" };
		ArrayList<Coin> coinList = CoinAccessor.mapCoins(coins);
		List<Coin> validCoins = CoinAccessor.validCoins(coinList);
		Integer totalAmount = CoinAccessor.totalAmountInCents(validCoins);
		assertEquals(totalAmount, Integer.valueOf(80));
	}

}
