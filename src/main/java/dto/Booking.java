package dto;

import org.apache.commons.lang3.RandomStringUtils;

public class Booking {
	
	private String firstName;
	private String lastName;
	private String mail;
	private String mobile;
	private String country;
	private String address;
	private String ticketPrice;
	
	public Booking() {
		firstName = RandomStringUtils.randomAlphabetic(10);
		lastName = RandomStringUtils.randomAlphabetic(10);
		address = RandomStringUtils.randomAlphabetic(10);
		mail = RandomStringUtils.randomAlphabetic(5) + "@randommail.com";
		mobile = RandomStringUtils.randomNumeric(8);
		country = "Spain";
	}
	
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
