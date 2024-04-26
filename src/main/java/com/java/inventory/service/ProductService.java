package com.java.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.java.inventory.dto.ProductDto;
import com.java.inventory.entity.Product;
import com.java.inventory.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository  productRepository;
	
	public ProductService(ProductRepository  productRepository) {
		this.productRepository  = productRepository;
	}

	public List<ProductDto> getAllProducts(){
		List<ProductDto> resposonseProductDtoList = new ArrayList<>();
		List<Product> productList = productRepository.findAll();
		
		for(Product prod: productList) {
			ProductDto prodDto = new ProductDto();
			BeanUtils.copyProperties(prod, prodDto);
			resposonseProductDtoList.add(prodDto);
		}
		return resposonseProductDtoList;
	}
}
