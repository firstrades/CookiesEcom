package ecom.Exception.project;

public class OutOfStockException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Stock is not available for this product.";
	}
}
