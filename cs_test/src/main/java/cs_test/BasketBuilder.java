package cs_test;

import java.util.ArrayList;
import java.util.List;

public class BasketBuilder {
	
	private PricingService pricingService = new PricingService() ;
	
	private List<BasketItem> basketItems ;
	private List<String> promotionSavings ;
	private Double totalPrice ;
	
	public BasketBuilder () {
		totalPrice = 0.0 ;
		basketItems = null ;
	}
	
	public BasketBuilder accumulate (String productName, Long itemCount) {
		//System.out.println(String.format("BB called for %s %d", productName, itemCount)) ;
		
		BasketItem basketItem = pricingService.getBasketItem(productName, itemCount) ;
		addBasketItem(basketItem) ;

		Double price = basketItem.getPrice() ;
		if (price != null)
			totalPrice += price ;
		
		String promotionSavings = basketItem.getPromotion() ;
		if (promotionSavings != null)
			addPromotionSavings(promotionSavings) ;
		
		return this ;
	}
	
	public BasketBuilder called (String s, Object obj) {
		System.out.println(String.format("BB called for % with %s", s, obj)) ;
		return this ;
	}
	
	public BasketBuilder called (String s) {
		System.out.println(String.format("BB called for %", s)) ;
		return this ;
	}

	private void addBasketItem (BasketItem basketItem) {
		if (basketItems == null)
			basketItems = new ArrayList<>() ;
		
		basketItems.add(basketItem) ;
	}
	
	private void addPromotionSavings (String promotionSaving) {
		if (promotionSavings == null)
			promotionSavings = new ArrayList<>() ;
		
		promotionSavings.add(promotionSaving) ;
		
	}
	
	public String toString () {
		return String.format("BB %s %f", (basketItems == null) ? "BS=NULL" : basketItems.size(), totalPrice) ;
	}
	
	public void printBasket() {
		System.out.println("Basket summary") ;
		basketItems.stream().forEach(System.out::println);
		
		promotionSavings.stream().forEach(System.out::println);
		
		System.out.println("Total price " + totalPrice);
	}
}
