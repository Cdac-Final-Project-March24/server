package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.OfferingService;

@RestController
@RequestMapping("/offering")
public class OfferingController {

	@Autowired
	private OfferingService offeringService;
}
