package project.infy.productMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@Column(name="prodid",nullable=false)
	Integer prodId;
	@Column(name="brand",nullable=false)
	String brand;
	@Column(name="category",nullable=false)
	String category;
	@Column(name="description",nullable=false)
	String description;
	@Column(name="image",nullable=false)
	String image;
	@Column(name="price",nullable=false)
	Double price;
	@Column(name="productname",nullable=false)
	String productName;
	@Column(name="rating")
	Integer rating;
	@Column(name="sellerid",nullable=false)
	Integer sellerId;
	@Column(name="stock",nullable=false)
	Integer stock;
	@Column(name="subcategory")
	String subcategory;
	
	
	
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodid) {
		this.prodId = prodid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerid) {
		this.sellerId = sellerid;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

}
