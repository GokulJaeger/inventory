package com.java.inventory.constant;


public class Constants {
	
	private Constants() {
		throw new IllegalStateException("Utility Constants Class");
	}
	/**
	 * Status Code Constants
	 */
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int ACCEPTED = 202;
	public static final int NO_CONTENT = 204;
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int BAD_GATEWAY = 502;
	public static final int SERVICE_UNAVAILABLE = 503;
	
	/**
	 * Status Code Title Constants
	 */
	public static final String TITLE_OK = "OK";
	public static final String TITLE_CREATED = "CREATED";
	public static final String TITLE_ACCEPTED = "ACCEPTED";
	public static final String TITLE_NO_CONTENT = "NO_CONTENT";
	public static final String TITLE_BAD_REQUEST = "BAD_REQUEST";
	public static final String TITLE_UNAUTHORIZED = "UNAUTHORIZED";
	public static final String TITLE_FORBIDDEN = "FORBIDDEN";
	public static final String TITLE_NOT_FOUND = "NOT_FOUND";
	public static final String TITLE_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	public static final String TITLE_BAD_GATEWAY = "BAD_GATEWAY";
	public static final String TITLE_SERVICE_UNAVAILABLE = "SERVICE_UNAVAILABLE";
	
	public static final String USER_CREATED = "User Created!...";
	public static final String USER_LOGGEDIN = "User Logged In!...";
	public static final String USER_LOGINFAILED = "User not found!...";
	public static final String USER_UPDATED = "User Updated!...";
	public static final String USER_DELETED = "User Deleted!...";
	
	public static final String USER_EMAIL = " Email already Exists!...";
	public static final String USER_ID = " User Id already Exists!...";
	public static final String USER_ROLE = " Role is invalid!...";
	
	public static final String UTILS_CREATED = "Utils Created!...";
	public static final String UTILS_UPDATED = "Utils Updated!...";
	public static final String UTILS_DELETED = "Utils Deleted!...";
	public static final String UTILS_EXIST = "Utils already Exist!...";
	public static final String UTILS_NOTEXIST = "Utils does't Exist!...";
	public static final String UTILS_ERROR = "Utils ERROR!...";
	public static final String UTILS_NAME_NULL = "Utils name cannot be null";
	
	public static final String API_AUTH = "/api/auth";
	public static final String API_SIGNUP = "/signup";
	public static final String API_LOGIN = "/login";
	
	public static final String API_USERS = "/api/users";
	public static final String API_ME = "/me";
	public static final String API_ALL = "/all";
	
	public static final String API_UTILS = "/api/utils";
	public static final String API_UTILS_GET_ALL = "/getAllUtils";
	public static final String API_UTILS_GET = "/getUtils";
	public static final String API_UTILS_POST = "/postUtils";
	public static final String API_UTILS_PUT = "/putUtils";
	public static final String API_UTILS_DELETE = "/deleteUtils";
	
	public static final String API_PRODUCT = "/api/products";
	public static final String API_PRODUCT_GET_ALL = "/getAllProducts";
	public static final String API_PRODUCT_GET = "/getProduct";
	public static final String API_PRODUCT_POST = "/postProducts";
	public static final String API_PRODUCT_PUT = "/putProduct";
	public static final String API_PRODUCT_DELETE = "/deleteProduct";
	

}
