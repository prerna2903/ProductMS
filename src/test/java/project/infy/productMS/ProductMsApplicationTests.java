package project.infy.productMS;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import project.infy.productMS.dto.ProductDTO;
import project.infy.productMS.entity.ProductEntity;
import project.infy.productMS.repository.ProductRepository;
import project.infy.productMS.service.ProductService;
import project.infy.productMS.validator.ProductValidator;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class ProductMsApplicationTests {
	@Mock
	ProductRepository productRepository;
	
	@Mock
	ProductValidator productValidator;
	
	@InjectMocks
	ProductService productService;
	

	@Test
	public void getAllProductsTest() throws Exception {
		
		ProductEntity productEntity1 = new ProductEntity();
		productEntity1.setBrand("adidas");
		productEntity1.setCategory("Clothing");
		productEntity1.setDescription("cotton comfy trackpants");
		productEntity1.setImage("image.jpeg/");
		productEntity1.setPrice(1500.00);
		productEntity1.setProdId(1);
		productEntity1.setProductName("summer trackpants");
		productEntity1.setRating(4);
		productEntity1.setSellerId(7);
		productEntity1.setStock(15);
		productEntity1.setSubcategory("men trackpants");
		
		ProductEntity productEntity2 = new ProductEntity();
		productEntity2.setBrand("nike");
		productEntity2.setCategory("Clothing");
		productEntity2.setDescription("cotton comfy trackpants");
		productEntity2.setImage("image.jpeg/");
		productEntity2.setPrice(2000.00);
		productEntity2.setProdId(2);
		productEntity2.setProductName("summer trackpants");
		productEntity2.setRating(3);
		productEntity2.setSellerId(8);
		productEntity2.setStock(20);
		productEntity2.setSubcategory("men trackpants");
		
		List<ProductEntity> productsList = new ArrayList<>();
		productsList.add(productEntity1);
		productsList.add(productEntity2);
		
		Mockito.when(productRepository.findAll()).thenReturn(productsList);
		List<ProductDTO> productDTO = productService.getProducts();
		Assertions.assertNotEquals(productDTO.size(),0);
		
	}
/*	
	@Test
	public void getProductById() throws Exception {
		int id = 4;
		productService.getProductById(id);
		Mockito.validateMockitoUsage();
	}
	
	@Test
	public void getProductByIdInvalid() throws Exception {
		int id = 4;
		Mockito.when(productRepository.findById(id)).thenReturn(null);
		productService.getProductById(id);
		Mockito.validateMockitoUsage();
		}
		*/
	
	
	@Test
	public void getProductsByCategoryTest() throws Exception {
		ProductEntity productEntity1 = new ProductEntity();
		productEntity1.setBrand("adidas");
		productEntity1.setCategory("Clothing");
		productEntity1.setDescription("cotton comfy trackpants");
		productEntity1.setImage("image.jpeg/");
		productEntity1.setPrice(1500.00);
		productEntity1.setProdId(1);
		productEntity1.setProductName("summer trackpants");
		productEntity1.setRating(4);
		productEntity1.setSellerId(7);
		productEntity1.setStock(15);
		productEntity1.setSubcategory("men trackpants");
	
		ProductEntity productEntity2 = new ProductEntity();
		productEntity2.setBrand("nike");
		productEntity2.setCategory("Clothing");
		productEntity2.setDescription("cotton comfy trackpants");
		productEntity2.setImage("image.jpeg/");
		productEntity2.setPrice(2000.00);
		productEntity2.setProdId(2);
		productEntity2.setProductName("summer trackpants");
		productEntity2.setRating(3);
		productEntity2.setSellerId(8);
		productEntity2.setStock(20);
		productEntity2.setSubcategory("men trackpants");
		
		List<ProductEntity> products = new ArrayList<>();
		products.add(productEntity1);
		products.add(productEntity2);
		
		Mockito.when(productRepository.findByCategory("Clothing")).thenReturn(products);
		List<ProductDTO> productDTO = productService.getProductByCategory("Clothing");
		Assertions.assertEquals(products.isEmpty(), productDTO.isEmpty());;
		

	}
	
	@Test
	public void getProductsByNameTest() throws Exception {
		ProductEntity productEntity1 = new ProductEntity();
		productEntity1.setBrand("adidas");
		productEntity1.setCategory("Clothing");
		productEntity1.setDescription("cotton comfy trackpants");
		productEntity1.setImage("image.jpeg/");
		productEntity1.setPrice(1500.00);
		productEntity1.setProdId(1);
		productEntity1.setProductName("FabHomeDecor Fabric Double Sofa Bed");
		productEntity1.setRating(4);
		productEntity1.setSellerId(7);
		productEntity1.setStock(15);
		productEntity1.setSubcategory("men trackpants");
	
		ProductEntity productEntity2 = new ProductEntity();
		productEntity2.setBrand("nike");
		productEntity2.setCategory("Clothing");
		productEntity2.setDescription("cotton comfy trackpants");
		productEntity2.setImage("image.jpeg/");
		productEntity2.setPrice(2000.00);
		productEntity2.setProdId(2);
		productEntity2.setProductName("FabHomeDecor Fabric Double Sofa Beds");
		productEntity2.setRating(3);
		productEntity2.setSellerId(8);
		productEntity2.setStock(20);
		productEntity2.setSubcategory("men trackpants");
		
		List<ProductEntity> products = new ArrayList<>();
		products.add(productEntity1);
		products.add(productEntity2);
		
		Mockito.when(productRepository.findByProductName("FabHomeDecor Fabric Double Sofa Bed")).thenReturn(products);
		List<ProductDTO> productDTO = productService.getProductByName("FabHomeDecor Fabric Double Sofa Bed");
		Assertions.assertNotEquals(productDTO.size(),0);
		
		
	}
	
	
}