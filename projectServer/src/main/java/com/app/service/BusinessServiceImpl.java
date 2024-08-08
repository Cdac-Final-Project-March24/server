package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.DuplicateException;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BusinessDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.UpdateBusinessDto;
import com.app.entity.Business;
import com.app.entity.User;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
	@Autowired
	BusinessDao businessDao;
	@Autowired
	UserDao userDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse updateBusiness(UpdateBusinessDto newBusiness, Long bId, Long oId) {
		Business business = mapper.map(newBusiness, Business.class);
		business.setId(bId);
		User owner = mapper.map(newBusiness, User.class);
		owner.setId(oId);
		business.setOwner(owner);
		if(!userDao.existsById(oId)) 
			throw new ResourceNotFoundException("Owner not found");
		if(!businessDao.existsById(bId)) 
			throw new ResourceNotFoundException("Business not found");
		userDao.save(owner);
		businessDao.save(business);
		return new ApiResponse("Updated successfully");
	}

}
