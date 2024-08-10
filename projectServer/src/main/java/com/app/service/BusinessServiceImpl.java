package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BusinessDao;
import com.app.dao.UserDao;
import com.app.dto.AddBusinessDto;
import com.app.dto.ApiResponse;
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
	public ApiResponse updateBusiness(AddBusinessDto newBusiness, Long bId) {
		Business orignalBusiness = businessDao.findById(bId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Business Id"));
		mapper.map(newBusiness, orignalBusiness);
		return new ApiResponse("Updated successfully");
	}

	@Override
	public AddBusinessDto addBusiness(AddBusinessDto newBusiness, Long oId) {
		Business business = mapper.map(newBusiness, Business.class);
		User owner = userDao.findById(oId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid owner id"));
		business.setOwner(owner);
		return mapper.map(businessDao.save(business), AddBusinessDto.class);
	}

}
