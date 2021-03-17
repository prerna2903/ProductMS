package project.infy.productMS.validator;

import project.infy.productMS.dto.ProductDTO;

public class ProductValidator {
	
	public static void validaterForAdd(ProductDTO productDTO) throws Exception {
		if(!ProductValidator.validaterForName(productDTO.getProductName())) {
			throw new Exception("Valiator.INVALID_NAME");
			
		}
		if(productDTO.getDescription().length()>500) {
			throw new Exception("Validator.INVALID_DESCRIPTION");
			
		}
		if(productDTO.getPrice()<200) {
			throw new Exception("Validator.INVALID_PRICE");
			
		}
		if(!ProductValidator.validaterForStock(productDTO.getStock())) {
			throw new Exception("Validator.INVALID_STOCK");
			
		}
		if(!ProductValidator.validaterForImage(productDTO.getImage())) {
			throw new Exception("Validator.INVALID_IMAGE");
		}
	}
	
	public static boolean validaterForName(String productName) {
		int length = productName.length();
		String regex = "[a-zA-Z]+[a-zA-Z' ']+[a-zA-Z]+";
		if(productName.matches(regex) && length <= 100) {
			return true;
		}
		return false;
	}
	
	public static boolean validaterForImage(String image) {
		int length = image.length();
		String TypeFirst = image.substring(length-4,length-1);
		String TypeSecond = image.substring(length-5,length-1);
		if(TypeFirst.equals("png")||TypeSecond.equals("jpeg")) {
			return true;
		}
		return false;
		
	}
	
	public static boolean validaterForStock(Integer stock) {
		if(stock>=10) {
			return true;
			
		}
		return false;
	}

}
