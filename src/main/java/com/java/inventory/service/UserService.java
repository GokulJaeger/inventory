package com.java.inventory.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.java.inventory.constant.Constants;
import com.java.inventory.entity.User;
import com.java.inventory.repository.UserRepository;
import com.java.inventory.vo.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(Response response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if (currentUser != null) {
			response.setTitle(Constants.TITLE_BAD_REQUEST);
			response.setMessage("User successfully fetched");
			response.setStatusCode(Constants.OK);
		} else {
			response.setTitle(Constants.TITLE_BAD_REQUEST);
			response.setMessage("User not found");
			response.setStatusCode(Constants.NOT_FOUND);
		}
		return currentUser;
	}

	public List<User> getAllUsers(Response response) {
		List<User> users = new ArrayList<>();
		try {
			userRepository.findAll().forEach(users::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (users.isEmpty()) {
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage("No data found");
			response.setStatusCode(Constants.NO_CONTENT);
		} else {
			response.setTitle(Constants.TITLE_OK);
			response.setMessage(" All users details successfully fetched");
			response.setStatusCode(Constants.OK);
		}
		return users;
	}
}
