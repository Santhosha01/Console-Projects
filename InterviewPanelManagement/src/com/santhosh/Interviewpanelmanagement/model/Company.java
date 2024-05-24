package com.santhosh.Interviewpanelmanagement.model;

public class Company {
	private String companyName;
	private String companyMailId;
	private long phoneNumber;
	private String address;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyMailId() {
		return companyMailId;
	}

	public void setCompanyMailId(String companyMailId) {
		this.companyMailId = companyMailId;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Company Details :\n \n Company Name = " + companyName + "\n Company Mail Id = " + companyMailId
				+ "\n phone Number = " + phoneNumber + "\n Address = " + address;
	}

}
