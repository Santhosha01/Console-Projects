package com.santhosh.Interviewpanelmanagement.model;

public class Candidate {
	private String candidateName;
	private int candidateId;
	private String mailId;
	private long phoneNumber;
	private String skill;
	private String location;
	private double rating;
	private String result;
	private String status;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "\n Candidate Details :\n Candidate Name = " + candidateName + "\n Candidate ID = " + candidateId
				+ "\n Mail ID = " + mailId + "\n Phone Number = " + phoneNumber + "\n Skill = " + skill
				+ "\n location = " + location + "\n Rating = " + rating + "\n Result = " + result + "\n Status = "
				+ status;
	}

}
