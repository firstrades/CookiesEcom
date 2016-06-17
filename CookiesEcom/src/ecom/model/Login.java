package ecom.model;

import java.io.Serializable;

public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String password;
	
	public Login() {
		this.userId   = null;
		this.password = null;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
