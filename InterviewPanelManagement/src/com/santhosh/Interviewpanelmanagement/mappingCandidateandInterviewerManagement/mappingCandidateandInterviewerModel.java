package com.santhosh.Interviewpanelmanagement.mappingCandidateandInterviewerManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.santhosh.Interviewpanelmanagement.adminmanagement.AdminView;
import com.santhosh.Interviewpanelmanagement.model.Candidate;
import com.santhosh.Interviewpanelmanagement.model.Interviewer;
import com.santhosh.Interviewpanelmanagement.storagemanagement.DatabaseSystem;

public class mappingCandidateandInterviewerModel {

	private mappingCandidateandInterviewerView interviewerManageView;

	public mappingCandidateandInterviewerModel(mappingCandidateandInterviewerView interviewerManageView) {
		this.interviewerManageView = interviewerManageView;
	}
	public boolean checkInterviewer(String interviewerName) {
		List<Interviewer> interviewers = DatabaseSystem.getInstance().getAllInterviewer();
		for (Interviewer interviewer : interviewers) {
			if(interviewer.getCount()!=3) {
			if(interviewerName.equals(interviewer.getInterviewerName())) {
				interviewer.setCount(interviewer.getCount()+1);
				return true;
			}
			}
			else {
				System.out.println("Interviewer Slots Filled");
				new AdminView().onSetupComplete(); 
			}
		}
		return false;
	}

	public Candidate checkCandidate(String candidateName) {
		List<Candidate> candidates = DatabaseSystem.getInstance().showCandidates();
		for (Candidate candidate : candidates) {
			if (candidateName.equals(candidate.getCandidateName())) {
				return candidate;
			}
		}
		return null;
	}

	public void mapCandidateInterviewer(String candidateName,String intervieweName) {
		List<Interviewer> interviewers=DatabaseSystem.getInstance().getAllInterviewer();
		for(Interviewer interviewer : interviewers)
		{
			if((interviewer.getInterviewerName()).equals(intervieweName)&&(interviewer.getStatus()).equals("Free")) {
				if(interviewer.getCount()==3) {
				interviewer.setStatus("Allocated");
				}
				List<Candidate> cands=DatabaseSystem.getInstance().showCandidates();
				List<Candidate> candidates=new ArrayList<Candidate>();
				for(Candidate c:cands) {
					if(c.getCandidateName().equals(candidateName)) {
						c.setStatus("Allocated");
						candidates.add(c);
					}
				}
				interviewer.setCandidates(candidates);
				DatabaseSystem.getInstance().writeCandidates(candidates);
			DatabaseSystem.getInstance().insertMapedcandidate(interviewer);
			}
			else {
				
			}
			}
		}	
}
