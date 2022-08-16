package com.utu.oms.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	
	private String empName;
	private String empPass;
	private double pay;
	private int leaveAvail=18;
	private String email;
	private long phoneNo;
	private String address;
	
	@Enumerated(EnumType.STRING)
	private EmpType empType;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPass() {
		return empPass;
	}

	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public int getLeaveAvail() {
		return leaveAvail;
	}

	public void setLeaveAvail(int leaveAvail) {
		this.leaveAvail = leaveAvail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmpType getEmpType() {
		return empType;
	}

	public void setEmpType(EmpType empType) {
		this.empType = empType;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empPass=" + empPass + ", pay=" + pay
				+ ", leaveAvail=" + leaveAvail + ", email=" + email + ", phoneNo=" + phoneNo + ", address=" + address
				+ ", empType=" + empType + "]";
	}
	
	public Employee(String empName, double pay, String email, long phoneNo, String address, EmpType empType) {
		this.empName = empName;
		this.pay = pay;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.empType = empType;
	}

	public Employee() {}
}
