package cs_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PricingStructureTest {

	@Test
	public void testPricingStructure () {
		PricingStructure pricingStructure ;
		Double result ;
		
		// test unit pricing
		
		pricingStructure = new PricingStructure("Apple", new Double("0.40"), null, null) ;
		result = pricingStructure.calculateGroupPrice(0L) ;
		assertEquals(new Double("0.0"), result);
		result = pricingStructure.calculateGroupPrice(1L) ;
		assertEquals(new Double("0.4"), result);
		result = pricingStructure.calculateGroupPrice(2L) ;
		assertEquals(new Double("0.8"), result);
		result = pricingStructure.calculateGroupPrice(3L) ;
		assertEquals(new Double("1.2"), result);
		
		pricingStructure = new PricingStructure("Apple", new Double("0.50"), 2L, 1L) ;
		result = pricingStructure.calculateGroupPrice(0L) ;
		assertEquals(new Double("0.0"), result);
		result = pricingStructure.calculateGroupPrice(1L) ;
		assertEquals(new Double("0.5"), result);
		result = pricingStructure.calculateGroupPrice(2L) ;
		assertEquals(new Double("0.5"), result);
		result = pricingStructure.calculateGroupPrice(3L) ;
		assertEquals(new Double("1.0"), result);
		result = pricingStructure.calculateGroupPrice(4L) ;
		assertEquals(new Double("1.0"), result);
		result = pricingStructure.calculateGroupPrice(5L) ;
		assertEquals(new Double("1.5"), result);
				
		
	}
}
