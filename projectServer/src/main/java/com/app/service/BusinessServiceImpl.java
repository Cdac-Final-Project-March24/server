package com.app.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	@Autowired
	private ImageHandelingService imageService;

	@Override
	public ApiResponse updateBusiness(AddBusinessDto newBusiness, MultipartFile img, Long bId) throws IOException {
		Business orignalBusiness = businessDao.findById(bId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Business Id"));
		mapper.map(newBusiness, orignalBusiness);
		if(img.getOriginalFilename() != null && !img.getOriginalFilename().isBlank()) {
			String path = imageService.saveImage(img);
			orignalBusiness.setCover(("http://localhost:8080/").concat(path));
		}
		return new ApiResponse("Updated successfully");
	}

	@Override
	public AddBusinessDto addBusiness(AddBusinessDto newBusiness, MultipartFile img, Long oId) throws IOException {
		Business business = mapper.map(newBusiness, Business.class);
		User owner = userDao.findById(oId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid owner id"));
		business.setOwner(owner);
		String path = imageService.saveImage(img); // save image and get its path
		business.setCover((("http://localhost:8080/").concat(path)));
		return mapper.map(businessDao.save(business), AddBusinessDto.class);
	}
	
	@Override
	public List<AddBusinessDto> getTopBusiness(double latitude, double longitude, int limit){
		return businessDao
				.findTopClosest(latitude, longitude, 
						PageRequest.of(0, limit, Sort.by("orderCount").descending()))
				.stream().map(b -> mapper.map(b, AddBusinessDto.class))
				.collect(Collectors.toList());
	}

}
