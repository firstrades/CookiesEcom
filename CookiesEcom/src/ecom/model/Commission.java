package ecom.model;

import java.io.Serializable;

public class Commission implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double franchiseCommission;
	private double distributorCommission;
	
	
	public double getFranchiseCommission() {
		return franchiseCommission;
	}
	public void setFranchiseCommission(double franchiseCommission) {
		this.franchiseCommission = franchiseCommission;
	}
	public double getDistributorCommission() {
		return distributorCommission;
	}
	public void setDistributorCommission(double distributorCommission) {
		this.distributorCommission = distributorCommission;
	}

	
	
	
}
