package com.santhosh.Interviewpanelmanagement;

import com.santhosh.Interviewpanelmanagement.login.LoginView;


public class InterviewPanelApplication {
	private static InterviewPanelApplication interviewPanel;

	private String appName = "Interview Panel System";

	private String appVersion = "0.0.1";

	public static InterviewPanelApplication getInstance() {
		if (interviewPanel == null) {
			interviewPanel = new InterviewPanelApplication();
		}
		return interviewPanel;
	}

	private void create() {
		LoginView loginView = new LoginView();
		System.out.println("--------------" + InterviewPanelApplication.getInstance().getAppName()
				+ " ---------------- \n\t\t  version " + "("
				+ InterviewPanelApplication.getInstance().getAppVersion() + ")");
		loginView.startMenu();
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static void main(String arg[]) {
		InterviewPanelApplication.getInstance().create();
	}

}