package com.utu.oms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utu.oms.dto.LeaveReq;
import com.utu.oms.dto.LeaveStatus;

public interface LeaveJpa extends JpaRepository<LeaveReq, Integer> {
	List<LeaveReq> findByLstLike(LeaveStatus status);
}
