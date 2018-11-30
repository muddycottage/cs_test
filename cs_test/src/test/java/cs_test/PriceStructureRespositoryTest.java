package cs_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PriceStructureRespositoryTest {

	private PricingStructureRespository pricingStructureRespository ;
	
	@Before
	public void setup () {
		pricingStructureRespository = new PricingStructureRespository() ;
	}
	
	// in this exercise, PricingStructureRepository has a very little functionality, so there is little to test
	// but Junit requires a test ....
	@Test
	public void testIt () {
		Assert.assertTrue(true) ;
	}
}
