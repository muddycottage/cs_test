package cs_test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * @author chris
 * 
 * PricingStructure supports simple item pricing and promotional offers such as 2 for 1.
 *
 */
public class PricingStructure {
	private String productName ; // the name of the item in the basket e.g. "Apple"
	private Double price ; // price of a single item
	private Long groupCount ; // number items which qualify for a price reduction - eg the "3' in "3 for 2"
	private Double priceRatio ; // ratio by which the group price is reduced = eg 2/3 for "3 for 2"
	
	public PricingStructure(String productName, Double price, Long groupCount, Double priceRatio) {
		super();
		this.productName = productName;
		this.price = price;
		this.groupCount = groupCount;
		this.priceRatio = priceRatio;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}		
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(Long groupCount) {
		this.groupCount = groupCount;
	}
	public Double getPriceRatio() {
		return priceRatio;
	}
	public void setPriceRatio(Double priceRatio) {
		this.priceRatio = priceRatio;
	}
	
	public Double calculateGroupPrice (Long itemCount) {
		Double totalPrice ;
		
		// is there a special promotion?
		if (groupCount == null) {
			// no, so just use item pricing
			totalPrice = price * itemCount;
		} else {
			// there is a special promotion, so work out how many whole groups of items
			// there are and how many items left over

			Long remainder = itemCount % groupCount;
			Long groups = itemCount / groupCount;

			totalPrice = groups * priceRatio * price + remainder * price;
		}
		
		// unashamedly, fixing the precision - in a production build I would use BigDecimal or JavaMoney - but that would require a Custom Collector...
		BigDecimal bd = new BigDecimal (totalPrice) ;
		bd = bd.setScale(2, RoundingMode.HALF_DOWN) ;
		
		return bd.doubleValue() ;
	}
}
