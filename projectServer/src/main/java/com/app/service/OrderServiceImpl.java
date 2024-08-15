package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ApiException;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BusinessDao;
import com.app.dao.OfferingDao;
import com.app.dao.OrderDao;
import com.app.dao.SubOrderDao;
import com.app.dao.UserDao;
import com.app.dto.AddBusinessDto;
import com.app.dto.ApiResponse;
import com.app.dto.OrderDto;
import com.app.dto.SubOrderDto;
import com.app.entity.Business;
import com.app.entity.Offering;
import com.app.entity.Order;
import com.app.entity.Payment;
import com.app.entity.Status;
import com.app.entity.SubOrder;
import com.app.entity.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;
	@Autowired
	SubOrderDao subOrderDao;
	@Autowired
	BusinessDao businessDao;
	@Autowired
	OfferingDao offeringDao;
	@Autowired
	UserDao userDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<OrderDto> getAllOrders(Long bId) {
		return orderDao.getAllOrdersByBId(bId).stream().map(o -> {
					OrderDto dto = mapper.map(o, OrderDto.class);
					dto.setCustomerName(o.getCustomer().getName());
					return dto;
				}).collect(Collectors.toList());
	}
	
	@Override
	public ApiResponse updateOrderStatus(Status status, Long id) {
		if(!orderDao.existsById(id))
			throw new ResourceNotFoundException("Invalid order id");
		int rows = orderDao.updateOrderStatus(status, id);
		if(rows == 0)
			throw new ApiException("Failed to update order status");
		return new ApiResponse("Status updated");
	}
	
	@Override
	public ApiResponse addToCart(String email, Long businessId, Long offeringId) {
		Business business = businessDao.findById(businessId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Business Id"));
		User customer = userDao.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("Invalid User Id"));
		Offering offering = offeringDao.findById(offeringId)				
				.orElseThrow(()->new ResourceNotFoundException("Invalid Offering Id"));
		Order order = orderDao.findByCustomerAndStatus(customer, Status.UNPLACED).orElse(null);
		SubOrder sub = subOrderDao.findByOrderAndOffering(order, offering)
				.orElse(new SubOrder(order, offering, 1));
		
		if(order == null) {
			order = orderDao.save(new Order(customer, business, Status.UNPLACED));
			order.addSubOrder(sub);
		}
		else if(!order.getBusiness().equals(business)) {
			order.setStatus(Status.CANCELLED); // If new business added then cancel current cart
			order = orderDao.save(new Order(customer, business, Status.UNPLACED));
			order.addSubOrder(sub);
		}
		else if(sub.getId() == null) {
			order.addSubOrder(sub);
		}
		else sub.setQuantity(sub.getQuantity()+1);
		
		return new ApiResponse("Added to Cart");
	}

	@Override
	public ApiResponse placeOrder(Payment payment, Long id) {
		Order order = orderDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid order id"));
		order.setPayment(payment);
		order.setAddress(order.getCustomer().getAddress());
		order.setStatus(Status.PENDING);
		return new ApiResponse("Order placed successfully");
	}

	@Override
	public OrderDto getCart(String email) {
		User customer = userDao.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("Invalid User Id"));
		Order order = orderDao.findByCustomerAndStatus(customer, Status.UNPLACED)
				.orElseThrow(()-> new ResourceNotFoundException("Cart is empty"));
		order.getSubOrder().size();
		OrderDto orderDto = mapper.map(order, OrderDto.class);
//		order.getSubOrder().forEach(s -> orderDto.getSubOrder().add(mapper.map(s, SubOrderDto.class)));
//		orderDto.setBusiness(mapper.map(order.getBusiness(), AddBusinessDto.class));
		orderDto.setCustomerName(order.getCustomer().getName());
		return orderDto;
	}
}
