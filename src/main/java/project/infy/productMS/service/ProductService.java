package project.infy.productMS.service;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import project.infy.productMS.dto.ProductDTO;
import project.infy.productMS.dto.StockDTO;
import project.infy.productMS.dto.SubscribedProductDTO;
import project.infy.productMS.entity.ProductEntity;
import project.infy.productMS.entity.SubscribedProductEntity;
import project.infy.productMS.repository.ProductRepository;
import project.infy.productMS.repository.SubscribedProductRepository;
import project.infy.productMS.validator.ProductValidator;

@Service
public class ProductService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SubscribedProductRepository subscribedProductRepository;
	
	//ADD PRODUCT
	public void addProduct(ProductDTO productDTO) throws Exception {
		ProductValidator.validaterForAdd(productDTO);
		logger.info("Add request for product :{} ", productDTO);
		ProductEntity productEntity = productDTO.createEntity();
		productRepository.save(productEntity);
	}
	
	//REMOVE PRODUCT
	public void removeProduct(Integer prodId) throws Exception {
		logger.info("Remove request for product :{} ", prodId);
		Optional<ProductEntity> productEntity = productRepository.findById(prodId);
		if(productEntity.isPresent()) {
			productRepository.deleteById(prodId);
		}else {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}
	}
	
	//GET ALL PRODUCTS
	public List<ProductDTO> getProducts() throws Exception {
		List<ProductEntity> productEntities = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		if(productEntities.isEmpty()) {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}
		else {
			for(ProductEntity product : productEntities) {
				ProductDTO productDTO = ProductDTO.valueOf(product);
				productDTOs.add(productDTO);
			}
		}
		logger.info("Product : {} ", productDTOs);
		return productDTOs;
	}
	
	//GET PRODUCTS BY CATEGORY
	public List<ProductDTO> getProductByCategory(String category) throws Exception {
		logger.info("Product details : {}", category);
		List<ProductEntity> productEntities = productRepository.findByCategory(category);
		List<ProductDTO> productDTOs = new ArrayList<>();
		if(productEntities.isEmpty()) {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}
		else {
			for(ProductEntity product : productEntities) {
				ProductDTO productDTO = ProductDTO.valueOf(product);
				productDTOs.add(productDTO);
		}
		}
		return productDTOs;
	}
	
	
	//GET PRODUCTS BY ID
	public ProductDTO getProductById(Integer prodId) throws Exception {
		logger.info("Product details : {}", prodId);
		Optional<ProductEntity> optionalProduct = productRepository.findById(prodId);
		ProductDTO productDTO = null;
		if (optionalProduct.isPresent()) {
			ProductEntity productEntity = optionalProduct.get();
			productDTO = ProductDTO.valueOf(productEntity);
		}
		else {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}
		return productDTO;
	}
	
	
	//GET PRODUCTS BY PRODUCT NAME
	public List<ProductDTO> getProductByName(String productName) throws Exception {
		logger.info("Product details : {}", productName);
		List<ProductEntity> productList = productRepository.findByProductName(productName);
		List<ProductDTO> productDTOs = new ArrayList<>();
		if(productList.isEmpty()) {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}
		else {
			for(ProductEntity product : productList) {
				ProductDTO productDTO = ProductDTO.valueOf(product);
				productDTOs.add(productDTO);
			}
		}
		return productDTOs;
	}
	
	//DELETE ALL SELLER PRODUCTS
	public void deleteSellerProducts(Integer sellerId) throws Exception {
		logger.info("Deleting all products of sellerid : {}", sellerId);
		List<ProductEntity> ItemsToDelete = productRepository.findBySellerId(sellerId);
		if(ItemsToDelete.isEmpty()) {
			throw new Exception("Service.NO_PRODUCTS_AVAILABLE");
		}
		else {
			for(ProductEntity product : ItemsToDelete) {
				productRepository.delete(product);
			}
		}
	}
	
	//UPDATE STOCK
	public void updateStock(StockDTO stockDTO) throws Exception {
		logger.info("Updating stock for : {}", stockDTO);
		Optional<ProductEntity> optionalproduct = productRepository.findById(stockDTO.getProdId());
		if(stockDTO.getStock()<10) {
			throw new Exception("Service.MORE_THAN_10_STOCKS_ARE_REQUIRED");
		}
		if(optionalproduct.isPresent()) {
		ProductEntity product = optionalproduct.get();
		product.setStock(stockDTO.getStock());
		productRepository.save(product);
		}
		}
	
	//STOCK UPDATE AFTER ORDER
	public Boolean checkStockBeforeOrder(Integer prodId,Integer quantity) {
		logger.info("Updating stock after order");
		Optional<ProductEntity> optionalproduct = productRepository.findById(prodId);
		Boolean value = false;
		if(optionalproduct.isPresent()) {
		ProductEntity product = optionalproduct.get();
		if(product.getStock()>=quantity) {
			product.setStock(product.getStock()-quantity);
			productRepository.save(product);
			value = true;
		}

		}
		return value;
	}
	
	//GET SUBSCRIBED PRODUCTS
	public List<SubscribedProductDTO> getsubscribedProducts(Integer buyerId) throws Exception {
		logger.info("Fething subscribed products for :{}",buyerId);
		List<SubscribedProductEntity> subscribeProducts = subscribedProductRepository.findByBuyerId(buyerId);
		List<SubscribedProductDTO> subscribedProductDTOList = new ArrayList<>();
		if(subscribeProducts.isEmpty()) {
			throw new Exception("Service.NO_PRODUCTS_AVAILABLE");
		}
		for(SubscribedProductEntity products : subscribeProducts) {
			SubscribedProductDTO subscribedProductDTO = SubscribedProductDTO.valueOf(products);
			subscribedProductDTOList.add(subscribedProductDTO);
			
		}
		return subscribedProductDTOList;
	}
	
	//ADD SUBSCRIBED PRODUCT
	public String addSubscribedProduct(SubscribedProductDTO subscribedProductDTO) {
		SubscribedProductEntity subscribeProductEntity = subscribedProductDTO.createEntity();
		subscribedProductRepository.save(subscribeProductEntity);
		return "Successfully added to subscribed products";
		}
	
}


	

