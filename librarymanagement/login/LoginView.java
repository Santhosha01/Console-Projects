package com.santhosh.librarymanagement.login;

import java.util.Scanner;
import com.santhosh.librarymanagement.LibraryManagementApplication;
import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.librarysetup.LibrarySetupView;
import com.santhosh.librarymanagement.manageUser.UserView;
import com.santhosh.librarymanagement.model.Credentials;

public class LoginView {
	private LoginModel loginModel;
	private UserView userView = new UserView();
	Scanner sc = new Scanner(System.in);
	public LoginView() {
		loginModel = new LoginModel(this);
	}

	public void startMenu()  {
		System.out.println("\n----------------------Home Page--------------------------");
		System.out.println("\n 1.Admin \n 2.User \n 3.Exit");
		System.out.println("\nEnter your choice");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			loginModel.init();
			break;
		case 2:
			if (LibraryDatabase.getInstance().getLibrary() != null) {
				userView.UserPage();
			} else {
				System.out.println("Library is not Setup");
				startMenu();
			}
			break;
		case 3:
			System.out.println(
					"\n-- Thanks for using " + LibraryManagementApplication.getInstance().getAppName() + " --");
//			LibraryDatabase.getInstance().writeValue();
			System.exit(0);
		default:
			System.out.println("InValid Input");
			startMenu();
		}
	}
	
	public void init() {
		Credentials credentials = new Credentials();
		credentials.setUserName("zsgs");
		credentials.setPassword("admin");
		loginModel.createCredentials(credentials);
	}

	public void adminLogin(){
		System.out.println("\n----------------Login Page------------------");
		System.out.println();
		System.out.println("Enter your Admin name");
		String adminName = sc.next();
		System.out.println("Enter your Password");
		String password = sc.next();
		loginModel.validateAdmin(adminName, password);
	}

	public void showAlert(String massage) {
		System.out.println(massage);
	}

	public void startLibrarySetup() {
		System.out.println("Successfully login \n\n   Welcome to Library Managemant System");
		System.out.println();
		LibrarySetupView librarySetupView = new LibrarySetupView();
		librarySetupView.init();
	}
}
