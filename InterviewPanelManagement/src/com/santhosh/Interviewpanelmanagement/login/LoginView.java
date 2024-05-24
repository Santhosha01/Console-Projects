package com.santhosh.Interviewpanelmanagement.login;

import java.util.Scanner;

import com.santhosh.Interviewpanelmanagement.InterviewPanelApplication;
import com.santhosh.Interviewpanelmanagement.adminmanagement.AdminView;
import com.santhosh.Interviewpanelmanagement.interviewermanagement.InterviewerManagementView;
import com.santhosh.Interviewpanelmanagement.model.Credential;
import com.santhosh.Interviewpanelmanagement.storagemanagement.DatabaseSystem;


public class LoginView {

	private LoginModel loginModel;

	public LoginView() {
		loginModel = new LoginModel(this);
	}

	
	public void adminLogin() {
		try {
			System.out.println("\n----------------Admin Page------------------");
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter your Admin name");
			String adminName = sc.next();
			System.out.println("Enter your Password");
			String password = sc.next();
			loginModel.validateAdmin(adminName, password);
		} catch (Exception e) {
			adminLogin();
		}
	}

	public void showAlert(String massage) {
		System.out.println(massage);
	}
	
	public void startCompanySetup() {
		try {
			System.out.println("\n--------------Company Details---------------");
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter the Company Name");
			String companyName = sc.nextLine();
			System.out.println("Enter the Company Mail ID");
			String mailId = sc.nextLine();
			System.out.println("Enter the Company Contact Number");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter the Company Address");
			String address = sc.nextLine();
			if (loginModel.checkMailId(mailId) && loginModel.checkPhoneNumber(phoneNumber)) {
				loginModel.setCompanyDetails(companyName, phoneNumber, mailId, address);
			} else {
				System.out.println("Invalid Mail Id or Phone Number");
				startCompanySetup();
			}
		} catch (Exception e) {
			startCompanySetup();
		}
		}
	
	public void startCandidateSetup() {
		System.out.println("\n\n        Welcome to Interview Panel System");
		AdminView adminView = new AdminView();
//		setInterviewersDetails();
//		setCandidatesDetails();
		adminView.onSetupComplete();
	}

	public void adminInit() {
		Credential credentials = new Credential();
		credentials.setUserName("zoho");
		credentials.setPassword("interview");
		loginModel.createCredentials(credentials);
	}
	
	public void startMenu() {
		System.out.println("\n----------------------Home Page--------------------------");
		Scanner sc=new Scanner(System.in);
		System.out.println("\n 1.Admin \n 2.Interviewer \n 3.Exit");
		System.out.println("\nEnter your choice");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			loginModel.init();
			break;
		case 2:
			new InterviewerManagementView().interviewerLogin();
			break;
		case 3:
			
			DatabaseSystem.getInstance().writeCandidates(DatabaseSystem.getInstance().showCandidates());
			DatabaseSystem.getInstance().writeInterviewers();
			System.out.println(
					"\n-- Thanks for using " + InterviewPanelApplication.getInstance().getAppName() + " --");
		    System.exit(0);
			break;
		default :
			System.out.println("InValid Input");
			startMenu();
		}
	}
}
