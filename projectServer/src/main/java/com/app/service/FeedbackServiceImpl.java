package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BusinessDao;
import com.app.dao.FeedbackDao;
import com.app.dto.FeedbackDto;
import com.app.dto.GetOfferingDto;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDao feedbackDao;
	@Autowired
	BusinessDao businessDao;
	@Autowired
	ModelMapper mapper;

	@Override
	public List<FeedbackDto> getFeedback(Long bId) {
		if(!businessDao.existsById(bId))
			throw new ResourceNotFoundException("Invalid Business Id");

		return feedbackDao.getfeedback(bId).stream()
				.map(f -> {
					FeedbackDto dto = mapper.map(f, FeedbackDto.class);
					dto.setCustomer(f.getCustomer().getName());
					return dto;
				}).collect(Collectors.toList());
	}

}
