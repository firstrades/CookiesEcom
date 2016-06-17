package ecom.model;

public class ExtractDistributorDetails {

	private User user;
	private PinAreaMap pinAreaMap;
	private Commission commission;
	
	public ExtractDistributorDetails() {
		user          = new User();
		pinAreaMap    = new PinAreaMap();
		commission    = new Commission();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PinAreaMap getPinAreaMap() {
		return pinAreaMap;
	}

	public void setPinAreaMap(PinAreaMap pinAreaMap) {
		this.pinAreaMap = pinAreaMap;
	}

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}
	
	
}
