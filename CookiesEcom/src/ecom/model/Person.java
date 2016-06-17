package ecom.model;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String sex;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
