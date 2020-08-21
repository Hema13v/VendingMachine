package com.assignment.vendingmachine.common;

public enum CoinEnum {

	Cent("Cent", 1), Nickle("Nickle", 5), Dime("Dime", 10), Quarter("Quarter", 25), HalfDollar("HalfDollar", 50);

	private String name;
	private Integer cents;

	CoinEnum(String name, Integer cents) {
		this.name = name;
		this.cents = cents;
	}

	public String getName() {
		return name;
	}

	public Integer getCents() {
		return cents;
	}

}
