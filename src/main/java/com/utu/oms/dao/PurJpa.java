package com.utu.oms.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utu.oms.dto.Purchase;

public interface PurJpa extends JpaRepository<Purchase, Date> {

}
