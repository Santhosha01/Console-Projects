package com.santhosh.Interviewpanelmanagement.mappingCandidateandInterviewerManagement;

import java.util.List;
import java.util.Scanner;

import com.santhosh.Interviewpanelmanagement.adminmanagement.AdminView;
import com.santhosh.Interviewpanelmanagement.model.Candidate;
import com.santhosh.Interviewpanelmanagement.model.Credential;
import com.santhosh.Interviewpanelmanagement.model.Interviewer;
import com.santhosh.Interviewpanelmanagement.storagemanagement.DatabaseSystem;

public class mappingCandidateandInterviewerView {

	private mappingCandidateandInterviewerModel mappingCandidateandInterviewerModel;
	private Credential credentail = new Credential();
	private Scanner sc = new Scanner(System.in);

	public mappingCandidateandInterviewerView() {
		mappingCandidateandInterviewerModel = new mappingCandidateandInterviewerModel(this);
	}

	public void showInterviewersDetail() {
		System.out.println("----------List of Interviewers Details----------\n");
		List<Interviewer> interviewers=DatabaseSystem.getInstance().getAllInterviewer();
		int i = 1;
		if (interviewers.isEmpty()) {
			System.out.println("No Interviewers");
			new AdminView().onSetupComplete();
		} else {
			for (Interviewer interviewer : interviewers) {
				System.out.println(i++ + "." + interviewer.getInterviewerName() + " - " + interviewer.getStatus());
			}
		}
	}

	public boolean checkAdminCredentails() {
		System.out.println("Enter Admin Name");
		String admin = sc.nextLine();
		System.out.println("Enter password");
		String password = sc.nextLine();
		if (credentail.getUserName().equals(admin) && credentail.getPassword().equals(password)) {
			return true;
		}
		return false;

	}

	public void mapCandidateandInterviewer() {
		System.out.println("Enter Candidate Name");
		String name = sc.nextLine();
		System.out.println("Enter Interviewer Name");
		String interviewerName = sc.nextLine();
		if ((mappingCandidateandInterviewerModel.checkCandidate(name)) != null) {
			if (mappingCandidateandInterviewerModel.checkInterviewer(interviewerName)) {
				mappingCandidateandInterviewerModel.mapCandidateInterviewer(name, interviewerName);
				System.out.println("Candidate Allocated to the interviewer");
				addCandidate();
			}
			else {
				System.out.println("Invalid Interviewer Name");
				mapCandidateandInterviewer();
			}
		} else {
			System.out.println("Invalid Candidate Name");
			mapCandidateandInterviewer();
		}
	}

	public void addCandidate() {
		System.out.println("Do you want to add Another Candidate to this Interviewer? yes (or) no");
		String option = sc.nextLine();
		if (("yes").equalsIgnoreCase(option)) {
			mapCandidateandInterviewer();
		} else {
			new AdminView().onSetupComplete();
		}
	}

	public void pendingCandidates() {
		System.out.println("\nPending Candidates\n");
		int index = 1;
		List<Candidate> candidates = DatabaseSystem.getInstance().showCandidates();
		for (Candidate candidate : candidates) {
			if (candidate.getStatus().equals("Pending")) {
				System.out.println(index++ + "." + candidate.getCandidateName());
			}
		}
		if(index==1) {
			System.out.println("No Pending Candidates");
		}
	}

	public void avaliableInterviewer() {
		System.out.println("    Avaliable Interviewers  \n");
		int index = 1;
		List<Interviewer> avaliableInterviewers = DatabaseSystem.getInstance().getAllInterviewer();
		for (Interviewer interviewer : avaliableInterviewers) {
			if (interviewer.getStatus().equals("Free")) {
				System.out.println(index++ + "." + interviewer.getInterviewerName());
			}
		}
	}
}
