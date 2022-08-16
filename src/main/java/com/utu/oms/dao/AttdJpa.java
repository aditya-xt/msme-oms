package com.utu.oms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utu.oms.dto.Attendance;

public interface AttdJpa extends JpaRepository<Attendance, Integer> {

	List<Attendance> findByEmpIdEqualsAndDateLike(int empId, String date);
	
	List<Attendance> findByEmpIdEquals(int empId);
}
