package cs_test;

public class PricingService {
	
	PricingStructureRespository pricingStructureRespository ;
	
	public PricingService () {
		pricingStructureRespository = new PricingStructureRespository() ;
	}
	
	BasketItem getBasketItem (String productName, Long itemCount) {
		BasketItem basketItem = new BasketItem (productName, itemCount) ;
		
		// get the pricing structure for this product
		PricingStructure pricingStructure = pricingStructureRespository.find(productName);
		if (pricingStructure != null) {
			Double price = pricingStructure.calculateGroupPrice(itemCount);
			basketItem.setPrice(price) ;
		
			String promotion = pricingStructure.getPromotion(itemCount) ;
			basketItem.setPromotion(promotion) ;
		}

		return basketItem ;
	}
	Double getSubTotal (String productName, Long itemCount) {
		
		Double subTotal ;
		
		// get the pricing structure for this product
		PricingStructure pricingStructure = pricingStructureRespository.find(productName) ;
		if (pricingStructure != null) {
			subTotal = pricingStructure.calculateGroupPrice (itemCount) ;
		}
		else {
			// this product is not known ...
			// in the real world, we'd expect to take some more informed action, such as raising a warning
			// for this exercise just return zero to avoid having to insert null tests in the caller code ...
			subTotal = 0.0 ;
		}
		
		return subTotal ;
	}
}
