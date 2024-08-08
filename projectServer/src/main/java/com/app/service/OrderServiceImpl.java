package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ApiException;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.OrderDao;
import com.app.dto.ApiResponse;
import com.app.entity.Order;
import com.app.entity.Status;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;

	@Override
	public List<Order> getAllOrders(Long bId) {
		return orderDao.getAllOrdersByBId(bId);
	}
	
	public ApiResponse updateOrderStatus(Status status, Long id) {
		if(!orderDao.existsById(id))
			throw new ResourceNotFoundException("Invalid order id");
		int rows = orderDao.updateOrderStatus(status, id);
		if(rows == 0)
			throw new ApiException("Failed to update order status");
		return new ApiResponse("Status updated");
	}

}
