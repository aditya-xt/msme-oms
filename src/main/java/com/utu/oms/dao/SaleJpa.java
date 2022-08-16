package com.utu.oms.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utu.oms.dto.Sales;

public interface SaleJpa extends JpaRepository<Sales, Date> {

}
