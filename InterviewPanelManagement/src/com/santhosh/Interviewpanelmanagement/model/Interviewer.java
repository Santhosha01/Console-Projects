package com.santhosh.Interviewpanelmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Interviewer {
	private String interviewerName;
	private int interviewerId;
	private String mailId;
	private String password;
	private String status;
	private int count;

	private List<Candidate> candidates = new ArrayList<Candidate>();
	
	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public int getInterviewId() {
		return interviewerId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewerId = interviewId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidate) {
		this.candidates=candidate;	
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
