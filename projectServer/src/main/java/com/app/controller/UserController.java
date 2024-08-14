package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddUserDto;
import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.security.JwtUtils;
import com.app.dto.UpdateUserRequestDto;
import com.app.entity.User;
import com.app.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authMgr;
	@Autowired
	private JwtUtils jwtUtils;

	// User Signin
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid SigninRequest request) {
		System.out.println("in sign in" + request);
		// create a token(implementation of Authentication i/f)
		// to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		// invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
		// => authentication n authorization successful !
		System.out.println(verifiedToken.getPrincipal());// custom user details object
		// create JWT n send it to the clnt in response
		SigninResponse resp = new SigninResponse(jwtUtils.generateJwtToken(verifiedToken),
				"success");
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	// User Registration
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody @Valid AddUserDto newUser) {
		System.out.println(newUser);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userService.addUser(newUser));
	}

	// for updating user profile , first we will call this api, and on confirming
	// changes automatically front end will call get api written below.
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequestDto request) {
		User updatedUser = userService.updateUser(
				id,
				request.getName(),
				request.getEmail(),
				request.getPassword(),
				request.getMobileNumber(),
				request.getAddress());
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
}
