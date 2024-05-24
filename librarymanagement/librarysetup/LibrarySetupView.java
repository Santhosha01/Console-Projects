package com.santhosh.librarymanagement.librarysetup;

import java.util.Scanner;

import com.santhosh.librarymanagement.LibraryManagementApplication;
import com.santhosh.librarymanagement.bookmanagement.BookManagementView;
import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.login.LoginView;
import com.santhosh.librarymanagement.manageUser.UserView;
import com.santhosh.librarymanagement.model.Library;

public class LibrarySetupView {

	private LibrarySetupModel librarySetupModel;
	Scanner sc = new Scanner(System.in);
	public LibrarySetupView() {
		librarySetupModel = new LibrarySetupModel(this);
	}

	public void init() {
		librarySetupModel.startSetup();
	}

	public void onSetupComplete() {
		
		BookManagementView bookManagementView = new BookManagementView();
		while (true) {
			System.out.println("\n----------------Admin Page-------------------");
			System.out.println(
					"\n 1.Add Book \n 2.View Books \n 3.Search Book \n 4.Delete Book \n 5.Update Book \n 6.Manage User \n 7.View Users \n 8.show Issued Books \n 9.Library Details \n 10.Log out ");
			System.out.println("\nEnter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				bookManagementView.getBookDetails();
				break;
			case 2:
				bookManagementView.showBooks();
				break;
			case 3:
				bookManagementView.searchBook();
				break;
			case 4:
				bookManagementView.deleteBook();
				break;
			case 5:
				bookManagementView.updateBook();
			case 6:
				new UserView().manageUser();
				break;
			case 7:
				new UserView().viewUsers();
				break;
			case 8:
				bookManagementView.showIssuedBooks();
				break;
			case 9:
                viewLibraryDetails();  
                break;
			case 10:
				System.out.println(
						"\n-- Thanks for using " + LibraryManagementApplication.getInstance().getAppName() + " --");
				new LoginView().startMenu();
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}

	}

	void viewLibraryDetails() {
		Library library=LibraryDatabase.getInstance().getLibraryDetails();
		System.out.println(library.toString());
		onSetupComplete();
	}

	public void showAlert(String alert) {
		System.out.println(alert);
	}

	public void initiateSetup() {
		try {
			System.out.println("--------------Library Details---------------");
			System.out.println("\nEnter the Library Name");
			String libraryName = sc.nextLine();
			System.out.println("Enter the Library Mail ID");
			String mailId = sc.nextLine();
			System.out.println("Enter the Library Contact Number");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter the Library Address");
			String address = sc.nextLine();
			if (librarySetupModel.checkMailId(mailId) && librarySetupModel.checkPhoneNumber(phoneNumber)) {
				librarySetupModel.setLibraryDetails(libraryName, phoneNumber, mailId, address);
			} else {
				System.out.println("Invalid Mail Id or Phone Number");
				initiateSetup();
			}

		} catch (Exception e) {
			initiateSetup();
		}	
	}
}
