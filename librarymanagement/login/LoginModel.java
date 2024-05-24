package com.santhosh.librarymanagement.login;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.Credentials;

public class LoginModel {
	private LoginView loginView;
	private Credentials credentials;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	public void validateAdmin(String adminName, String password)  {
		if (isValidAdminName(adminName)) {
			if (isValidPassword(password)) {
				System.out.println("");
				loginView.startLibrarySetup();
			} else {
				loginView.showAlert("Invalid Password");
				loginView.adminLogin();
			}
		} else {
			loginView.showAlert("Invalid Admin Name");
			loginView.adminLogin();
		}
	}
	public void init()  {
		if (credentials == null) {
			loginView.init();
		} else {
			loginView.adminLogin();
		}
	}

	public void createCredentials(Credentials credential) {
		LibraryDatabase.getInstance().insertCredentials(credential);
		loginView.adminLogin();
	}
	
	private boolean isValidAdminName(String adminName) {
		return adminName.equals(LibraryDatabase.getInstance().getCredentials().getUserName());
	}

	private boolean isValidPassword(String password) {
		return password.equals(LibraryDatabase.getInstance().getCredentials().getPassword());
	}

}
