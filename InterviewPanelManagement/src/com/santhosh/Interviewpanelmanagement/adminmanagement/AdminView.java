package com.santhosh.Interviewpanelmanagement.adminmanagement;

import java.util.List;
import java.util.Scanner;

import com.santhosh.Interviewpanelmanagement.interviewermanagement.InterviewerManagementView;
import com.santhosh.Interviewpanelmanagement.login.LoginView;
import com.santhosh.Interviewpanelmanagement.mappingCandidateandInterviewerManagement.mappingCandidateandInterviewerView;
import com.santhosh.Interviewpanelmanagement.model.Candidate;
import com.santhosh.Interviewpanelmanagement.model.Company;
import com.santhosh.Interviewpanelmanagement.storagemanagement.DatabaseSystem;

public class AdminView {
	private AdminModel adminModel;
	private mappingCandidateandInterviewerView mappingCandidateandInterviewerView = new mappingCandidateandInterviewerView();
	private InterviewerManagementView interviewerManagementView;
	Scanner sc = new Scanner(System.in);

	public AdminView() {
		adminModel = new AdminModel(this);
		interviewerManagementView = new InterviewerManagementView();
	}

	public void onSetupComplete() {
		DatabaseSystem.getInstance().readInterviewers();
		DatabaseSystem.getInstance().readCandidate();
		try {
		while (true) {
			System.out.println("\n--------------Application Features---------------");
			System.out.println(
					"\n 1.Add Candidate \n 2.View Candidates \n 3.Add Interviewer \n 4.View Interviewers \n 5.Avaliable Interviewer \n 6.Map Candidate and Interviewer \n 7.Pending Candidates \n 8.Selected Candidates \n 9.Company Details \n 10.Log out ");
			System.out.println("\nEnter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				getCandidateDetails();
				break;
			case 2:
				showCandidates();
				break;
			case 3:
				getInterviewerDetails();
				break;
			case 4:
				mappingCandidateandInterviewerView.showInterviewersDetail();
				break;
			case 5:
				mappingCandidateandInterviewerView.avaliableInterviewer();
				break;
			case 6:
				mappingCandidateandInterviewerView.mapCandidateandInterviewer();
				break;
			case 7:
				mappingCandidateandInterviewerView.pendingCandidates();
				break;
			case 8:
				interviewerManagementView.showSelectedCandidate();
				break;
			case 9:
				getCompanyDetails();
				break;
			case 10:
				System.out.println("Log out Successfully");
				new LoginView().startMenu();
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}
		}
		catch(Exception e) {
			sc.nextLine();
			onSetupComplete();
		}

	}

	private void getCompanyDetails() {
		Company company=DatabaseSystem.getInstance().getCompany();
        System.out.println(company.toString());	
	}

	public void getCandidateDetails() {
		try {
			System.out.println("-------------Get Candidate Details---------------");
			System.out.println("\nEnter Candidate Name");
			String candidateName = sc.nextLine();
			System.out.println("Enter Candidate Mail ID");
			String mailId = sc.nextLine();
			System.out.println("Enter Candidate Phone Number");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter Candidate Skill");
			String skill = sc.nextLine();
			System.out.println("Enter Candidate Location");
			String location = sc.nextLine();
			System.out.println("Enter the Status");
			String status=sc.nextLine();
			if(adminModel.checkMailId(mailId)&&adminModel.checkPhoneNumber(phoneNumber)) {
				adminModel.setCandidateDetails(candidateName, mailId, phoneNumber, skill, location,status);
			}
			else {
				System.out.println("Invalid Mail Id or Phone Number");
				getCandidateDetails();
			}
		} catch (Exception e) {
			getCandidateDetails();
		}
	}

	public void showAlert(String alert) {
		System.out.println(alert);
	}

	private void showCandidates() {
		System.out.println("\n---------------List of Candidates-------------\n");
		int i = 1;
		List<Candidate> candidates = DatabaseSystem.getInstance().showCandidates();
		if(candidates.isEmpty()) {
			System.out.println("No Candidates");
			onSetupComplete();
		}
		else {
		for (Candidate candidate : candidates) {
			System.out.println(i++ + "." + candidate.getCandidateName() + " - " + candidate.getSkill());
		}
		}
	}
  
	public void getInterviewerDetails() {
		try {
		System.out.println("Enter Interviewer Name");
		String interviewerName=sc.nextLine();
		System.out.println("Enter your Mail ID");
		String mailId=sc.nextLine();
		System.out.println("Enter your Password");
		String password=sc.nextLine();
		System.out.println("Enter Interviewer Status");
		String status=sc.nextLine();
		if(adminModel.checkMailId(mailId)) {
			adminModel.setInterviewerDetails(interviewerName, mailId, password, status);
		}
		else {
			System.out.println("Invalid Mail Id or Phone Number");
			getInterviewerDetails();
		}
		}
		catch(Exception e)
		{
			getInterviewerDetails();
		}
	}
}
