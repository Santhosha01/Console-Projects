package com.santhosh.Interviewpanelmanagement.login;

import com.santhosh.Interviewpanelmanagement.adminmanagement.AdminView;
import com.santhosh.Interviewpanelmanagement.model.Company;
import com.santhosh.Interviewpanelmanagement.model.Credential;
import com.santhosh.Interviewpanelmanagement.storagemanagement.DatabaseSystem;

public class LoginModel {
	private LoginView loginView;
	private Credential credentials;
	private Company company;
	private AdminView adminView;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
		company = DatabaseSystem.getInstance().getCompany();
		adminView=new AdminView();
	}

	public void startSetup() {
		if (company == null) {
			loginView.startCompanySetup();
		} else {
			adminView.onSetupComplete();
		}
	}

	public void init() {
		if (credentials == null) {
			loginView.adminInit();
		} else {
			loginView.adminLogin();
		}
	}

	private boolean isValidAdminName(String userName) {
		return userName.equals(DatabaseSystem.getInstance().getCredentials().getUserName());
	}

	private boolean isValidPassword(String password) {
		return password.equals(DatabaseSystem.getInstance().getCredentials().getPassword());
	}

	public void validateAdmin(String adminName, String password) {
		if (isValidAdminName(adminName)) {
			if (isValidPassword(password)) {
				loginView.showAlert("\nlogin Successfully ");
				System.out.println("\n\n        Welcome to Interview Panel System\n");
				startSetup();
			} else {
				loginView.showAlert("Invalid Password");
				loginView.adminLogin();
			}
		} else {
			loginView.showAlert("Invalid Admin Name");
			loginView.adminLogin();
		}
	}

	public boolean checkPhoneNumber(long phoneNumber) {
		String number = "" + phoneNumber;
		String phoneNumberFormat = "\\d{10}";
		return number.matches(phoneNumberFormat);
	}

	public boolean checkMailId(String mailId) {
		String mail = mailId;
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		return mail.matches(regex);
	}

	public void setCompanyDetails(String companyName, long phoneNumber, String mailId, String address) {
		Company company = new Company();
		company.setCompanyName(companyName);
		company.setCompanyMailId(mailId);
		company.setPhoneNumber(phoneNumber);
		company.setAddress(address);
		createCompany(company);
	}

	public void createCompany(Company company) {
		DatabaseSystem.getInstance().insertCompanyDetails(company);
		loginView.showAlert("\nCompany Setup Completed");
		loginView.startCandidateSetup();
	}

	public void createCredentials(Credential credentials) {
		DatabaseSystem.getInstance().insertCredentials(credentials);
		loginView.adminLogin();
	}

}
