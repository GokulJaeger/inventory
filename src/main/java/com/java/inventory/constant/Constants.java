package com.java.inventory.constant;

@SuppressWarnings("unused")
public class Constants {
	
	private Constants() {
		throw new IllegalStateException("Utility Constants Class");
	}
	/**
	 * Status Code Constants
	 */
	private static final int OK = 200;
	private static final int CREATED = 201;
	private static final int ACCEPTED = 202;
	private static final int NO_CONTENT = 204;
	private static final int BAD_REQUEST = 400;
	private static final int UNAUTHORIZED = 401;
	private static final int FORBIDDEN = 403;
	private static final int NOT_FOUND = 404;
	private static final int INTERNAL_SERVER_ERROR = 500;
	private static final int BAD_GATEWAY = 502;
	private static final int SERVICE_UNAVAILABLE = 503;
	
	private static final String USER_CREATED = "User Created!...";
	private static final String USER_UPDATED = "User Updated!...";
	private static final String USER_DELETED = "User Deleted!...";
	
	private static final String USER_EMAIL = " Email already Exists!...";
	private static final String USER_ID = " User Id already Exists!...";
	private static final String USER_ROLE = " Role is invalid!...";
	
	private static final String API_AUTH = "/api/auth";
	private static final String API_SIGNUP = "/signup";
	private static final String API_LOGIN = "/login";
	
	private static final String API_USERS = "/api/users";
	private static final String API_ME = "/me";
	private static final String API_ALL = "/all";
	


}
