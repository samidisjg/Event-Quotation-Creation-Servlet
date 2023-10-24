package s_supplier;

public class s_order {
	
	private String orderId;
	private int supplierId;
	private String orders;
	
	public s_order(String orderId, int supplierId, String orders) {
		super();
		this.orderId = orderId;
		this.supplierId = supplierId;
		this.orders = orders;
	}

	public String getOrderId() {
		return orderId;
	}

	public int getSupplierId() {
		return supplierId;
	}
	
	public String getOrders() {
		return orders;
	}
	
}
