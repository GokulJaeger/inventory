package com.java.inventory.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.LoginUserDto;
import com.java.inventory.dto.RegisterUserDto;
import com.java.inventory.dto.SignUpUserDto;
import com.java.inventory.entity.Role;
import com.java.inventory.entity.RoleEnum;
import com.java.inventory.entity.User;
import com.java.inventory.entity.UserLogin;
import com.java.inventory.repository.RoleRepository;
import com.java.inventory.repository.UserLoginRepository;
import com.java.inventory.repository.UserRepository;
import com.java.inventory.vo.Response;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final UserLoginRepository userLoginRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
			AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			UserLoginRepository userLoginRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.userLoginRepository = userLoginRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public SignUpUserDto signup(RegisterUserDto input, Response response) {
		SignUpUserDto signUpDto = new SignUpUserDto();
		Role role = new Role();
		Optional<Role> optionalRole = Optional.of(role);

		Optional<User> existingUserEmail = userRepository.findByEmail(input.getEmail());
		if (existingUserEmail.isPresent()) {
			response.setTitle(Constants.TITLE_BAD_REQUEST);
			response.setMessage(Constants.USER_EMAIL);
			response.setStatusCode(Constants.BAD_REQUEST);

		} else {
			Optional<User> existingUserId = userRepository.findByUserId(input.getUserId());
			if (existingUserId.isPresent()) {
				response.setTitle(Constants.TITLE_BAD_REQUEST);
				response.setMessage(Constants.USER_ID);
				response.setStatusCode(Constants.BAD_REQUEST);
			} else {
				if (input.getRole().equalsIgnoreCase("USER")) {
					optionalRole = roleRepository.findByName(RoleEnum.USER);
				} else if (input.getRole().equalsIgnoreCase("ADMIN")) {
					optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
				}

				if (optionalRole.isEmpty()) {
					response.setTitle(Constants.TITLE_BAD_REQUEST);
					response.setMessage(Constants.USER_ROLE);
					response.setStatusCode(Constants.BAD_REQUEST);
					return null;
				}

				User user = new User();
				user.setUserId(input.getUserId());
				user.setFullName(input.getFullName());
				user.setEmail(input.getEmail());
				user.setActive(true);
				user.setRole(optionalRole.get());
				user.setPassword(passwordEncoder.encode(input.getPassword()));

				user = userRepository.save(user);

				UserLogin userLogin = new UserLogin();
				userLogin.setEmail(user.getEmail());
				userLogin.setAccessToken("empty_token");
				userLogin.setUser(user);

				userLogin = userLoginRepository.save(userLogin);

				user.setUserLogin(userLogin);
				user = userRepository.save(user);

				BeanUtils.copyProperties(user, signUpDto);
				response.setTitle(Constants.TITLE_CREATED);
				response.setMessage(Constants.USER_CREATED);
				response.setStatusCode(Constants.CREATED);

			}
		}

		return signUpDto;
	}

	public User authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		return userRepository.findByEmail(input.getEmail()).orElseThrow();
	}

	public void saveUserJwtToken(User inputUser, String jwtToken) {
		Optional<User> existingUser = userRepository.findByEmail(inputUser.getEmail());

		existingUser.ifPresent(user -> {
			user.setJwtToken(jwtToken);
			userRepository.save(user);
			Optional<UserLogin> existUserLogin = userLoginRepository.findById(user.getUserLogin().getId());
			existUserLogin.ifPresent(userLogin -> {
				userLogin.setAccessToken(jwtToken);
				userLoginRepository.save(userLogin);
			});
		});
	}
}
