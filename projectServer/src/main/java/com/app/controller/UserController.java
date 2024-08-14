package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddUserDto;
import com.app.dto.UpdateUserRequestDto;
import com.app.entity.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody @Valid AddUserDto newUser){
		System.out.println(newUser);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userService.addUser(newUser));
	}
	
	
	//for updating user profile , first we will call this api, and on confirming changes automatically front end will call get api written below.
	 @PutMapping("/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequestDto request) {
	        User updatedUser = userService.updateUser(
	            id,
	            request.getName(),
	            request.getEmail(),
	            request.getPassword(),
	            request.getMobileNumber(),
	            request.getAddress()
	        );
	        return ResponseEntity.ok(updatedUser);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
	        User user = userService.getUserById(id);
	        return ResponseEntity.ok(user);
	    }
}
