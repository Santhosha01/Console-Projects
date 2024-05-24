package com.santhosh.Interviewpanelmanagement.storagemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santhosh.Interviewpanelmanagement.model.Candidate;
import com.santhosh.Interviewpanelmanagement.model.Company;
import com.santhosh.Interviewpanelmanagement.model.Credential;
import com.santhosh.Interviewpanelmanagement.model.Interviewer;



public class DatabaseSystem {
	private static DatabaseSystem databaseSystem;
	private Company company;
	private Credential credential;
	private List<Candidate> candidates;
	private List<Interviewer> interviewers;
	private List<Candidate> selectedCandiate;
	private  List<HashMap<String, List<Candidate>>> mappedCandidates;
    ObjectMapper obj=new ObjectMapper();
	private String candidateFile= "C:\\Users\\santh\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\Candidates.json";
	private String interviewerFile= "C:\\Users\\santh\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\Interviewers.json";
	private String mappedCandidateFile= "C:\\Users\\santh\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\MappedCandidates.json";
	private String companyFile= "C:\\Users\\santh\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\Company.json";
    private String selectedCandidateFile="C:\\Users\\santh\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\SelectedCandidate.json";
	
	private DatabaseSystem() {
		this.candidates = new ArrayList<>();
		this.interviewers = new ArrayList<>();
		this.mappedCandidates = new ArrayList<>();
		this.selectedCandiate = new ArrayList<Candidate>();
	}

	public static DatabaseSystem getInstance() {
		if (databaseSystem == null) {
			databaseSystem = new DatabaseSystem();
		}
		return databaseSystem;
	}

	public boolean insertCandidate(Candidate candidate) {
		
		if(candidates.isEmpty()) {
		candidates.add(candidate);
		}
		else {
			for (Candidate cand : candidates) {
				if (candidate.getMailId().equals(cand.getMailId()))
					{return false;}
			}
			candidates.add(candidate);
		}
		return true;
	}

	public List<Candidate> showCandidates() {
	    return candidates;
	}
	
	public void readCandidate() {
		ObjectMapper candidateObj = new ObjectMapper();
		Path path=Paths.get(candidateFile);
		if(Files.exists(path)) {
			try {
			   candidates = candidateObj.readValue(new File(candidateFile),
						new TypeReference<List<Candidate>>() {});
			} catch (Exception e) {
				e.printStackTrace();
		}
		}
	}
	
	public void writeCandidates(List<Candidate> canidates) {
		ObjectMapper candidateObj = new ObjectMapper();
		try {
			File fileCandidate = new File(candidateFile);
			if (!fileCandidate.exists()) {
				fileCandidate.createNewFile();}
				candidateObj.writeValue(fileCandidate, candidates);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public boolean insertInterviewers(Interviewer interviewer) {
		
		if(interviewers.isEmpty()) {
			interviewers.add(interviewer);
			}
			else {
				for (Interviewer cand : interviewers) {
					if (interviewer.getMailId().equals(cand.getMailId()))
						{return false;}
				}
				interviewers.add(interviewer);
			}
			return true;		
	}

	public List<Interviewer> getAllInterviewer() {
		return interviewers;
	}	
	
	public void readInterviewers() {

		ObjectMapper interviewerObj = new ObjectMapper();
		Path path=Paths.get(interviewerFile);
		if(Files.exists(path)) {
		try {
		
		List<Interviewer> listOfInterviewer = interviewerObj.readValue(new File(interviewerFile), new TypeReference<List<Interviewer>>(){});
		interviewers=listOfInterviewer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	public void writeInterviewers() {
		ObjectMapper candidateObj = new ObjectMapper();
		try {
			File fileCandidate = new File(interviewerFile);
			if (!fileCandidate.exists()) {
				fileCandidate.createNewFile();}
				candidateObj.writeValue(fileCandidate, interviewers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertSelectedCandidate(Candidate candidate) {
		ObjectMapper interviewerObj = new ObjectMapper();
		try {
			File fileCandidate = new File(selectedCandidateFile);
			if (!fileCandidate.exists())
				fileCandidate.createNewFile();
			if (fileCandidate.length() > 0) {// verifying that the length of the file is not empty
              selectedCandiate = interviewerObj.readValue(new File(selectedCandidateFile), new TypeReference<List<Candidate>>() {});
				selectedCandiate.add(candidate);
				interviewerObj.writeValue(fileCandidate, selectedCandiate);
			} else { 
				selectedCandiate.add(candidate);
				interviewerObj.writeValue(fileCandidate, selectedCandiate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Candidate> showSelectedCandidate() {

		Path path=Paths.get(selectedCandidateFile);
		if(Files.exists(path)) {
		try {
			return selectedCandiate = obj.readValue(new File(selectedCandidateFile),
                    new TypeReference<List<Candidate>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
		}
		 return null;
	}

	public void insertCompanyDetails(Company company) {
		this.company = company;	
		try {
			File file = new File(companyFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, company);
		} catch (Exception e) {
		}
	}
	
	public Company getCompany() {
		ObjectMapper companyObj = new ObjectMapper();
		Path path=Paths.get(companyFile);
		if(Files.exists(path)) {
		try {
			return companyObj.readValue(new File(companyFile), Company.class);
		} catch (Exception e) {
			
		}
		}
//		else {
//			System.out.println("No Company");
//		}
		return null;
	}

	public void insertCredentials(Credential credential) {
	       this.credential=credential;
		}
		
		public Credential getCredentials() {
			return credential;
		}

		public void insertMapedcandidate(Interviewer interviewer) {
			ObjectMapper interviewerObj = new ObjectMapper();		 
			HashMap<String, List<Candidate>> mapped=new HashMap<String, List<Candidate>>();
             mapped.put(interviewer.getInterviewerName(),interviewer.getCandidates());
			try {
				mappedCandidates.add(mapped);
				String json=interviewerObj.writeValueAsString(mappedCandidates);
				FileWriter filewrite=new FileWriter(mappedCandidateFile);
				filewrite.write(json);
				filewrite.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public List<HashMap<String, List<Candidate>>> showMapedCandidates() {
			Path path=Paths.get(mappedCandidateFile);
			if(Files.exists(path)) {
			try {
					mappedCandidates = obj.readValue(new File(mappedCandidateFile),
	                    new TypeReference<List<HashMap<String, List<Candidate>>>>() {});
			}
				 catch (IOException e) {
	            e.printStackTrace();
		        }
			return mappedCandidates;
			}
			else {
				System.out.println("No Mapped Candidates");
			}
			return null;
		}

		
}
