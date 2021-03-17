package project.infy.productMS.dto;

import project.infy.productMS.entity.SubscribedProductEntity;

public class SubscribedProductDTO {
	
	Integer subId;
	Integer buyerId;
	Integer prodId;
	Integer quantity;

	
	
	

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "SubscribedProductDTO [subid=" + subId + ", buyerid=" + buyerId + ", productId="
				+ prodId + ", quantity=" + quantity + "]";
	}
	
	public static SubscribedProductDTO valueOf(SubscribedProductEntity subscribedProductEntity) {
		SubscribedProductDTO subscribeProductDTO = new SubscribedProductDTO();
		subscribeProductDTO.setBuyerId(subscribedProductEntity.getBuyerId());
		subscribeProductDTO.setProdId(subscribedProductEntity.getProdId());
		subscribeProductDTO.setQuantity(subscribedProductEntity.getQuantity());
		subscribeProductDTO.setSubId(subscribedProductEntity.getSubId());
		return subscribeProductDTO;
	}
	
	public SubscribedProductEntity createEntity() {
		SubscribedProductEntity subscribedProductEntity = new SubscribedProductEntity();
		subscribedProductEntity.setBuyerId(this.getBuyerId());
		subscribedProductEntity.setProdId(this.getProdId());
		subscribedProductEntity.setQuantity(this.getQuantity());
		subscribedProductEntity.setSubId(this.getSubId());
		return subscribedProductEntity;
	}

}
