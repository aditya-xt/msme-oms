package com.utu.oms.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utu.oms.dto.Stock;

public interface StockJpa extends JpaRepository<Stock, Date> {

}
