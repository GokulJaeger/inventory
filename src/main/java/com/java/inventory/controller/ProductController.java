package com.java.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.ProductDto;
import com.java.inventory.dto.UtilsDto;
import com.java.inventory.service.ProductService;
import com.java.inventory.vo.Response;

@RequestMapping(Constants.API_PRODUCT)
@RestController
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(Constants.API_PRODUCT_GET_ALL)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Response> getAllProducts() {
		logger.info("ProductController getAllProducts STARTED");
		Response response = new Response();
		response.setData(productService.readAllProducts(response));
		response.setInstance(Constants.API_PRODUCT.concat(Constants.API_PRODUCT_GET_ALL));
		logger.info("ProductController getAllProducts ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(Constants.API_PRODUCT_GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Response> getProduct(ProductDto requestProductDto) {
		logger.info("ProductController getProduct STARTED");
		Response response = new Response();
		response.setData(productService.readProduct(requestProductDto, response));
		response.setInstance(Constants.API_PRODUCT.concat(Constants.API_PRODUCT_GET));
		logger.info("ProductController getProduct ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}