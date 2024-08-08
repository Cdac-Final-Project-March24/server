package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.FeedbackDao;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDao feedbackDao;

}
