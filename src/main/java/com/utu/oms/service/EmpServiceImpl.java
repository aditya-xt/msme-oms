package com.utu.oms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utu.oms.dao.EmployeeDao;
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

@Service
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public boolean deleteEmployee(Employee employee) {
		return employeeDao.deleteEmployee(employee);
	}

	@Override
	public Employee getEmployee(int empId) {
		return employeeDao.getEmployee(empId);
	}

	@Override
	public boolean emergencySalary(int empId) {
		// TODO Auto-generated method stub
		return employeeDao.emergencySalary(empId);
	}

	@Override
	public boolean leaveRequest(Employee employee, String reason, Date startDate, Date endDate)
			throws InsufficientLeaveException {
		// TODO Auto-generated method stub
		return employeeDao.leaveRequest(employee, reason, startDate, endDate);
	}

	@Override
	public boolean updateStock(StockType sType, int quantity) {
		// TODO Auto-generated method stub
		return employeeDao.updateStock(sType, quantity);
	}

	@Override
	public boolean addPurchase(StockType sType, int quantity) {
		// TODO Auto-generated method stub
		return employeeDao.addPurchase(sType, quantity);
	}

	@Override
	public boolean addSales(SaleItem si, int quantity) {
		// TODO Auto-generated method stub
		return employeeDao.addSales(si, quantity);
	}

	@Override
	public boolean generateSalary() {
		// TODO Auto-generated method stub
		return employeeDao.generateSalary();
	}

	@Override
	public void addAttendance(int empId) {
		// TODO Auto-generated method stub
		employeeDao.addAttendance(empId);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.addEmployee(employee);
	}

	@Override
	public Employee addEmployeeObj(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.addEmployeeObj(employee);
	}

	@Override
	public List<Purchase> viewPurchase() {
		// TODO Auto-generated method stub
		return employeeDao.viewPurchase();
	}

	@Override
	public List<Sales> viewSales() {
		// TODO Auto-generated method stub
		return employeeDao.viewSales();
	}

	@Override
	public List<LeaveReq> viewLeaveReq() {
		// TODO Auto-generated method stub
		return employeeDao.viewLeaveReq();
	}

	@Override
	public boolean adminLogin(String email, String pass) {
		// TODO Auto-generated method stub
		return employeeDao.adminLogin(email, pass);
	}

	@Override
	public boolean empLogin(int empId, String pass, EmpType empType) {
		// TODO Auto-generated method stub
		return employeeDao.empLogin(empId, pass, empType);
	}

	@Override
	public boolean leaveRsp(int srNo, int empId, String startDate, String endDate, LeaveStatus status) {
		// TODO Auto-generated method stub
		return employeeDao.leaveRsp(srNo, empId, startDate, endDate, status);
	}

	@Override
	public Admin getAdmin(String email) {
		// TODO Auto-generated method stub
		return employeeDao.getAdmin(email);
	}

	@Override
	public boolean changePassword(int empId, String newPass) {
		// TODO Auto-generated method stub
		return employeeDao.changePassword(empId, newPass);
	}

	@Override
	public List<Employee> viewEmployee() {
		// TODO Auto-generated method stub
		return employeeDao.viewEmployee();
	}

	@Override
	public boolean setAttendance(int empNo, String empName, Attd attd, Date date) {
		// TODO Auto-generated method stub
		return employeeDao.setAttendance(empNo, empName, attd, date);
	}

	@Override
	public List<Employee> attRemain(List<Employee> employees) {
		// TODO Auto-generated method stub
		return employeeDao.attRemain(employees);
	}

	@Override
	public List<Attendance> viewAttendance() {
		// TODO Auto-generated method stub
		return employeeDao.viewAttendance();
	}

	@Override
	public List<Attendance> viewAttendanceSelf(int empId) {
		// TODO Auto-generated method stub
		return employeeDao.viewAttendanceSelf(empId);
	}

}
