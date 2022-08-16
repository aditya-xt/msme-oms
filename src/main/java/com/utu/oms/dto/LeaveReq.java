package com.utu.oms.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LeaveReq {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int srNo;
	private int empId;
	private String empName;
	private String reason;
	private Date startDate;
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	private LeaveStatus lst = LeaveStatus.NONE;

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getLst() {
		return lst;
	}

	public void setLst(LeaveStatus lst) {
		this.lst = lst;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LeaveReq(int empId, String empName, String reason, Date startDate, Date endDate) {
		this.empId = empId;
		this.empName = empName;
		this.reason = reason;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "LeaveReq [srNo=" + srNo + ", empId=" + empId + ", empName=" + empName + ", reason=" + reason
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", lst=" + lst + "]";
	}

	public LeaveReq() {}
	
}
