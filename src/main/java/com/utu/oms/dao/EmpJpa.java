package com.utu.oms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utu.oms.dto.Employee;

public interface EmpJpa extends JpaRepository<Employee, Integer> {

}
