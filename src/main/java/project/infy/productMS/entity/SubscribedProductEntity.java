package project.infy.productMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscribedproduct")
public class SubscribedProductEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subid",nullable=false)
	Integer subId;
	@Column(name="buyerid",nullable=false)
	Integer buyerId;
	@Column(name="prodid",nullable=false)
	Integer prodId;
	@Column(name="quantity",nullable=false)
	Integer quantity;
	
	
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subid) {
		this.subId = subid;
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerid) {
		this.buyerId = buyerid;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodid) {
		this.prodId = prodid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	

}
