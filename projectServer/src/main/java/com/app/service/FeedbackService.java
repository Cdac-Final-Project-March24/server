package com.app.service;

import java.util.List;

import com.app.dto.FeedbackDto;

public interface FeedbackService {
	
	List<FeedbackDto> getFeedback(Long bId);

}
