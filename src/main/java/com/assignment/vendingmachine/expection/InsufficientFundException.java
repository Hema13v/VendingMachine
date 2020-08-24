package com.assignment.vendingmachine.expection;

public class InsufficientFundException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7703221038027027077L;

	public InsufficientFundException(String message) {
		super(message);
	}

}
