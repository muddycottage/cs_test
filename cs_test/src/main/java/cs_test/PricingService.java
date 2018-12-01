package cs_test;

/** PricingService - calculates the sub-total for a set of products
 * 
 * @author Christopher Atkinson
 * @version 1Dec2018
 */
public class PricingService {
	
	// in a Spring Boot (or similar) environment, this would be @Autowired
	PricingStructureRespository pricingStructureRespository ;
	
	public PricingService () {
		// if the repo is @Autowired, this constuction is not required
		pricingStructureRespository = new PricingStructureRespository() ;
	}
	
	   /**
	    * getSubTotal computes the sub-total cost of a set of product, applying any volume pricing
	    * 
	    * @param productName	Identifies the product
	    * @param itemCount		The number of items of this product in the basket
	    * @return total cost of the items or zero if the product is not recognised
	    */	
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
