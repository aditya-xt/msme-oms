package com.utu.oms.dto;

public enum Attd {
	P(1),A(0);
	
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	Attd(int i) {
		this.value=i;
	}
}
