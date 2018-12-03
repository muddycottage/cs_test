package cs_test;

import java.util.HashMap;
import java.util.Map;

// in the real world, this class would extend something like JpaRepository
public class PricingStructureRespository {
	
	Map <String, PricingStructure> pricingData ;
	
	public PricingStructureRespository () {
		// simulate data held in a DB somewhere
		createPricingData();
	}

	public PricingStructure find(String name) {
		// be null safe ....
		return (pricingData == null) ? null : pricingData.get(name) ;
	}
	
	// utility methods needed because we don't have a real source for this data (e.g. a DB)
	private void createPricingData () {
		addPriceData ("Apple", null, new Double("0.35"), null, null) ;
		addPriceData ("Banana", null, new Double("0.20"), null, null) ;
		addPriceData ("Melon", null, new Double("0.50"), 2L, 1L) ;
		addPriceData ("Lime", null, new Double("0.15"), 3L, 2L) ;
	}
	
	private void addPriceData (String productName, String groupName, Double price, Long groupCount, Long lowerCount) {
		PricingStructure pricingStructure = new PricingStructure(productName, price, groupCount, lowerCount) ;
		
		if (pricingData == null) {
			pricingData = new HashMap<>() ;
		}
		
		pricingData.put(productName, pricingStructure) ;
	}
}
