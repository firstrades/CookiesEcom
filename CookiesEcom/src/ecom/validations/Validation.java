package ecom.validations;

import javax.servlet.http.HttpServletRequest;

public interface Validation {
	public void setRequestParameters(String[] requestParameters);
	public String validate(HttpServletRequest request);
}
