package com.java.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.entity.User;
import com.java.inventory.service.UserService;

import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<User> authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();

		return ResponseEntity.ok(currentUser);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<User>> allUsers() {
		List<User> users = userService.allUsers();

		return ResponseEntity.ok(users);
	}
	
//	@PutMapping("/update")
//	public ResponseEntity<List<User>> allUsers() {
//		List<User> users = userService.allUsers();
//
//		return ResponseEntity.ok(users);
//	} 
}
