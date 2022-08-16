package com.utu.oms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Sales {
	@Id
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private SaleItem productName;
	private int quantity;
	private double total;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SaleItem getProductName() {
		return productName;
	}

	public void setProductName(SaleItem productName) {
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

	public void setTotal(SaleItem productName, int quantity) {
		this.total =  productName.getPrice()*quantity;
	}

	@Override
	public String toString() {
		return "Sales [date=" + date + ", productName=" + productName + ", quantity=" + quantity + ", total=" + total
				+ "]";
	}

	public Sales(Date date, SaleItem productName, int quantity) {
		this.date = date;
		this.productName = productName;
		this.quantity = quantity;
		this.total = productName.getPrice()*quantity;
	}
	
	public Sales() {}
}
