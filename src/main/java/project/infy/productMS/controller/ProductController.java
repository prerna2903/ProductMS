package project.infy.productMS.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import project.infy.productMS.dto.ProductDTO;
import project.infy.productMS.dto.StockDTO;
import project.infy.productMS.dto.SubscribedProductDTO;
import project.infy.productMS.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Environment environment;
	
	@Autowired
	RestTemplate restTemplate;
	
	//ADD PRODUCT
	@PostMapping(value = "/api/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) throws Exception {
		logger.info("Add request for product {} ", productDTO);
		ResponseEntity<String> response = null;
		try {
			productService.addProduct(productDTO);
			String message = environment.getProperty("Controller.PRODUCT_ADDED");
			response = new ResponseEntity<String>(message,HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	//REMOVE PRODUCT
	@DeleteMapping(value = "/api/products/{prodId}")
	public ResponseEntity<String> removeProduct(@PathVariable Integer prodId) throws Exception {
		logger.info("Remove request for product {} ", prodId);
		ResponseEntity<String> response = null;
		try {
			productService.removeProduct(prodId);
			String message = environment.getProperty("Controller.PRODUCT_DELETED");
			response = new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	//GET ALL PRODUCTS
	@GetMapping(value = "/api/products" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProducts() throws Exception {
		logger.info("Fetching all products");
		ResponseEntity<List<ProductDTO>> response = null;
		List<ProductDTO> products = null;
		try {
			products = productService.getProducts();
			response = new ResponseEntity<List<ProductDTO>>(products,HttpStatus.OK);
		} catch (Exception e) {
			response =  new ResponseEntity<List<ProductDTO>>(products,HttpStatus.BAD_REQUEST);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
		return response;
		//return productService.getProducts();
	}
	
	//GET PRODUCTS BY CATEGORY
	@GetMapping(value = "/api/products/category/{category}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) throws Exception {
		logger.info("Fetching products by category {}",category);
		ResponseEntity<List<ProductDTO>> response = null;
		List<ProductDTO> products = null;
		try {
			products = productService.getProductByCategory(category);
			response = new ResponseEntity<List<ProductDTO>>(products,HttpStatus.FOUND);
		} catch (Exception e) {
			response  = new ResponseEntity<List<ProductDTO>>(products,HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	//GET PRODUCTS BY PRODUCT NAME
	@GetMapping(value = "/api/products/name/{productName}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String productName) throws Exception {
		logger.info("Fetching products by name {}",productName);
		ResponseEntity<List<ProductDTO>> response = null;
		List<ProductDTO> products = null;
		try {
			products = productService.getProductByName(productName);
			response = new ResponseEntity<List<ProductDTO>>(products,HttpStatus.FOUND);
		}catch (Exception e) {
			response = new ResponseEntity<List<ProductDTO>>(products,HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	//GET PRODUCTS BY ID
	@GetMapping(value="/api/products/{prodId}" , produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer prodId) throws Exception {
		logger.info("Fetching product by id {}",prodId);
		ResponseEntity<ProductDTO> response = null;
		ProductDTO products = null;
		try {
			products = productService.getProductById(prodId);
			response = new ResponseEntity<ProductDTO>(products,HttpStatus.FOUND);
		}catch (Exception e){
			response = new ResponseEntity<ProductDTO>(products,HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	//DELETE ALL SELLER PRODUCTS
	@DeleteMapping(value = "/api/products/seller/{sellerId}")
	public ResponseEntity<String> deleteSellerProducts(@PathVariable Integer sellerId) throws Exception {
		logger.info("Deleting all seller products {}",sellerId);
		ResponseEntity<String> response = null;
		try {
			productService.deleteSellerProducts(sellerId);
			String message = environment.getProperty("Controller.ALL_SELLER_PRODUCTS_DELETED");
			response = new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (Exception e) {
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
    //UPDATE STOCK 
	@PostMapping(value = "/api/products/stockupdate")
	public ResponseEntity<String> updateStock(@RequestBody StockDTO stockDTO) throws Exception {
		logger.info("Updating Stock for product {} ",stockDTO);
		ResponseEntity<String> response = null;
		try {
			productService.updateStock(stockDTO);
			String message = environment.getProperty("Controller.UPDATE_STOCK");
			response = new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (Exception e) {
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	//STOCK UPDATE AFTER ORDER
	@GetMapping(value = "/api/products/{prodId}/quantity/{quantity}")
	public Boolean checkStockBeforeOrder(@PathVariable Integer prodId,@PathVariable Integer quantity) {
		logger.info("checking stock for product {}",prodId);
		return productService.checkStockBeforeOrder(prodId, quantity);
	}
	
	//ADD SUBSCRIBER PRODUCT
	@PostMapping(value = "/product/subscribedproducts")
	public ResponseEntity<String> addSubscribedProduct(@RequestBody SubscribedProductDTO subscribedProductDTO) throws Exception {
		logger.info("adding subscriber product");
		ResponseEntity<String> response = null;
		try {
			System.out.println("adding");
			String url = environment.getProperty("userUri")+subscribedProductDTO.getBuyerId()+"/privileged";
			System.out.println(url);
			Boolean isPrivileged = new RestTemplate().getForObject(url,Boolean.class);
			System.out.println(isPrivileged);
			if(isPrivileged) {
				System.out.println("adding");
				productService.addSubscribedProduct(subscribedProductDTO);
				System.out.println("added");
				String message = environment.getProperty("Controller.SUBSCRIBER_PRODUCT_ADDED");
				response = new ResponseEntity<String>(message,HttpStatus.OK);
			}
			else {
				System.out.println("error");
				String message = environment.getProperty("Controller.BUYER_IS_NOT_PRIVILEGED");
				response = new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			System.out.println("error");
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
			
		}
		return response;
		
	} 
		
	//VIEW SUBSCRIBER PRODUCTS
	@GetMapping(value = "/product/subscribedproducts/{buyerId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscribedProductDTO>> viewSubscribedProductsList(@PathVariable Integer buyerId) throws Exception {
		logger.info("viewing subscribed products");
		ResponseEntity<List<SubscribedProductDTO>> response = null;
		List<SubscribedProductDTO> products = null;
		try {
			products = productService.getsubscribedProducts(buyerId);
			response = new ResponseEntity<List<SubscribedProductDTO>>(products,HttpStatus.OK);
		}catch (Exception e) {
			response = new ResponseEntity<List<SubscribedProductDTO>>(products,HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}

