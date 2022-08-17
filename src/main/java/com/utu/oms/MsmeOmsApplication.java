package com.utu.oms;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.utu.oms.dao.LeaveJpa;
import com.utu.oms.dao.PurJpa;
import com.utu.oms.dao.SaleJpa;
import com.utu.oms.dao.StockJpa;
import com.utu.oms.dto.LeaveReq;
import com.utu.oms.dto.LeaveStatus;
import com.utu.oms.dto.Purchase;
import com.utu.oms.dto.SaleItem;
import com.utu.oms.dto.Sales;
import com.utu.oms.dto.Stock;
import com.utu.oms.dto.StockType;
import com.utu.oms.exception.InsufficientLeaveException;
import com.utu.oms.dao.EmployeeDao;
import com.utu.oms.dao.EmployeeDaoImpl;
import com.utu.oms.dto.EmpType;
import com.utu.oms.dto.Employee;

@SpringBootApplication
public class MsmeOmsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MsmeOmsApplication.class, args);
		
		org.springframework.context.ApplicationContext context = SpringApplication.run(MsmeOmsApplication.class, args);
			
//		Employee employee = new Employee("Jani", 875, "mukul@gmail.com", 9975064874L, "U.P.", EmpType.ACC);
//		
//		
//		EmployeeDao dao = context.getBean(EmployeeDaoImpl.class);
//		Employee employeeObj = dao.addEmployee(employee);
//		System.out.println(">>>> Added done");
//		
//		Employee employee = dao.getEmployee(16);
//		System.out.println(employee);
//		
//		String rsn = "Cousins marriage";
//		LocalDate date = LocalDate.of(2022, 3, 20);
//		System.out.println(">>>> Localdate : "+date);
//		Date startDate = Date.from( date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		System.out.println(">>>> Date - startDate : "+startDate);
//		LocalDate date1 = LocalDate.of(2022, 3, 24);
//		System.out.println(">>>> Localdate after 5 days : "+date);
//		Date endDate = Date.from( date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		System.out.println(">>>> Date - endDate : "+endDate);
//		
//		
//		
//		
//		boolean leaveRequest=false;
//		try {
//			leaveRequest = dao.leaveRequest(employee, rsn, startDate, endDate);
//		} catch (InsufficientLeaveException e) {
//			e.printStackTrace();
//		}
//		System.out.println(">>>> Leave req : "+leaveRequest);
//		
		
//		System.out.println(save.getEmpId());
		
//		LocalDate date = LocalDate.now();
//		Date date1 = new Date();
//		Date date2 = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
//		String format = formatter.format(date1);
		
//		Purchase purchase = new Purchase(date1, StockType.PAPER, 200);
//		PurJpa purJpa = context.getBean(PurJpa.class);
//		Purchase purchase2 = purJpa.save(purchase);
//		System.out.println(purchase2);
		
		
//		Sales purchase = new Sales(date1, SaleItem.CLOTH, 20);
//		SaleJpa saleJpa = context.getBean(SaleJpa.class);
//		Sales sales = saleJpa.save(purchase);
//		System.out.println(sales);
		
//		Stock purchase = new Stock(date1, StockType.PAPER, 500);
//		StockJpa saleJpa = context.getBean(StockJpa.class);
//		Stock stock = saleJpa.save(purchase);
//		
//		Date date2 = new Date();
//		Stock purchase1 = new Stock(date2, StockType.INK, 240);
//		Stock stock1 = saleJpa.save(purchase1);
//		Stock purchase2 = new Stock(date1, StockType.INK, 24);
//		Stock stock1 = saleJpa.save(purchase1);
//		System.out.println(stock);
//		System.out.println(stock1);
		
//		LeaveReq req = new LeaveReq(34, "Aditya", "Aise hi. thank you.");
//		req.setLst(LeaveStatus.ACCEPTED);
//		LeaveJpa leaveJpa = context.getBean(LeaveJpa.class);
//		LeaveReq req2 = leaveJpa.save(req);
//		System.out.println(req2);
		
//		LeaveReq req = new LeaveReq(34, "Aditya", "Aise hi. thank you.");
//		req.setLst(LeaveStatus.ACCEPTED);
//		LeaveJpa leaveJpa = context.getBean(LeaveJpa.class);
//		LeaveReq req2 = leaveJpa.save(req);
//		System.out.println(req2);
		
		
	}

}
