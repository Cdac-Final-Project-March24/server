package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.SubOrder;

public interface SubOrderDao extends JpaRepository<SubOrder, Long> {

}
