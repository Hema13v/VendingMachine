package com.assignment.vendingmachine.processor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.assignment.vendingmachine.accessor.CoinAccessor;
import com.assignment.vendingmachine.model.Coin;

public class ProductProcessorTest {

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

}
