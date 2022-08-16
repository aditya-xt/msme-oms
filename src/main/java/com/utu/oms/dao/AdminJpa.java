package com.utu.oms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utu.oms.dto.Admin;

public interface AdminJpa extends JpaRepository<Admin, String>{
	
}
