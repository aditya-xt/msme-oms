package com.utu.oms.dto;

public enum StockType {
	INK(60),PAPER(10);
	
	private Integer price;
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	StockType(int i) {
		this.price=i;
	}
}
