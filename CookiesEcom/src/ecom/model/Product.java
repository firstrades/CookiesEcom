package ecom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ecom.common.Status;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected long        productId;
	protected long        sellerId;
	protected String      sellerCompany;  
	
	protected String      category;
	protected String      subCategory;
	protected String      productName;
	protected String      companyName;
	
	protected List<KeyFeature>  keyFeatures;   
	protected List<Size>        sizes;
	
	protected Price       price;
	
	protected int         stock;
	protected double      weight;
	protected String      warranty;	
	protected int         cancellationAfterBooked;
	
	protected Status      status;	                         // Enum
	protected Commission  commission;
	protected String      productAdditionDate;
	
	protected double      shippingCost;
	protected int         deliveryTime;
	
	protected Images      images;
	
	
	public Product() {
		keyFeatures = new ArrayList<>();
		sizes       = new ArrayList<>();
		price       = new Price();
		commission  = new Commission();
		images      = new Images();
	}
	
	
	
	// Getter & Setter
	
	public long getProductId() {
		return productId;
	}

	public String getProductAdditionDate() {
		return productAdditionDate;
	}

	public void setProductAdditionDate(String productAdditionDate) {
		this.productAdditionDate = productAdditionDate;
	}

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}	

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getCancellationAfterBooked() {
		return cancellationAfterBooked;
	}

	public void setCancellationAfterBooked(int cancellationAfterBooked) {
		this.cancellationAfterBooked = cancellationAfterBooked;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}	

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getSellerCompany() {
		return sellerCompany;
	}

	public void setSellerCompany(String sellerCompany) {
		this.sellerCompany = sellerCompany;
	}



	public List<KeyFeature> getKeyFeatures() {
		return keyFeatures;
	}



	public List<Size> getSizes() {
		return sizes;
	}



	public void setKeyFeatures(List<KeyFeature> keyFeatures) {
		this.keyFeatures = keyFeatures;
	}



	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}



	public double getShippingCost() {
		return shippingCost;
	}



	public int getDeliveryTime() {
		return deliveryTime;
	}



	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}



	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}



	public Images getImages() {
		return images;
	}



	public void setImages(Images images) {
		this.images = images;
	}

	
	
	
}
