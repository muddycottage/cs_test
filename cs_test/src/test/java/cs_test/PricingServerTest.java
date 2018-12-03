package cs_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PricingServerTest {

	@InjectMocks
	PricingService pricingService ;
	
	@Mock
	PricingStructureRespository pricingStructureRespository ;
	
	@Before
	public void setup() {
		doAnswer((i) -> { return null ; }).when(pricingStructureRespository).find(eq("Curry")) ;
		doAnswer((i) -> { return new PricingStructure("Apple", new Double("0.40"), null, null) ; }).when(pricingStructureRespository).find(eq("Apple")) ;
		doAnswer((i) -> { return new PricingStructure("Lime", new Double("0.25"), 3L, 2L) ; }).when(pricingStructureRespository).find(eq("Lime")) ;
		
	}
	@Test
	public void testPricingServer () {
		Double result ;
		
		result = pricingService.getSubTotal("Soup", 4L) ;
		assertEquals(new Double("0.0"), result);
		
		result = pricingService.getSubTotal("Curry", 4L) ;
		assertEquals(new Double("0.0"), result);
		
		result = pricingService.getSubTotal("Apple", 0L) ;
		assertEquals(new Double("0.0"), result);
		result = pricingService.getSubTotal("Apple", 1L) ;
		assertEquals(new Double("0.4"), result);
		result = pricingService.getSubTotal("Apple", 2L) ;
		assertEquals(new Double("0.8"), result);
		
		result = pricingService.getSubTotal("Lime", 1L) ;
		assertEquals(new Double("0.25"), result);
		result = pricingService.getSubTotal("Lime", 2L) ;
		assertEquals(new Double("0.50"), result);
		result = pricingService.getSubTotal("Lime", 3L) ;
		assertEquals(new Double("0.50"), result);
		result = pricingService.getSubTotal("Lime", 4L) ;
		assertEquals(new Double("0.75"), result);
		result = pricingService.getSubTotal("Lime", 5L) ;
		assertEquals(new Double("1.00"), result);
		result = pricingService.getSubTotal("Lime", 6L) ;
		assertEquals(new Double("1.00"), result);
	}
}
