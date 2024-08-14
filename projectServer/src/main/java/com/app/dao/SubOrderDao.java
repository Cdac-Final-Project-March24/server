package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Offering;
import com.app.entity.Order;
import com.app.entity.SubOrder;

public interface SubOrderDao extends JpaRepository<SubOrder, Long> {
	Optional<SubOrder> findByOrderAndOffering(Order order, Offering offering);
}
