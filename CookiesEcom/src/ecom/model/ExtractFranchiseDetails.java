package ecom.model;

public class ExtractFranchiseDetails {

	private User user;
	private FranchisePins franchisePins;
	private Commission commission;
	
	public ExtractFranchiseDetails() {
		user          = new User();
		franchisePins = new FranchisePins();
		commission    = new Commission();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FranchisePins getFranchisePins() {
		return franchisePins;
	}

	public void setFranchisePins(FranchisePins franchisePins) {
		this.franchisePins = franchisePins;
	}

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}
	
	
	
}
