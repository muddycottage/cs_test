package cs_test;

public class BasketItem {
	private String state ;
	private String productName ;
	private Long itemCount ;
	private Double price ;
	
	private String promotion ;
	
	public BasketItem () {
		state = "0" ;
		System.out.println("BI : " + this.toString() ) ;
	}
	
	public BasketItem (String productName, Long itemCount) {
		state = "1" ;
		this.productName = productName ;
		this.itemCount = itemCount ;
		
//		System.out.println("BI " + this.toString());
	}
	
	public BasketItem (String productName, Long itemCount, Double price) {
		state = "1" ;
		this.productName = productName ;
		this.itemCount = itemCount ;
		this.price = price ;
		
		System.out.println("BI " + this.toString());
	}
	
	public BasketItem (BasketItem other, String productName, Long itemCount, Double price) {
		state = "2" ;
		this.productName = productName ;
		this.itemCount = itemCount ;
		this.price = price ;
		
		System.out.println("OTHER BI " + other.toString());
		System.out.println(" THIS BI " + this.toString());
	}
	
	public BasketItem (BasketItem bs1, BasketItem bs2) {
		state = "3" ;
		this.productName = bs1.productName ;
		this.itemCount = bs1.itemCount ;
		this.price = bs1.price ;
		
		System.out.println("BI1 " + bs1.toString());
		System.out.println("BI2 " + bs2.toString());

	}
	
	
	public String toString () {
		return String.format("BI (%s) %s %d %f", state, productName, itemCount, price) ;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

}
