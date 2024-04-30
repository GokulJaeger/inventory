package com.java.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.java.inventory.constant.Constants;
import com.java.inventory.dto.ProductDto;
import com.java.inventory.dto.UtilsDto;
import com.java.inventory.entity.Product;
import com.java.inventory.entity.Utils;
import com.java.inventory.repository.ProductRepository;
import com.java.inventory.vo.Response;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDto> readAllProducts(Response response) {
		List<ProductDto> resposonseProductDtoList = new ArrayList<>();
		List<Product> productList = productRepository.findAll();

		if (!productList.isEmpty()) {
			for (Product prod : productList) {
				ProductDto prodDto = new ProductDto();
				BeanUtils.copyProperties(prod, prodDto);
				resposonseProductDtoList.add(prodDto);
			}
			response.setTitle(Constants.TITLE_OK);
			response.setStatusCode(Constants.OK);
			response.setMessage("All Product details successfully fetched");
		} else {
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage("No data found");
			response.setStatusCode(Constants.NO_CONTENT);
		}
		return resposonseProductDtoList;
	}
	
	public ProductDto readProduct(ProductDto requestProductDto, Response response) {
		ProductDto resposonseProductDto = new ProductDto();
		Optional<Product> product = productRepository.findByName(requestProductDto.getName());
		System.out.println("Name: " + requestProductDto.getName());
		if(product.isPresent()) {
			Product prod = product.get();
			BeanUtils.copyProperties(prod, resposonseProductDto);
			response.setTitle(Constants.TITLE_OK);
			response.setMessage("Utils details successfully fetched");
			response.setStatusCode(Constants.OK);
		}else {
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage("No data found");
			response.setStatusCode(Constants.NO_CONTENT);
		}
		return resposonseProductDto;
	}
}
