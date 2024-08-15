package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Order;
import com.app.entity.Status;
import com.app.entity.User;

public interface OrderDao extends JpaRepository<Order, Long> {
	@Query("select o from Order o left join fetch o.subOrder "
			+ "where o.business.id = :bId and "
			+ "o.status NOT IN (com.app.entity.Status.UNPLACED, com.app.entity.Status.CANCELLED, com.app.entity.Status.DELIVERED)"
			+ "ORDER BY o.updatedOn")
	List<Order> getAllOrdersByBId(Long bId);
	
	@Modifying
	@Query("UPDATE Order o set o.status=:status where o.id=:id")
	int updateOrderStatus(Status status, Long id);
	
	Optional<Order> findByCustomerAndStatus(User customer, Status status);
}
