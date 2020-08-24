package com.assignment.vendingmachine.datahandler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.assignment.vendingmachine.accessor.CoinAccessor;
import com.assignment.vendingmachine.common.CoinEnum;
import com.assignment.vendingmachine.model.Coin;

public class CashBoxHandlerTest {

	@Test
	public void shouldUpdateCashBoxProperly() {
		HashMap<String, Integer> cashBox = new HashMap<String, Integer>();
		cashBox = CashBoxHandler.loadCoins(cashBox);
		String[] coins = new String[] { "Nickle", "Cent" };
		ArrayList<Coin> coinList = CoinAccessor.mapCoins(coins);
		List<Coin> validCoins = CoinAccessor.validCoins(coinList);
		HashMap<String, Integer> updatedCashBox = CashBoxHandler.updateCashBox(cashBox, validCoins);
		assertEquals(updatedCashBox.get("Nickle"), Integer.valueOf(21));
		assertEquals(updatedCashBox.get("Cent"), Integer.valueOf(11));
	}

	@Test
	public void shouldCalculateNumberOfCoinsProperly() {
		Integer returnAmount = 30;
		Integer numberOfCentsToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Cent);
		Integer numberOfNickleToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Nickle);
		Integer numberOfDimeToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Dime);
		Integer numberOfQuarterToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Quarter);
		Integer numberOfHalfDollarToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.HalfDollar);
		assertEquals(numberOfCentsToReturn, Integer.valueOf(30));
		assertEquals(numberOfNickleToReturn, Integer.valueOf(6));
		assertEquals(numberOfDimeToReturn, Integer.valueOf(3));
		assertEquals(numberOfQuarterToReturn, Integer.valueOf(1));
		assertEquals(numberOfHalfDollarToReturn, Integer.valueOf(0));
	}

}
