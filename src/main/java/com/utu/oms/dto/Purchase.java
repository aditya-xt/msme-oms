package com.utu.oms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Purchase {
	
	@Id
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private StockType productName;
	private int quantity;
	private double total;

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

	public double getTotal() {
		return total;
	}

	public void setTotal(StockType productName, int quantity) {
		this.total = productName.getPrice()*quantity;
	}

	public Purchase(Date date, StockType productName, int quantity) {
		this.date = date;
		this.productName = productName;
		this.quantity = quantity;
		this.total = productName.getPrice()*quantity;
	}

	@Override
	public String toString() {
		return "Purchase [date=" + date + ", productName=" + productName + ", quantity=" + quantity + ", total=" + total
				+ "]";
	}
	
	public Purchase() {}
	
	
}
