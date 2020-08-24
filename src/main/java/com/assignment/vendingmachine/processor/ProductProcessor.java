package com.assignment.vendingmachine.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.assignment.vendingmachine.accessor.CoinAccessor;
import com.assignment.vendingmachine.common.CoinEnum;
import com.assignment.vendingmachine.common.CoinStatus;
import com.assignment.vendingmachine.datahandler.CashBoxHandler;
import com.assignment.vendingmachine.datahandler.StockHandler;
import com.assignment.vendingmachine.expection.InsufficientFundException;
import com.assignment.vendingmachine.expection.InvalidCoinException;
import com.assignment.vendingmachine.expection.OutOfStockExpection;
import com.assignment.vendingmachine.model.Coin;
import com.assignment.vendingmachine.model.Product;

public class ProductProcessor {

	ArrayList<Product> products = new ArrayList<Product>();
	HashMap<String, Integer> cashBox = new HashMap<String, Integer>();
	String successMessage;
	String changeMessage;

	public static void main(String[] args) {
		ProductProcessor productProcessor = new ProductProcessor();
		productProcessor.initalLoad();
		productProcessor.processRequest();
	}

	public void initalLoad() {
		products = StockHandler.loadStock(products);
		cashBox = CashBoxHandler.loadCoins(cashBox);
		displayProducts(products);
	}

	public void displayProducts(ArrayList<Product> products) {
		System.out.println("Welcome to vending machine");
		System.out.println("--------------------------------");
		System.out.println("Products available are as below");
		System.out.println("--------------------------------");
		products.stream().forEach(
				product -> System.out.println("Product Name: " + product.getName() + ", Price: " + product.getPrice()));
		System.out.println("--------------------------------");
	}

	public void processRequest() {
		System.out.println("Please enter your favorite product");
		Scanner sc = new Scanner(System.in);
		String productName = sc.nextLine();

		if (productName.isEmpty()) {
			System.out.println("you did not enter product, please enter a product to buy");
			sc.close();
			return;
		}

		System.out.println("Please enter coins separated by space");
		String coins = sc.nextLine();
		sc.close();

		try {
			buyProduct(productName, coins);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void buyProduct(String productName, String coins)
			throws OutOfStockExpection, InvalidCoinException, InsufficientFundException {
		for (Product product : products) {
			if (product.getName().equals(productName) && product.getQuantity() > 0) {
				if (coins.isEmpty()) {
					String insufficentFundMessage = "Insufficent amount, You did not insert coins, Please insert coins";
					throw new InsufficientFundException(insufficentFundMessage);
				}

				String[] coinsArray = coins.split(" ");
				ArrayList<Coin> coinList = CoinAccessor.mapCoins(coinsArray);

				List<Coin> invalidCoins = CoinAccessor.invalidCoins(coinList);
				if (invalidCoins.size() > 0) {
					String invalidCoinMessage = "You have inserted invalid coins, please insert valid coins";
					throw new InvalidCoinException(invalidCoinMessage);
				}

				List<Coin> validCoins = CoinAccessor.validCoins(coinList);
				if (validCoins.size() > 0) {
					Integer totalAmountInCents = CoinAccessor.totalAmountInCents(validCoins);
					if (totalAmountInCents == product.getPrice()) {
						lendProduct(product, validCoins);
					} else if (totalAmountInCents > product.getPrice()) {
						changeCalculator(totalAmountInCents, product, validCoins);
					} else {
						returnInsertedCoins(validCoins);
						String insufficientAmountMessage = "Insufficient amount, You have not insterted required coins";
						throw new InsufficientFundException(insufficientAmountMessage);
					}
				} else {
					String insufficientAmountMessage = "Insufficient amount, You have not insterted required coins";
					throw new InsufficientFundException(insufficientAmountMessage);
				}
				return;
			}
		}
		String outOfStockMessage = "Requested product: " + productName + " is not available in stock";
		throw new OutOfStockExpection(outOfStockMessage);
	}

	private void lendProduct(Product product, List<Coin> validCoins) {
		product.setQuantity(product.getQuantity() - 1);
		cashBox = CashBoxHandler.updateCashBox(cashBox, validCoins);
		successMessage = "Please take your product: " + product.getName();
		System.out.println(successMessage);
		System.out.println("Thank You!");
	}

	private void changeCalculator(Integer totalAmountInCents, Product product, List<Coin> validCoins) {
		Integer returnAmount = totalAmountInCents - product.getPrice();

		Integer numberOfCentsToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Cent);
		String cent = CoinEnum.Cent.getName();
		if (numberOfCentsToReturn != 0 && cashBox.get(cent) >= numberOfCentsToReturn) {
			changeMessage = "Please dont forget to take the change: " + numberOfCentsToReturn + cent;
			System.out.println(changeMessage);

			validCoins = addChangeToValidCoins(validCoins, numberOfCentsToReturn, cent);
			lendProduct(product, validCoins);
			return;
		}

		Integer numberOfNickleToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Nickle);
		String nickle = CoinEnum.Nickle.getName();
		if (numberOfNickleToReturn != 0 && cashBox.get(nickle) >= numberOfNickleToReturn) {
			changeMessage = "Please dont forget to take the change: " + numberOfNickleToReturn + nickle;
			System.out.println(changeMessage);

			validCoins = addChangeToValidCoins(validCoins, numberOfNickleToReturn, nickle);
			lendProduct(product, validCoins);

			return;
		}

		Integer numberOfDimeToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Dime);
		String dime = CoinEnum.Dime.getName();
		if (numberOfDimeToReturn != 0 && cashBox.get(dime) >= numberOfDimeToReturn) {
			changeMessage = "Please dont forget to take the change: " + numberOfDimeToReturn + dime;
			System.out.println(changeMessage);

			validCoins = addChangeToValidCoins(validCoins, numberOfDimeToReturn, dime);
			lendProduct(product, validCoins);
			return;
		}

		Integer numberOfQuarterToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.Quarter);
		String quarter = CoinEnum.Quarter.getName();
		if (numberOfQuarterToReturn != 0 && cashBox.get(quarter) >= numberOfQuarterToReturn) {
			changeMessage = "Please dont forget to take the change" + numberOfQuarterToReturn + quarter;
			System.out.println("Please dont forget to take the change" + numberOfQuarterToReturn + quarter);

			validCoins = addChangeToValidCoins(validCoins, numberOfQuarterToReturn, quarter);
			lendProduct(product, validCoins);
			return;
		}

		Integer numberOfHalfDollarToReturn = CashBoxHandler.numberOfCoins(returnAmount, CoinEnum.HalfDollar);
		String halfDollar = CoinEnum.HalfDollar.getName();
		if (numberOfHalfDollarToReturn != 0 && cashBox.get(halfDollar) >= numberOfQuarterToReturn) {
			changeMessage = "Please dont forget to take the change" + numberOfHalfDollarToReturn + halfDollar;
			System.out.println(changeMessage);

			validCoins = addChangeToValidCoins(validCoins, numberOfHalfDollarToReturn, halfDollar);
			lendProduct(product, validCoins);
			return;
		} else {
			changeMessage = "Sorry, We dont have exact change";
			System.out.println(changeMessage);
			returnInsertedCoins(validCoins);
		}
	}

	private void returnInsertedCoins(List<Coin> coinsInserted) {
		System.out.println("Please take back below coin/'s and try again by inserting required coin/'s");
		coinsInserted.stream().forEach(coin -> System.out.println(coin.getName()));
	}

	private static List<Coin> addChangeToValidCoins(List<Coin> validCoins, Integer numberOfCoins, String coinName) {
		for (int i = 0; i < numberOfCoins; i++) {

			validCoins.add(new Coin(coinName, CoinStatus.VALID));
		}
		return validCoins;
	}

}
