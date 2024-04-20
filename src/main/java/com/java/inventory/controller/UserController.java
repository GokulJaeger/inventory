package com.java.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.constant.Constants;
import com.java.inventory.entity.User;
import com.java.inventory.service.UserService;
import com.java.inventory.vo.Response;

import java.util.List;

@RequestMapping(Constants.API_USERS)
@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(Constants.API_ME)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Response> authenticatedUser() {
		logger.info("UserController authenticatedUser STARTED");
		Response response = new Response();
		response.setData(userService.getUser(response));
		response.setInstance(Constants.API_USERS.concat(Constants.API_ME));
		logger.info("UserController authenticatedUser ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(Constants.API_ALL)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response> allUsers() {
		logger.info("UserController allUsers STARTED");
		Response response = new Response();
		List<User> users = userService.getAllUsers(response);
		response.setData(users);
		response.setInstance(Constants.API_USERS.concat(Constants.API_ALL));
		logger.info("UserController allUsers ENDED");
		return ResponseEntity.ok(response);
	}
	
//	@PutMapping("/update")
//	public ResponseEntity<List<User>> allUsers() {
//		List<User> users = userService.allUsers();
//
//		return ResponseEntity.ok(users);
//	} 
}
