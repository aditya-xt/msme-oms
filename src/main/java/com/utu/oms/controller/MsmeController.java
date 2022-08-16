package com.utu.oms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.utu.oms.service.EmpService;

@Controller
public class MsmeController {

	@Autowired
	private EmpService empService;

	static Employee employee = null;
	static Admin admin = null;

//	@RequestMapping(value = "/testingPg")
//	public String f1() {
//		System.out.println("f1() >>> EXECUTED");
//		return "Two";
//	}
//
//	@RequestMapping(value = "/adminLogin")
//	private String loadForm() {
//		System.out.println("loadForm() >>> EXECUTED");
//		return "Adminlog";
//	}

	@RequestMapping(value = "/home")
	private String loadHome() {
		if (admin != null) {
			admin = null;
		}
		if (employee != null) {
			employee = null;
		}
		System.out.println("loadHome() >>> EXECUTED");
		return "home";
	}

	@RequestMapping(value = "/")
	private String loadHome1() {
		if (admin != null) {
			admin = null;
		}
		if (employee != null) {
			employee = null;
		}
		System.out.println("loadHome() >>> EXECUTED");
		return "home";
	}
//	@RequestMapping(value = "/empLogin")
//	public String empLogin() {
//		return "EmpLogin";
//	}

	@RequestMapping(value = "LoginOpt")
	public String getLoginOpt() {
		return "LoginOpt";
	}

	@RequestMapping(value = "Adminlog")
	public String getAdminLog() {
		return "Adminlog";
	}

	@RequestMapping(value = "EmpLogin")
	public String getEmpLogin() {
		return "EmpLogin";
	}

	@RequestMapping(value = "addSales")
	public String getAddSales() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			return "addSales";
		}
		return "home";
	}

	@RequestMapping(value = "addSalesData")
	public String addSalesData(Model data, @RequestParam(value = "saleItem") SaleItem item,
			@RequestParam(value = "quantity") int quantity) {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			if (quantity > 0) {
				empService.addSales(item, quantity);
			}
			return "addSales";
		}
		return "home";
	}

	@RequestMapping(value = "addPurchase")
	public String getAddPurchase() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			return "addPurchase";
		}
		return "home";
	}

	@RequestMapping(value = "addPurchaseData")
	public String addPurchaseData(Model data, @RequestParam(value = "purchaseItem") StockType type,
			@RequestParam(value = "quantity") int quantity) {

		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			if (quantity > 0) {
				empService.addPurchase(type, quantity);
			}
			return "addPurchase";
		}
		return "home";
	}

	@RequestMapping(value = "stockUpdate")
	public String getStockUpdate() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			return "stockUpdate";
		}
		return "home";
	}

	@RequestMapping(value = "/updateStockData")
	public String stockUpdateData(Model data, @RequestParam(value = "quantity1") int q1,
			@RequestParam(value = "quantity2") int q2) {

		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			if ((q1 > 0) && (q2 > 0)) {
				empService.updateStock(StockType.INK, q1);
				empService.updateStock(StockType.PAPER, q2);
			}
			return "stockUpdate";
		}
		return "home";
	}

	@RequestMapping(value = "addAttd")
	public String getAddAttd(Model model) {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			List<Employee> viewEmployee = empService.viewEmployee();
			List<Employee> attRemain = empService.attRemain(viewEmployee);
			model.addAttribute("attRem", attRemain);
			return "addAttd";
		}
		return "home";
	}

	@RequestMapping(value = "viewAttd")
	public String getViewAttd(Model model) {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			List<Attendance> attendance = empService.viewAttendance();
			model.addAttribute("allAttd", attendance);
			
			return "viewAttd";
		}
		return "home";
	}
	
	@RequestMapping(value = "viewAttdSelf")
	public String getViewAttdSelf(Model model) {
		if (employee != null && (employee.getEmpType().equals(EmpType.WORKER))) {
			List<Attendance> attendance = empService.viewAttendanceSelf(employee.getEmpId());
			model.addAttribute("allAttd", attendance);
			
			return "viewAttdSelf"; 
		}
		return "home";
	}

	@RequestMapping(value = "present")
	public String present(Model model, @RequestParam(value = "eid") int id, @RequestParam(value = "ename") String name) {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			Date date = new Date();
			empService.setAttendance(id, name, Attd.P, date);
			List<Employee> viewEmployee = empService.viewEmployee();
			List<Employee> attRemain = empService.attRemain(viewEmployee);
			model.addAttribute("attRem", attRemain);
			return "addAttd";
		}
		return "home";
	}
	
	@RequestMapping(value = "absent")
	public String absent(Model model, @RequestParam(value = "eid") int id, @RequestParam(value = "ename") String name) {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			Date date = new Date();
			empService.setAttendance(id, name, Attd.A, date);
			List<Employee> viewEmployee = empService.viewEmployee();
			List<Employee> attRemain = empService.attRemain(viewEmployee);
			model.addAttribute("attRem", attRemain);
			return "addAttd";
		}
		return "home";
	}

	@RequestMapping(value = "genSalary")
	public String getGenSalary() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			return "genSalary";
		}
		return "home";
	}

	@RequestMapping(value = "emergencySal")
	public String getEmergencySal() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {

			return "emergencySal";
		}
		return "home";
	}

	@RequestMapping(value = "leaveReq")
	public String getLeaveReq() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {

			return "leaveReq";
		}
		return "home";
	}

	@RequestMapping(value = "/reqLeaveData")
	public String reqLeaveData(Model data, @RequestParam(value = "startDate") String stdate,
			@RequestParam(value = "endDate") String edate, @RequestParam(value = "Reason") String reason) {

		if (employee != null) {
			Date endDate = null, startDate = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startDate = dateFormat.parse(stdate);
				endDate = dateFormat.parse(edate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				empService.leaveRequest(employee, reason, startDate, endDate);
			} catch (InsufficientLeaveException e) {
				if (employee.getEmpType().equals(EmpType.ACC)) {
					return "InsufLeave";
				}else if (employee.getEmpType().equals(EmpType.WORKER)) {
					return "InsufLeaveEmp";
				}
			}
			if (employee.getEmpType().equals(EmpType.ACC)) {
				return "leaveReq";
			}else if (employee.getEmpType().equals(EmpType.WORKER)) {
				return "leaveReqEmp";
			}
		}
		return "home";
	}

	@RequestMapping(value = "/updateStock")
	public String getupdateStock() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			return "stockUpdate";
		}
		return "home";
	}
	
	@RequestMapping(value = "/changePass")
	public String getChangePass() {
		if (employee != null) {
			return "changePass";
		}
		return "home";
	}
	
	@RequestMapping(value = "/changePassword")
	public String changePassword(Model model, @RequestParam(value = "opass") String opass, @RequestParam(value = "npass") String npass, @RequestParam(value = "cnpass") String cnpass) {
		if (employee != null) {
			if ((opass.equals(employee.getEmpPass())) && (npass.equals(cnpass))) {
				boolean b = empService.changePassword(employee.getEmpId(), cnpass);
				if (b) {
					return "changePass";
				}else {
					return "tryAgain";
				}
			}
			return "tryAgain";
		} 
		return "home";
	}

	@RequestMapping(value = "AccHome")
	public String getAccHome() {
		if (employee != null && (employee.getEmpType().equals(EmpType.ACC))) {
			return "AccHome";
		}
		return "home";
	}

	@RequestMapping(value = "EmpHome")
	public String getEmpHome() {
		if (employee != null && (employee.getEmpType().equals(EmpType.WORKER))) {
			return "EmpHome";
		}
		return "home";
	}
	
	@RequestMapping(value = "searchEmp")
	public String searchEmp(Model model, @RequestParam(value = "userid") int id) {
		if (admin!=null) {
			Employee employee2 = empService.getEmployee(id);
			if (employee2==null) {
				return "searchEmpNxt";
			}
			model.addAttribute("empData", employee2);
			return "searchEmp";
		}
		return "home";
	}
	
	@RequestMapping(value = "searchEmpNxt")
	public String searchEmpNxt() {
		if (admin!=null) {
			return "searchEmpNxt";
		}
		return "home";
	}

	@RequestMapping(value = "emergencySalEmp")
	public String getEmergencySalEmp() {
		if (employee != null && (employee.getEmpType().equals(EmpType.WORKER))) {
			return "emergencySalEmp";
		}
		return "home";
	}

	@RequestMapping(value = "leaveReqEmp")
	public String getLeaveReqEmp() {
		if (employee != null && (employee.getEmpType().equals(EmpType.WORKER))) {
			return "leaveReqEmp";
		}
		return "home";
	}

	@RequestMapping(value = "/adminLoginValidation")
	public String validating(Model data, @RequestParam("mail") String email, @RequestParam("pass") String pass) {
		boolean adminLogin = empService.adminLogin(email, pass);
		if (adminLogin) {
			admin = empService.getAdmin(email);
			return "AdminHome";
		}
		return "LoginOpt";

	}

	@RequestMapping(value = "/empLoginValidation")
	public String empValidating(Model data, @RequestParam("userid") int id, @RequestParam("pass") String pass,
			@RequestParam("mtype") EmpType type) {
		System.out.println("emp validating() >>> EXECUTED");
		System.out.println(">>>> userid : " + id + "\npass : " + pass + "\nmType : " + type);
		boolean empLogin = empService.empLogin(id, pass, type);
		if (empLogin) {
			if (type.equals(EmpType.ACC)) {
				employee = empService.getEmployee(id);
				return "AccHome";
			} else if (type.equals(EmpType.WORKER)) {
//				System.out.println(">>>> Employee is Worker");
				employee = empService.getEmployee(id);
				return "EmpHome";
			}
		}
		return "LoginOpt";
	}

	@RequestMapping(value = "/addEmp")
	public String addEmployee(Model data, @RequestParam("ename") String name, @RequestParam("email") String email,
			@RequestParam("phone") long phone, @RequestParam("address") String address, @RequestParam("pay") double pay,
			@RequestParam("mtype") EmpType etype) {

		if (admin != null) {
			Employee employee1 = new Employee(name, pay, email, phone, address, etype);
			empService.addEmployee(employee1);
			employee1 = null;
			return "AddEmp";
		}
		return "home";
	}

	@RequestMapping(value = "/updateLeaveD")
	public String updateLeaveD(Model data, @RequestParam("serialNo") int srno, @RequestParam("empId") int empId, @RequestParam("sd") String sd, @RequestParam("ed") String ed) { 

		if (admin != null) { 
			System.out.println("empId"+empId);
			empService.leaveRsp(srno, empId, sd, ed, LeaveStatus.DECLINED);
			List<LeaveReq> reqs = empService.viewLeaveReq();
			data.addAttribute("requests", reqs);
			return "ViewLeaveReq";
		} 
		return "home";
	}
	
	@RequestMapping(value = "/updateLeaveA")
	public String updateLeaveA(Model data, @RequestParam("serialNo") int srno, @RequestParam("empId") int empId, @RequestParam("sd") String sd, @RequestParam("ed") String ed) { 
		
		if (admin != null) { 
			empService.leaveRsp(srno, empId, sd, ed, LeaveStatus.ACCEPTED);
			List<LeaveReq> reqs = empService.viewLeaveReq();
			data.addAttribute("requests", reqs);
			return "ViewLeaveReq";
		} 
		return "home";
	}

	@RequestMapping(value = "/AddEmp")
	public String getAddEmp() {
		if (admin != null) {
			return "AddEmp";
		}
		return "home";
	}

	@RequestMapping(value = "/ViewPurchase")
	public String getViewPurchase(Model model) {
		if (admin != null) {
			List<Purchase> purchases = empService.viewPurchase();
			model.addAttribute("allPurchases", purchases);
			return "ViewPurchase";
		}
		return "home";
	}

	@RequestMapping(value = "/ViewSales")
	public String getViewSales(Model model) {
		if (admin != null) {
			List<Sales> sales = empService.viewSales();
			model.addAttribute("allSales", sales);
			return "ViewSales";
		}
		return "home";
	}

	@RequestMapping(value = "/ViewEmployee")
	public String getViewEmployee(Model model) {
		if (admin != null) {
			List<Employee> viewEmployee = empService.viewEmployee();
			model.addAttribute("allEmp", viewEmployee);
			return "ViewEmployee";
		}

		return "home";
	}

	@RequestMapping(value = "ViewLeaveReq")
	public String getViewLeaveReq(Model model) {
		if (admin != null) {
			List<LeaveReq> reqs = empService.viewLeaveReq();
			model.addAttribute("requests", reqs);
			return "ViewLeaveReq";
		}

		return "home";
	}

	@RequestMapping(value = "/AdminHome")
	public String getAdminHome() {
		if (admin != null) {
			return "AdminHome";
		}
		return "home";
	}

}
