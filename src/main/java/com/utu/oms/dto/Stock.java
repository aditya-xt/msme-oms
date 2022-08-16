package com.utu.oms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Stock {
	@Id
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private StockType productName;
	private int quantity;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StockType getProductName() {
		return productName;
	}

	public void setProductName(StockType productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Stock(Date date, StockType productName, int quantity) {
		super();
		this.date = date;
		this.productName = productName;
		this.quantity = quantity;
	}

	public Stock() {}

	@Override
	public String toString() {
		return "Stock [date=" + date + ", productName=" + productName + ", quantity=" + quantity + "]";
	}
	
	
	
}
