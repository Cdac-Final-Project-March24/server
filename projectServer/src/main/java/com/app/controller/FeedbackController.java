package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.FeedbackService;
import com.app.service.UserService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@GetMapping("/{bId}")
	public ResponseEntity<?> getFeedback(@PathVariable Long bId){
		return ResponseEntity.status(HttpStatus.OK)
				.body(feedbackService.getFeedback(bId));
	}
	
}
