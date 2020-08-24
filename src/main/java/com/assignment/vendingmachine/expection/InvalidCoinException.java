package com.assignment.vendingmachine.expection;

public class InvalidCoinException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3584381351614982240L;

	public InvalidCoinException(String message) {
		super(message);
	}

}
