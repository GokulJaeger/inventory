package com.java.inventory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.java.inventory.constant.Constants;
import com.java.inventory.dto.ProductDto;
import com.java.inventory.entity.Product;
import com.java.inventory.entity.Utils;
import com.java.inventory.repository.ProductRepository;
import com.java.inventory.repository.UtilsRepository;
import com.java.inventory.vo.Response;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepository productRepository;
	private final UtilsRepository utilsRepository;

	public ProductService(ProductRepository productRepository, UtilsRepository utilsRepository) {
		this.productRepository = productRepository;
		this.utilsRepository = utilsRepository;
	}

	public List<ProductDto> readAllProducts(Response response) {
		logger.info("ProductService readAllProducts STARTED");
		logger.info("ProductService readAllProducts Product all details fetch");
		List<ProductDto> resposonseProductDtoList = new ArrayList<>();
		List<Product> productList = productRepository.findAll();

		if (!productList.isEmpty()) {
			for (Product prod : productList) {
				ProductDto prodDto = new ProductDto();
				BeanUtils.copyProperties(prod, prodDto);
				prodDto.setUtilsId(prod.getUtils().getId());
				resposonseProductDtoList.add(prodDto);
			}
			response.setTitle(Constants.TITLE_OK);
			response.setStatusCode(Constants.OK);
			response.setMessage(Constants.PRODUCT_FETCH_ALL);
		} else {
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage(Constants.NO_DATA_FOUND);
			response.setStatusCode(Constants.NO_CONTENT);
		}
		logger.info("ProductService readAllProducts ENDED");
		return resposonseProductDtoList;
	}

	public ProductDto readProduct(ProductDto requestProductDto, Response response) {
		logger.info("ProductService readProduct STARTED");
		ProductDto resposonseProductDto = new ProductDto();
		logger.info("ProductService readProduct Product details fetch for: {}", requestProductDto.getName());
		Optional<Product> product = productRepository.findByName(requestProductDto.getName());
		if (product.isPresent()) {
			Product prod = product.get();
			resposonseProductDto.setUtilsId(prod.getUtils().getId());
			BeanUtils.copyProperties(prod, resposonseProductDto);
			response.setTitle(Constants.TITLE_OK);
			response.setMessage(Constants.PRODUCT_FETCH);
			response.setStatusCode(Constants.OK);
		} else {
			resposonseProductDto = null;
			response.setTitle(Constants.TITLE_NO_CONTENT);
			response.setMessage(Constants.NO_DATA_FOUND);
			response.setStatusCode(Constants.NO_CONTENT);
		}
		logger.info("ProductService readProduct ENDED");
		return resposonseProductDto;
	}

	public Optional<?> createProduct(ProductDto requestProductDto, Response response) {
		logger.info("ProductService createProduct STARTED");
		ProductDto resposonseProductDto = new ProductDto();
		logger.info("ProductService createProduct Product create request for: {}", requestProductDto.getName());
		if (requestProductDto.getName() != null) {
			Optional<Product> existingProduct = productRepository.findByName(requestProductDto.getName());
			List<String> nullFields = new ArrayList<>(); // List to track null fields
			if (!existingProduct.isPresent()) {
				Product createProduct = new Product();
				mapDtoToEntity(createProduct, requestProductDto, nullFields);
				createProduct.setCreatedAt(new Date());
				createProduct.setUpdatedAt(new Date());
				createProduct.setActive(true);
				Optional<Utils> existingUtils = utilsRepository.findById(requestProductDto.getUtilsId());
				if (existingUtils.isPresent()) {
					Utils utils = existingUtils.get();
					createProduct.setUtils(utils);
					createProduct = productRepository.save(createProduct);
					resposonseProductDto.setUtilsId(createProduct.getUtils().getId());
					BeanUtils.copyProperties(createProduct, resposonseProductDto);
				} else {
					nullFields.add("Utils Id does not exist");
				}
				response.setMessage(Constants.UTILS_CREATED);
				response.setStatusCode(Constants.CREATED);
				response.setTitle(Constants.TITLE_CREATED);
				if (!nullFields.isEmpty()) {
					return Optional.of(nullFields);
				}
			} else {
				response.setMessage(Constants.PRODUCT_EXIST);
				response.setStatusCode(Constants.BAD_REQUEST);
				response.setTitle(Constants.TITLE_BAD_REQUEST);
			}
		} else {
			response.setMessage(Constants.PRODUCT_NAME_NULL);
			response.setStatusCode(Constants.BAD_REQUEST);
			response.setTitle(Constants.TITLE_BAD_REQUEST);
		}
		logger.info("ProductService createProduct ENDED");
		return Optional.of(resposonseProductDto);
	}

	public Optional<?> updateProduct(ProductDto requestProductDto, Response response) {
		logger.info("ProductService updateProduct STARTED");
		ProductDto resposonseProductDto = new ProductDto();
		logger.info("ProductService updateProduct Product update request for: {}", requestProductDto.getId());
		if (requestProductDto.getName() != null) {
			Optional<Product> existingProduct = productRepository.findById(requestProductDto.getId());
			List<String> nullFields = new ArrayList<>(); // List to track null fields
			if (existingProduct.isPresent()) {

				Product updateProduct = existingProduct.get();
				mapDtoToEntity(updateProduct, requestProductDto, nullFields);
				updateProduct.setUpdatedAt(new Date());
				updateProduct.setActive(true);
				Optional<Utils> existingUtils = utilsRepository.findById(requestProductDto.getUtilsId());
				if (existingUtils.isPresent()) {
					Utils utils = existingUtils.get();
					updateProduct.setUtils(utils);
					updateProduct = productRepository.save(updateProduct);
					resposonseProductDto.setUtilsId(updateProduct.getUtils().getId());
					BeanUtils.copyProperties(updateProduct, resposonseProductDto);
				} else {
					nullFields.add("Utils Id does not exist");
				}
				if (!nullFields.isEmpty()) {
					return Optional.of(nullFields);
				}
			} else {
				response.setMessage(Constants.PRODUCT_NOTEXIST);
				response.setStatusCode(Constants.BAD_REQUEST);
				response.setTitle(Constants.TITLE_BAD_REQUEST);
			}
		} else {
			response.setMessage(Constants.PRODUCT_NAME_NULL);
			response.setStatusCode(Constants.BAD_REQUEST);
			response.setTitle(Constants.TITLE_BAD_REQUEST);
		}
		logger.info("ProductService updateProduct ENDED");
		return Optional.of(resposonseProductDto);

	}

	public void deleteProduct(ProductDto requestProductDto, Response response) {
		logger.info("ProductService deleteProduct STARTED");
		logger.info("ProductService deleteProduct Product delete request for: {}", requestProductDto.getName());
		if (requestProductDto.getName() != null) {
			Optional<Product> existingDeleteProduct = productRepository.findByName(requestProductDto.getName());
			if (existingDeleteProduct.isPresent()) {
				productRepository.deleteById(existingDeleteProduct.get().getId());
				response.setMessage(Constants.PRODUCT_DELETED);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
			} else {
				response.setMessage(Constants.PRODUCT_NOTEXIST);
				response.setStatusCode(Constants.OK);
				response.setTitle(Constants.TITLE_OK);
			}
		} else {
			response.setMessage(Constants.PRODUCT_NAME_NULL);
			response.setStatusCode(Constants.BAD_REQUEST);
			response.setTitle(Constants.TITLE_BAD_REQUEST);
		}
		logger.info("ProductService deleteProduct ENDED");
	}

	private void mapDtoToEntity(Product product, ProductDto productDTO, List<String> nullFields) {
		logger.info("ProductService mapDtoToEntity STARTED");
		// Map name
		if (isNotNullOrEmpty(productDTO.getName())) {
			product.setName(productDTO.getName());
		}

		// Map cost
		if (isNotNullOrEmpty(productDTO.getCost())) {
			product.setCost(productDTO.getCost());
		} else {
			nullFields.add("cost is null");
		}

		// Map barcode
		if (isNotNullOrEmpty(productDTO.getBarcode())) {
			product.setBarcode(productDTO.getBarcode());
		} else {
			nullFields.add("barcode is null");
		}

		// Map quantity
		if (isNotNullOrEmpty(productDTO.getQuantity())) {
			product.setQuantity(productDTO.getQuantity());
		} else {
			nullFields.add("quantity is null");
		}

		// Map brand
		if (isNotNullOrEmpty(productDTO.getBrand())) {
			product.setBrand(productDTO.getBrand());
		} else {
			nullFields.add("brand is null");
		}

		// Map description (assuming it corresponds to desc1 in Product)
		if (isNotNullOrEmpty(productDTO.getDescription())) {
			product.setDescription(productDTO.getDescription());
		} else {
			nullFields.add("description is null");
		}

		// Map location
		if (isNotNullOrEmpty(productDTO.getLocation())) {
			product.setLocation(productDTO.getLocation());
		} else {
			nullFields.add("location is null");
		}

		// Map image
		if (isNotNullOrEmpty(productDTO.getImage())) {
			product.setImage(productDTO.getImage());
		} else {
			nullFields.add("image is null");
		}

		/**
		 * // Map isActive product.setActive(true);
		 * 
		 * // Set createdAt and updatedAt (assuming you want to preserve these dates)
		 * product.setCreatedAt(new Date()); // Set current date if not provided
		 * product.setUpdatedAt(new Date()); // Set current date if not provided
		 */
		logger.info("ProductService mapDtoToEntity ENDED");
	}

	// Helper method to check if a string is not null and not empty
	private boolean isNotNullOrEmpty(String value) {
		logger.info("ProductService isNotNullOrEmpty Called...");
		return value != null && !value.trim().isEmpty();
	}
}
