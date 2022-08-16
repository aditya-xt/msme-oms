package com.utu.oms.dao;

import java.util.Date;
import java.util.List;

import com.utu.oms.dto.Admin;
import com.utu.oms.dto.Attd;
import com.utu.oms.dto.Attendance;
import com.utu.oms.dto.EmpType;
import com.utu.oms.dto.Employee;
import com.utu.oms.dto.LeaveReq;
import com.utu.oms.dto.LeaveStatus;
import com.utu.oms.dto.Purchase;
import com.utu.oms.dto.SaleItem;
import com.utu.oms.dto.Sales;
import com.utu.oms.dto.StockType;
import com.utu.oms.exception.InsufficientLeaveException;

public interface EmployeeDao {
	
	public boolean deleteEmployee(Employee employee);
	
	public Employee getEmployee(int empId);
	
	public boolean emergencySalary(int empId); 
	
	public boolean leaveRequest(Employee employee, String reason, Date startDate, Date endDate) throws InsufficientLeaveException;
	
	public boolean updateStock(StockType sType,int quantity);
	
//	public int viewAttendance();
//	public boolean attendanceEntry();
	
	public boolean addPurchase(StockType sType, int quantity);
	
	public boolean addSales(SaleItem si, int quantity);
	
	public boolean generateSalary();
	
	public void addAttendance(int empId);
	
	// Admin methods ---------------------------
	
	public Employee addEmployee(Employee employee);
	
	public Employee addEmployeeObj(Employee employee);
	
	public List<Purchase> viewPurchase();
	
	public List<Sales> viewSales();
	
	public List<LeaveReq> viewLeaveReq();
	
	public boolean adminLogin(String email, String pass);
	
	public boolean empLogin(int empId, String pass, EmpType empType);
	
	public boolean leaveRsp(int srNo, int empId, String startDate, String endDate, LeaveStatus status);
	
	// ------------------------------------------
	
	public Admin getAdmin(String email);
	
	
	
	// ===================================================
	
	public boolean setAttendance(int empNo, String empName, Attd attd, Date date);
	
	public List<Attendance> viewAtted();
	
	public boolean changePassword(int empId, String newPass);
	
	public List<Employee> viewEmployee();
	
	public List<Employee> attRemain(List<Employee> employees);
	
	public List<Attendance> viewAttendance();
	
	public List<Attendance> viewAttendanceSelf(int empId);
	
	
	
	
}
