package com.utu.oms.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int srNo;
	private int empId;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Attd status;
	private String date;

	public Attendance() {}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Attd getStatus() {
		return status;
	}

	public void setStatus(Attd status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		date = date;
	}

	public Attendance(int empId, String name, Attd status, String date) {
		this.empId = empId;
		this.name = name;
		this.status = status;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Attendance [srNo=" + srNo + ", empId=" + empId + ", name=" + name + ", status=" + status + ", Date="
				+ date + "]";
	}
	
}
