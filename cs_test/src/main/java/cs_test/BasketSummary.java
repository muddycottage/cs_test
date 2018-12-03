package cs_test;

public class BasketSummary {

	String state ;
	
	public BasketSummary called (String s) {
		System.out.println("BS called " + s);
		return this ;
	}

	public BasketSummary () {
		state = "0" ;
		System.out.println("BS : " + this.toString() ) ;
	}
	public BasketSummary (String productName, Long itemCount, Double price) {
		state = "1" ;

		System.out.println("BS " + this.toString());
	}
	
	public BasketSummary (BasketSummary other, String productName, Long itemCount, Double price) {
		state = "2" ;
		
		System.out.println("OTHER BS " + other.toString());
		System.out.println(" THIS BS " + this.toString());
	}
	
	public BasketSummary (BasketSummary bs1, BasketSummary bs2) {
		state = "3" ;

		System.out.println("BS1 " + bs1.toString());
		System.out.println("BS2 " + bs2.toString());

	}
	
	public String toString () {
		return String.format(" : %s", state) ;
	}
}
