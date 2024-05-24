package com.santhosh.Interviewpanelmanagement.adminmanagement;

import com.santhosh.Interviewpanelmanagement.mappingCandidateandInterviewerManagement.mappingCandidateandInterviewerView;
import com.santhosh.Interviewpanelmanagement.model.Candidate;
import com.santhosh.Interviewpanelmanagement.model.Interviewer;
import com.santhosh.Interviewpanelmanagement.storagemanagement.DatabaseSystem;

public class AdminModel {
	private AdminView adminView;
	private static int candidateId=1;
	private static int interviewerId=1;
	private mappingCandidateandInterviewerView interviewerManageView;

	public AdminModel(AdminView adminView) {
		this.adminView = adminView;
		interviewerManageView = new mappingCandidateandInterviewerView();
	}

	public void setCandidateDetails(String candidateName, String mailId, long phoneNumber,
			String skill, String location,String status) {
		Candidate candidate = new Candidate();
		candidate.setCandidateName(candidateName);
		candidate.setCandidateId(candidateId++);
		candidate.setMailId(mailId);
		candidate.setPhoneNumber(phoneNumber);
		candidate.setSkill(skill);
		candidate.setLocation(location);
		candidate.setStatus(status);
		createCandidate(candidate);
	}

	public void createCandidate(Candidate candidate) {
		if(!DatabaseSystem.getInstance().insertCandidate(candidate)) {
		  System.out.println("Candidate Already Exist");
		}
		else {
		adminView.showAlert("\nCandidate Details uploaded Successfully");
		}
	}
	public boolean checkPhoneNumber(long phoneNumber) {
		String number=""+phoneNumber;
		String phoneNumberFormat="\\d{10}";
		return number.matches(phoneNumberFormat);
	}

	public boolean checkMailId(String mailId) {
		String mail = mailId;
		String mailformat = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+$";
		return mail.matches(mailformat);
	}

	public void setInterviewerDetails(String interviewerName, String mailId, String password, String status) {
        Interviewer interviewer = new Interviewer();
        interviewer.setInterviewerName(interviewerName);
        interviewer.setInterviewId(interviewerId++);
        interviewer.setMailId(mailId);
        interviewer.setPassword(password);
        interviewer.setStatus(status);
        interviewer.setCandidates(null);
		createInterviewer(interviewer);
	}
	
	public void createInterviewer(Interviewer interviewer) {
		if(!DatabaseSystem.getInstance().insertInterviewers(interviewer)) {
			  System.out.println("Interviewer Already Exist");
			}
		else {
		adminView.showAlert("\nInterviewer Details uploaded Successfully");
		}
		}


	
}
