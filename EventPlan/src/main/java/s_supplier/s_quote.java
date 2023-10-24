package s_supplier;

public class s_quote {
	private String quoteId;
	private String itemName;
	private double quantity;
	private double unitPrice;
	private double itemPrice;
	
	public s_quote() {
		
	}
	
	public s_quote(String quoteId,String itemName, double quantity, double unitPrice, double itemPrice) {
		super();
		this.quoteId = quoteId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.itemPrice = itemPrice;

	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	

	
}



