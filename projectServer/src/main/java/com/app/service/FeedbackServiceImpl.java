package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.FeedbackDao;

public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDao feedbackDao;

}
