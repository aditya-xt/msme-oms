package com.utu.oms.dto;

public enum SaleItem {
	CLOTH(150), DIGIPRINT(30) ,FUSING(4);
	
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	SaleItem(int i) {
		this.price=i;
	}
}
