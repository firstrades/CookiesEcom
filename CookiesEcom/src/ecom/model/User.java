package ecom.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Login    login;		
	private Person   person;	
	private Contact  contact;	
	private Address  address;	
	private UserInfo userInfo;
	
	public User() {
		this.login    = new Login();
		this.person   = new Person();
		this.contact  = new Contact();
		this.address  = new Address();
		this.userInfo = new UserInfo();
	}

	public Login getLogin() {
		return login;
	}

	public Person getPerson() {
		return person;
	}

	public Contact getContact() {
		return contact;
	}

	public Address getAddress() {
		return address;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	
	
	
}
