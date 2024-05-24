package com.santhosh.Interviewpanelmanagement.interviewermanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.santhosh.Interviewpanelmanagement.InterviewPanelApplication;
import com.santhosh.Interviewpanelmanagement.login.LoginView;
import com.santhosh.Interviewpanelmanagement.model.Candidate;
import com.santhosh.Interviewpanelmanagement.model.Interviewer;
import com.santhosh.Interviewpanelmanagement.storagemanagement.DatabaseSystem;

public class InterviewerManagementView {
	private InterviewerManagementModel interviewerManagementModel;
	Scanner sc = new Scanner(System.in);
    Interviewer interviewer;
	public InterviewerManagementView() {
		interviewerManagementModel = new InterviewerManagementModel(this);
	}

	public void ratetheInterview() {
		interviewerLogin();
	}

	public void interviewerLogin() {
		System.out.println("\n----------------Interviewer Page------------------");
		System.out.println("\nEnter your Interviewer Name");
		String name = sc.nextLine();
		System.out.println("Enter your Password");
		String password = sc.nextLine();
		interviewerManagementModel.validateInterview(name, password);
		}

	public void interviewerOptions(String name) {
		System.out.println("\n\n        Welcome to Interview Panel System");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n--------------Application Features---------------");
			System.out
					.println("\n 1.View Mapped Candidate \n 2.View Candidate Details \n 3.Rate the Candidate \n 4.Selected Candidates \n 5.Log out ");
			System.out.println("\nEnter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				getCandidate(name);
				break;
			case 2:
				getCandidateDetails(name);
				break;
			case 3:
				initRatingProcess(name);
				break;
			case 4:
				showSelectedCandidate();
				break;
			case 5:
				System.out.println("log out Successfully");
				System.out.println(
						"\n-- Thanks for using " + InterviewPanelApplication.getInstance().getAppName() + " --");
				new LoginView().startMenu();
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}

	}

	public void showAlert(String massage) {
		System.out.println(massage);
	}

	public void initRatingProcess(String name) {
		System.out.println("--------Rating the candidate-------");
		System.out.println("Enter your Candidate Name");
		String candidateName = sc.nextLine();
		System.out.println("Enter your Rating for the Candidate");
		double rating = sc.nextDouble();
		sc.nextLine();
		if (interviewerManagementModel.isValidMappedCandidate(name,candidateName)) {
			interviewerManagementModel.setCandidateRating(name,candidateName, rating);
			System.out.println("Candiate Rating Completed");
		} else {
			System.out.println("Candidate not Mapped to this Interviewer");
			interviewerOptions(name);
		}
	}

	public void showSelectedCandidate() {
		System.out.println("------------Selected Candidate------------");
		int i = 1;
		List<Candidate> selectedCandidate = DatabaseSystem.getInstance().showSelectedCandidate();
		if(selectedCandidate==null) {
          System.out.println("Candidate Not Selected Yet");		
		}
		else {
		for (Candidate candidate : selectedCandidate) {
			System.out.println(i++ + "." + candidate.getCandidateName());
		}
		}
	}

	public void getCandidate(String name) {
		System.out.println("\nCandidate Name\n");
		int index=1;
		List<HashMap<String,List<Candidate>>> mappedCandidate=DatabaseSystem.getInstance().showMapedCandidates();
		for (HashMap<String, List<Candidate>> map : mappedCandidate) {
            for (String key : map.keySet()) {
            	if(key.equals(name)) {
                List<Candidate> candidates = map.get(key);
                for (Candidate candidate : candidates) {
                    System.out.println(index+++"."+candidate.getCandidateName());
                }
            	}
            }
        }

	}
	
	public Candidate getCandidatedetails(String candidateName) {
		List<Candidate> candidates=DatabaseSystem.getInstance().showCandidates();
		for(Candidate candidate:candidates) {
			if(candidate.getCandidateName().equals(candidateName)) {
				return candidate;
			}
		}
		return null;
	}

	
	
	public void getCandidateDetails(String name) {
		System.out.println("Enter your Candidate Name");
		String candidateName=sc.nextLine();
		List<HashMap<String,List<Candidate>>> mappedCandidate=DatabaseSystem.getInstance().showMapedCandidates();
		int flag=0;
		for (HashMap<String, List<Candidate>> map : mappedCandidate) {
            for (String key : map.keySet()) {
            	if(key.equals(name)) {
                List<Candidate> candidates = map.get(key);
                for (Candidate candidate : candidates) {
                	if(candidate.getCandidateName().equals(candidateName)) {
                		Candidate cand=getCandidatedetails(candidateName);
                		System.out.println(cand.getCandidateName());
                		System.out.println(cand.toString());
                		flag=1;
                		break;
                	}
                }
            	}
            }
        }
		if(flag==0) {
		   System.out.println("Candidate Not Found");
		}
		}
}
