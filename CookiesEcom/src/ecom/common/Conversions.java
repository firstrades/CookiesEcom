package ecom.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Conversions {

	public static UserType getEnumUserType(String userType) {
		
		switch (userType) {
		
		case "customer"    : return UserType.CUSTOMER; 
		case "seller"      : return UserType.SELLER;
		case "franchise"   : return UserType.FRANCHISE;
		case "distributor" : return UserType.DISTRIBUTOR;
		case "admin"       : return UserType.ADMIN;
		default            : return null;
		
		}	
		
	}
	
	public static Status getEnumStatus(String status) {
		
		switch (status) {
		
		case "approved"    : return Status.APPROVED; 
		case "awaiting"    : return Status.AWAITING;
		case "cancelled"   : return Status.CANCELLED;		
		default            : return null;
		
		}	
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
