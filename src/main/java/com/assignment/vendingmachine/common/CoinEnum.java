package com.assignment.vendingmachine.common;

public enum CoinEnum {
	
//	Cent(1),
//	Nickle(5),
//	Dime(10),
//	Quarter(25),
//	HalfDollar(50);
//	
//	private final Integer cents;
//	
//	Coin(Integer cents) {
//		this.cents = cents;
//	}
//	
//	public Integer getCents() {
//		return cents;
//	}
//	
//	public boolean isCoinValid() {
//		switch(this) {
//		case Cent:
//		case Nickle:
//		case Dime:
//		case Quarter:
//		case HalfDollar:
//			return true;
//		default:
//			return false;
//		}
//	}
	
	Cent("Cent", 1),
	Nickle("Nickle", 5),
	Dime("Dime", 10),
	Quarter("Quarter", 25),
	HalfDollar("HalfDollar", 50);
	
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
