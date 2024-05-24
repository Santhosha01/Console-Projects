package com.santhosh.librarymanagement.librarysetup;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.Library;

public class LibrarySetupModel {
	private LibrarySetupView librarySetupView;

	private Library library;

	LibrarySetupModel(LibrarySetupView librarySetupView) {
		this.librarySetupView = librarySetupView;
		library = LibraryDatabase.getInstance().getLibrary();
	}

	public void setLibraryDetails(String libraryName, long phoneNumber, String mailId, String address) throws StreamWriteException, DatabindException, IOException {
		Library library = new Library();
		library.setLibraryName(libraryName);
		library.setPhoneNumber(phoneNumber);
		library.setMailId(mailId);
		library.setAddress(address);
		createLibrary(library);
	}

	public void startSetup() {
		if (library == null || library.getLibraryName() == null) {
			librarySetupView.initiateSetup();
		} else {
			librarySetupView.onSetupComplete();
			return;
		}
	}

	public void createLibrary(Library library) {
		LibraryDatabase.getInstance().insertLibrary(library);
		librarySetupView.showAlert("\nLibrary Setup Completed");
		librarySetupView.onSetupComplete();
	}

	public boolean checkPhoneNumber(long phoneNumber) {
		String number = "" + phoneNumber;
		String phoneNumberFormat = "\\d{10}";
		return number.matches(phoneNumberFormat);
	}

	public boolean checkMailId(String mailId) {
		String mail = mailId;
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		return mail.matches(regex);
	}

}
