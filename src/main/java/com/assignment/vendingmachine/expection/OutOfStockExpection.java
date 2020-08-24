package com.assignment.vendingmachine.expection;

public class OutOfStockExpection extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7489102213674710971L;

	public OutOfStockExpection(String message) {
		super(message);
	}

}
