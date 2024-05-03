package com.java.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.ProductDto;
import com.java.inventory.service.ProductService;
import com.java.inventory.vo.Response;

@RequestMapping(value = Constants.API_PRODUCT, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = Constants.API_PRODUCT_GET_ALL)
	public ResponseEntity<Response> getAllProducts() {
		logger.info("ProductController getAllProducts STARTED");
		Response response = new Response();
		// read All Products
		response.setData(productService.readAllActiveProducts(response));
		response.setInstance(Constants.API_PRODUCT.concat(Constants.API_PRODUCT_GET_ALL));
		logger.info("ProductController getAllProducts ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = Constants.API_PRODUCT_GET)
	public ResponseEntity<Response> getProduct(@RequestBody ProductDto requestProductDto) {
		logger.info("ProductController getProduct STARTED");
		Response response = new Response();
		// read Product
		response.setData(productService.readActiveProduct(requestProductDto, response));
		response.setInstance(Constants.API_PRODUCT.concat(Constants.API_PRODUCT_GET));
		logger.info("ProductController getProduct ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = Constants.API_PRODUCT_POST)
	public ResponseEntity<Response> postProduct(@RequestBody ProductDto requestProductDto) {
		logger.info("ProductController postProduct STARTED");
		Response response = new Response();
		// create Product
		Optional<?> optionalResponse = productService.createProduct(requestProductDto, response);
		if (optionalResponse.isPresent()) {
			response.setData(optionalResponse.get());
			Object entity = optionalResponse;
			// Check Object instance is of type ProductDto
			if (entity instanceof ProductDto) {
				response.setMessage(Constants.PRODUCT_CREATED);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
				// Check the object instance is of List and then checks the generic type is
				// instance of String type
			} else if (entity instanceof List<?> && ((List<?>) entity).get(0) instanceof String) {
				response.setMessage(Constants.NULL_FIELDS);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
			}
		}else {
			response.setMessage(Constants.SERVER_ERROR);
			response.setStatusCode(Constants.INTERNAL_SERVER_ERROR);
			response.setTitle(Constants.TITLE_INTERNAL_SERVER_ERROR);
		}
		response.setInstance(Constants.API_PRODUCT.concat(Constants.API_PRODUCT_POST));
		logger.info("ProductController postProduct ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping(value = Constants.API_PRODUCT_PUT)
	public ResponseEntity<Response> putProduct(@RequestBody ProductDto requestProductDto) {
		logger.info("ProductController putProduct STARTED");
		Response response = new Response();
		// update Product
		Optional<?> optionalResponse = productService.updateProduct(requestProductDto, response);
		if (optionalResponse.isPresent()) {
			response.setData(optionalResponse.get());
			Object entity = optionalResponse;
			// Check Object instance is of type ProductDto
			if (entity instanceof ProductDto) {
				/**
				 * ProductDto productDto = (ProductDto) entity; response.setData(productDto);
				 */
				response.setMessage(Constants.PRODUCT_UPDATED);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
				// Check the object instance is of List and then checks the generic type is
				// instance of String type
			} else if (entity instanceof List<?> && ((List<?>) entity).get(0) instanceof String) {
				/**
				 * List<String> nullValues = (List<String>) entity;
				 * response.setData(nullValues);
				 */
				response.setMessage(Constants.NULL_FIELDS);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
			}
		}else {
			response.setMessage(Constants.SERVER_ERROR);
			response.setStatusCode(Constants.INTERNAL_SERVER_ERROR);
			response.setTitle(Constants.TITLE_INTERNAL_SERVER_ERROR);
		}
		response.setInstance(Constants.API_PRODUCT.concat(Constants.API_PRODUCT_PUT));
		logger.info("ProductController putProduct ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = Constants.API_PRODUCT_DELETE)
	public ResponseEntity<Response> deleteProduct(@RequestBody ProductDto requestProductDto) {
		logger.info("ProductController deleteProduct STARTED");
		Response response = new Response();
		// delete Product
		productService.deleteProduct(requestProductDto, response);
		response.setInstance(Constants.API_PRODUCT.concat(Constants.API_PRODUCT_DELETE));
		logger.info("ProductController deleteProduct ENDED");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
