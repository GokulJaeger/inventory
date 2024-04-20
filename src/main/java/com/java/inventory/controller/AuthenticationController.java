package com.java.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.LoginUserDto;
import com.java.inventory.dto.RegisterUserDto;
import com.java.inventory.dto.SignUpUserDto;
import com.java.inventory.entity.User;
import com.java.inventory.service.AuthenticationService;
import com.java.inventory.service.JwtService;
import com.java.inventory.vo.LoginResponse;
import com.java.inventory.vo.Response;


@RequestMapping(Constants.API_AUTH)
@RestController
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(Constants.API_SIGNUP)
    public ResponseEntity<Response> register(@RequestBody RegisterUserDto registerUserDto) {
    	logger.info("AuthenticationController register STARTED");
    	Response response = new Response();
    	SignUpUserDto registeredUser = authenticationService.signup(registerUserDto, response);
    	
    	response.setData(registeredUser);
		response.setInstance(Constants.API_AUTH.concat(Constants.API_SIGNUP));
		logger.info("AuthenticationController register ENDED");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(Constants.API_LOGIN)
    public ResponseEntity<Response> authenticate(@RequestBody LoginUserDto loginUserDto) {
    	logger.info("AuthenticationController authenticate STARTED");
    	Response response = new Response();
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpireDate(jwtService.extractExpiration(jwtToken));
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        
        authenticationService.saveUserJwtToken(authenticatedUser, jwtToken);
        if(authenticatedUser != null) {
        	logger.info("AuthenticationController authenticate User Login Success");
        	response.setTitle(Constants.TITLE_ACCEPTED);
        	response.setMessage(Constants.USER_LOGGEDIN);
    		response.setStatusCode(Constants.ACCEPTED);
        }else {
        	logger.info("AuthenticationController authenticate User Login Failed");
        	response.setTitle(Constants.TITLE_NOT_FOUND);
        	response.setMessage(Constants.USER_LOGINFAILED);
    		response.setStatusCode(Constants.NOT_FOUND);
        }
        
        response.setData(loginResponse);
		response.setInstance(Constants.API_AUTH.concat(Constants.API_LOGIN));
		logger.info("AuthenticationController authenticate ENDED");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}