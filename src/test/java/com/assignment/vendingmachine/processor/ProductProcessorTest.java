package com.assignment.vendingmachine.processor;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.assignment.vendingmachine.expection.InsufficientFundException;
import com.assignment.vendingmachine.expection.InvalidCoinException;
import com.assignment.vendingmachine.expection.OutOfStockExpection;
import com.assignment.vendingmachine.model.Product;

public class ProductProcessorTest {

	private ProductProcessor productProcessor;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		productProcessor = new ProductProcessor();
	}

	@Test
	public void shouldThrowOutOfStockExpection_if_product_not_in_stock()
			throws OutOfStockExpection, InsufficientFundException, InvalidCoinException {
		String productName = "Limca";
		String coins = "Nickle Cent";
		String expectedMessage = "Requested product: Limca is not available in stock";
		exception.expect(OutOfStockExpection.class);
		exception.expectMessage(expectedMessage);
		productProcessor.initalLoad();
		productProcessor.buyProduct(productName, coins);
	}

	@Test
	public void shouldThrowInvalidCoinException_if_inserted_coin_invalid()
			throws OutOfStockExpection, InsufficientFundException, InvalidCoinException {
		String productName = "Pepsi";
		String coins = "Paisa";
		String expectedMessage = "You have inserted invalid coins, please insert valid coins";
		exception.expect(InvalidCoinException.class);
		exception.expectMessage(expectedMessage);
		productProcessor.initalLoad();
		productProcessor.buyProduct(productName, coins);
	}

	@Test
	public void shouldThrowInsufficentFundsExpection_if_no_coin_inserted()
			throws OutOfStockExpection, InsufficientFundException, InvalidCoinException {
		String productName = "Pepsi";
		String coins = "";
		String expectedMessage = "Insufficent amount, You did not insert coins, Please insert coins";
		exception.expect(InsufficientFundException.class);
		exception.expectMessage(expectedMessage);
		productProcessor.initalLoad();
		productProcessor.buyProduct(productName, coins);
	}

	@Test
	public void shouldThrowInsufficentFundsExpection_if_coins_inserted_amount_less_than_product_price()
			throws OutOfStockExpection, InsufficientFundException, InvalidCoinException {
		String productName = "Pepsi";
		String coins = "Nickle";
		String expectedMessage = "Insufficient amount, You have not insterted required coins";
		exception.expect(InsufficientFundException.class);
		exception.expectMessage(expectedMessage);
		productProcessor.initalLoad();
		productProcessor.buyProduct(productName, coins);
	}

	@Test
	public void shouldShowSucessMessage_if_coins_inserted_amount_equal_to_product_price()
			throws OutOfStockExpection, InsufficientFundException, InvalidCoinException {
		String productName = "DairyMilk";
		String coins = "Dime Dime Nickle";
		String successMessage = "Please take your product: DairyMilk";
		productProcessor.initalLoad();
		productProcessor.buyProduct(productName, coins);
		assertEquals(productProcessor.successMessage, successMessage);
	}

	@Test
	public void shouldShowChangeMessage_if_coins_inserted_amount_greater_than_product_price()
			throws OutOfStockExpection, InsufficientFundException, InvalidCoinException {
		String productName = "DairyMilk";
		String coins = "Dime Dime Nickle Dime Dime";
		String successMessage = "Please take your product: DairyMilk";
		String changeMessage = "Please dont forget to take the change: 4Nickle";
		productProcessor.initalLoad();
		productProcessor.buyProduct(productName, coins);
		assertEquals(productProcessor.successMessage, successMessage);
		assertEquals(productProcessor.changeMessage, changeMessage);
	}

	@Test
	public void shouldUpdate_CashBox_and_Stock_if_successful()
			throws OutOfStockExpection, InsufficientFundException, InvalidCoinException {
		String productName = "DairyMilk";
		String coins = "Dime Dime Nickle";
		String successMessage = "Please take your product: DairyMilk";
		productProcessor.initalLoad();
		productProcessor.buyProduct(productName, coins);

		// check quantity is decreased from stock
		Optional<Product> dairlyMilkProduct = productProcessor.products.stream()
				.filter(product -> product.getName().equals(productName)).findFirst();
		assertEquals(dairlyMilkProduct.get().getQuantity(), Integer.valueOf(19));
		// check cash box is updated
		assertEquals(productProcessor.cashBox.get("Dime"), Integer.valueOf(22));
		assertEquals(productProcessor.cashBox.get("Nickle"), Integer.valueOf(21));
		// check success message
		assertEquals(productProcessor.successMessage, successMessage);
	}

}
