package cs_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class MainClass {
	private PricingService pricingService = new PricingService() ;
	
	public static void main(String[] args) {
		MainClass mainClass = new MainClass() ;
		
		mainClass.doit();
	}
	
	private void doit() {

		// create a basket of purchases
		List<String> basket = createBasket() ;
		
		// create a map of products with counts of each
		Map<String, Long> productCounts = basket.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
		
		// Note to keep this exercise duration short, we are using a Double to hold the basket total.
		// In the real-world one would use BigDecimal or JavaMoney to manage precision and rounding,
		// but this would require a custom collector instead of the summingDouble (which is available out of the box)
		//
		// This custom collector might not simply add the chosen numeric representation but could be extended to
		// return useful stuff like "could not find product X" and "you saved £x.xx on the offers"
		
		Double basketTotal ;

		// a variety of approaches to calculating the total

		basketTotal = productCounts.entrySet().stream().collect(Collectors.summingDouble(e -> pricingService.getSubTotal(e.getKey(), e.getValue()))) ;
		System.out.println(String.format("Total value of basket : %f", basketTotal)) ;
		
		
		BiFunction <String, Long, Double> calculateSubTotal = (ss, dd) -> pricingService.getSubTotal(ss, dd) ;
		basketTotal = productCounts.entrySet().stream().collect(Collectors.summingDouble(e -> calculateSubTotal.apply(e.getKey(), e.getValue()))) ;
		System.out.println(String.format("Total2 %f", basketTotal)) ;
		
		// old school ....
		
		basketTotal = new Double("0.0") ;
		for (Map.Entry<String, Long> mapEntry : productCounts.entrySet()) {
			String productName = mapEntry.getKey() ;
			Long itemCount = mapEntry.getValue() ;
			Double subTotal = pricingService.getSubTotal(productName, itemCount) ;

			if (subTotal != null)
				basketTotal += subTotal ;
			
			// show how the basket price being built up ....
			System.out.println(String.format("%s %s %f %f", productName, itemCount, subTotal, basketTotal));
		}
		
		System.out.println();
	}
	
	// utility methods
	
	private List<String> createBasket () {
		List<String> purchases = new ArrayList<> () ;
		purchases.add("Apple") ;
		purchases.add("Banana") ;
		purchases.add("Apple") ;
		purchases.add("Melon") ;
		purchases.add("Melon") ;
		purchases.add("Melon") ;
		
		purchases.add("Banana") ;
		purchases.add("Soup") ;
		purchases.add("Lime") ;
		purchases.add("Lime") ;
		purchases.add("Lime") ;
		purchases.add("Apple") ;
		purchases.add("Curry") ;
		
		return purchases ;
	}
}

