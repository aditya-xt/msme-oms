package com.utu.oms.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.utu.oms.dto.Stock;
import com.utu.oms.dto.StockType;
import com.utu.oms.exception.InsufficientLeaveException;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private EmpJpa empJpa;
	
	@Autowired
	private PurJpa purJpa;
	
	@Autowired
	private LeaveJpa leaveJpa;
	
	@Autowired
	private SaleJpa saleJpa;
	
	@Autowired
	private StockJpa stockJpa;
	
	@Autowired
	private AdminJpa adminJpa;
	
	@Autowired
	private AttdJpa attdJpa;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean emergencySalary(int empId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStock(StockType sType, int quantity) {
		Stock stock = new Stock();
		stock.setDate(new Date());
		stock.setProductName(sType);
		stock.setQuantity(quantity);
		Stock stock2 = stockJpa.save(stock);
		boolean check = (stock2!=null);
		return check;
	}

	@Override
	public boolean addPurchase(StockType sType, int quantity) {
		Purchase purchase = new Purchase();
		purchase.setProductName(sType);
		purchase.setQuantity(quantity);
		purchase.setDate(new Date());
		purchase.setTotal(sType, quantity);
		Purchase purchase2 = purJpa.save(purchase);
		boolean check = (purchase2!=null);
		return check;
	}

	@Override
	public boolean addSales(SaleItem si, int quantity) {
		Sales sales = new Sales();
		sales.setProductName(si);
		sales.setQuantity(quantity);
		sales.setDate(new Date());
		sales.setTotal(si, quantity);
		Sales sales2 = saleJpa.save(sales);
		boolean check = (sales2!=null);
		return check;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		System.out.println(">>>> Before Saving");
		Employee employee2 = empJpa.save(employee);
		int empid = employee2.getEmpId();
		System.out.println(">>>> empid : "+empid+"/n>>>> Employee :"+employee2);
		boolean check1 = (employee2!=null);
		System.out.println("check1 : "+check1);
		if (check1) {
			if (this.updatePass(entityManager, empid)) {
				employee2.setEmpPass(Integer.toString(empid));
				return employee2;
			}
		}
		return null;
	}
	
	private boolean updatePass(EntityManager entityManager, int empid) {
		String sql = "Update employee set emp_pass = ?1 where emp_id = ?2";
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, Integer.toString(empid));
		query.setParameter(2, empid);
		int executeUpdate = query.executeUpdate();
		if (executeUpdate>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Employee addEmployeeObj(Employee employee) {
		Employee employee2 = empJpa.save(employee);
		return employee2;
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		empJpa.delete(employee);
		if (empJpa.existsById(employee.getEmpId())) {
			return false;
		}
		return true;
	}

	@Override
	public Employee getEmployee(int empId) {
		
		try {
			Optional<Employee> byId = empJpa.findById(empId);
			Employee employee = byId.get();
			return employee;
		} catch (Exception e) {
			return null;
		}
		
//		Optional<Employee> byId = empJpa.findById(empId);
//		Employee employee = byId.get();
//		return employee;
	}

	@Override
	public boolean leaveRequest(Employee employee, String reason, Date startDate, Date endDate) throws InsufficientLeaveException {
		
//		int diff = (endDate.compareTo(startDate));
		int diff = this.getDiff(startDate, endDate);
		int availLeave = employee.getLeaveAvail();
		System.out.println(diff);
		boolean check1=false,check2,check=false;
		
		if (diff<availLeave) {
			Date date = new Date();
			LeaveReq req = new LeaveReq();
			req.setEmpId(employee.getEmpId());
			req.setEmpName(employee.getEmpName());
			req.setReason(reason);
			req.setStartDate(startDate);
			req.setEndDate(endDate);
			if ((startDate.compareTo(date)>0) && (endDate.compareTo(startDate)>0)) {
				LeaveReq save = leaveJpa.save(req);
				check1 = (save!=null);
			}
			
		}else {
			throw new InsufficientLeaveException("You have not Enough leaves available.");
		}
		return check;
	}
	
//	@Query("update Employee e set e.leaveAvail=: days where e.empId=: id")
	
//	@Query("from Employee e where e.empId =: id")
	
//	@Query("update Employee e set e.leaveAvail = ?1 where e.empId = ?2")
	
	private boolean updateLeave(EntityManager entityManager, int newDays, int empId) {
		String sql = "Update employee set leave_avail = ?1 where emp_id = ?2";
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, newDays);
		query.setParameter(2, empId);
		int executeUpdate = query.executeUpdate();
		System.out.println(">>>> Update method executed");
		System.out.println(">>>> "+ executeUpdate +" rows Updated");
		
		if (executeUpdate>0) {
			return true;
		}
		return false;
	}
	
	private int getDiff(Date startDate, Date endDate) {
		long diffTime = endDate.getTime() - startDate.getTime();
		long diffDays = (diffTime / (1000 * 60 * 60 * 24)) % 365;
		return (int) diffDays+1;
	}

	@Override
	public boolean generateSalary() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addAttendance(int empId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Purchase> viewPurchase() {
		List<Purchase> list = purJpa.findAll();
		return list;
	}

	@Override
	public List<Sales> viewSales() {
		List<Sales> list = saleJpa.findAll();
		return list;
	}

	@Override
	public List<LeaveReq> viewLeaveReq() {
		List<LeaveReq> findByLstLike = leaveJpa.findByLstLike(LeaveStatus.NONE);
		return findByLstLike;
	}

	@Override
	public boolean adminLogin(String email, String pass) {
		try {
			Optional<Admin> byId = adminJpa.findById(email);
			Admin admin = byId.get();
			if (admin.getPassword().equals(pass)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean empLogin(int empId, String pass, EmpType empType) {
		try {
			Optional<Employee> byId = empJpa.findById(empId);
			Employee employee = byId.get();
			if (employee.getEmpPass().equals(pass) && employee.getEmpType().equals(empType)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean leaveRsp(int srNo, int empId, String startDate, String endDate, LeaveStatus status) {
		if (status.equals(LeaveStatus.ACCEPTED)) {
			
			this.updateLeaveSt(entityManager, srNo, status);
			
			Date stDate = this.dateFormater(startDate);
			Date enDate = this.dateFormater(endDate);
			int diff = this.getDiff(stDate, enDate);
			Employee employee = this.getEmployee(empId);
			int newDays = employee.getLeaveAvail()-diff;
			
			boolean updateLeave = this.updateLeave(entityManager, newDays, empId);
			return updateLeave;
			
		}else if (status.equals(LeaveStatus.DECLINED)) {
			boolean updateLeaveSt = this.updateLeaveSt(entityManager, srNo, status);
			return updateLeaveSt;
		}
		return false;
	}
	
	private Date dateFormater(String date) {
		Date stDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stDate;
	}

	private boolean updateLeaveSt(EntityManager entityManager, int srNo, LeaveStatus status) {
		String sql = "Update leave_req set lst = ?1 where sr_no = ?2";
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, status.toString());
		query.setParameter(2, srNo);
		System.out.println(">>>> Status : "+status);
		System.out.println(">>>> sr_no : "+srNo);
		System.out.println(">>>> Query Being Executed");
		int executeUpdate = query.executeUpdate();
		System.out.println(">>>> Query Executed");
		if (executeUpdate>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Admin getAdmin(String email) {
		try {
			Optional<Admin> byId = adminJpa.findById(email);
			Admin admin = byId.get();
			return admin;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean setAttendance(int empNo, String empName, Attd attd, Date date) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(date);
		Attendance attendance = new Attendance(empNo, empName, attd, string);
		Attendance attendance2 = attdJpa.save(attendance);
		if (attendance2!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Attendance> viewAtted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePassword(int empId, String newPass) {
		boolean pass = this.chPass(entityManager, empId, newPass);
		return pass;
	}
	
	private boolean chPass(EntityManager entityManager, int empId, String newPass) {
		String sql = "Update employee set emp_pass = ?1 where emp_id = ?2";
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, newPass);
		query.setParameter(2, empId);
		int executeUpdate = query.executeUpdate();
		System.out.println(">>>> Update method executed");
		System.out.println(">>>> "+ executeUpdate +" rows Updated");
		
		if (executeUpdate>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Employee> viewEmployee() {
		List<Employee> list = empJpa.findAll();
		return list;
	}

	@Override
	public List<Employee> attRemain(List<Employee> employees) {
		List<Employee> remain = new ArrayList<Employee>();
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(date);
		
		
		for (Employee employee : employees) {
			if (this.checkRemain(employee, string)) {
				remain.add(employee);
			}
		}
		return remain;
	}
	
	private boolean checkRemain(Employee employee, String date) {
		List<Attendance> list = attdJpa.findByEmpIdEqualsAndDateLike(employee.getEmpId(), date);
		if (list.size()>0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Attendance> viewAttendance() {
		List<Attendance> list = attdJpa.findAll();
		return list;
	}

	@Override
	public List<Attendance> viewAttendanceSelf(int empId) {
		List<Attendance> list = attdJpa.findByEmpIdEquals(empId);
		return list;
	}

}
