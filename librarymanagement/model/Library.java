package com.santhosh.librarymanagement.model;

import java.util.List;

public class Library {

	private String libraryName;
	private long phoneNumber;
	private String mailId;
	private String address;

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return " Library Details :\n\n library Name = " + libraryName + "\n phone Number = " + phoneNumber + "\n Mail Id = " + mailId
				+ "\n address = " + address;
	}

}
