package com.santhosh.librarymanagement.manageUser;

import java.util.List;
import java.util.Scanner;
import com.santhosh.librarymanagement.LibraryManagementApplication;
import com.santhosh.librarymanagement.bookmanagement.BookManagementView;
import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.librarysetup.LibrarySetupView;
import com.santhosh.librarymanagement.login.LoginView;
import com.santhosh.librarymanagement.model.User;

public class UserView {
	private UserModel userModel;
	Scanner sc = new Scanner(System.in);
	private BookManagementView bookManagementView;
    private String uName="";
	public UserView() {
		userModel=new UserModel(this);
		bookManagementView = new BookManagementView();
	}

	public void UserPage() {
		System.out.println("------------Welcome to user Page-------------");
		System.out.println(" 1.Sign up \n 2.Sign in");
		System.out.println("Enter your choice");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			userSignup();
			break;
		case 2:
			userSignin();
			break;
		default:
			System.out.println("InValid Input");
			UserPage();
		}
	}

	private void userSignin() {
		try {
			System.out.println("----------Sign in page----------");
			System.out.println("Enter your User Name");
			String userName = sc.nextLine();
			System.out.println("Enter your Password");
			String password = sc.nextLine();
			
//			System.out.println("ohhh");
//			System.out.println(userModel.searchUserName(userName));
//			System.out.println(userModel.searchUserPassword(password));
			if (userModel.searchUserName(userName)) {
				uName=userName;
				if (userModel.searchUserPassword(password)) {
					System.out.println("Login Successfully");
					showUserfeatures(uName);
				}
			} else {
				System.out.println("you dont have account,Proceed Sign up");
				userSignup();
			}
		} catch (Exception e) {
			userSignin();
		}
	}

	public void userSignup() {
		try {
			System.out.println("----------Sign Up page----------");
			System.out.println("\nEnter the following user Details: ");
			System.out.println("Enter user name:");
			String userName = sc.nextLine();
			System.out.println("Enter your Password");
			String password = sc.nextLine();
			System.out.println("Enter user Mail ID:");
			String mailId = sc.nextLine();
			System.out.println("Enter User Phone Number :");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			if (userModel.searchUserName(userName)) {
				if (userModel.searchUserPassword(password)) {
					System.out.println("you already have a Account, Proceed Sign in");
					userSignin();
				} else {
					System.out.println("Invalid Password");
					userSignin();
				}
				System.out.println("Invalid User Name");
			}
			if (userModel.checkMailId(mailId) && userModel.checkPhoneNumber(phoneNumber)) {
				userModel.setUserDetails(userName, password, mailId, phoneNumber);
			} else {
				System.out.println("Invalid Mail Id or Phone Number");
				userSignup();
			}
		} catch (Exception e) {
			userSignup();
		}
	}

	public void onUserAdded(User user) {
		System.out.println("\n------- User '" + user.getUserName() + "' added successfully ------- \n");
		userSignin();
	}

	public void onUserExist(User user) {
		System.out.println("\n------- User '" + user.getUserName() + "' already exist -------\n");
	    userSignin();
	}

	public void showUserfeatures(String user)  {
		while (true) {
			System.out.println("\n----------------Welcome to User Page-------------------");
			System.out.println("\n 1.View Books \n 2.Search Book \n 3.Get Book \n 4.View My Details \n 5.Log out ");
			System.out.println("\nEnter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				bookManagementView.showBooks();
				break;
			case 2:
				new BookManagementView().searchBook();
				break;
			case 3:
				new BookManagementView().issueBook(uName);
				break;
			case 4:
				viewUserDetails();
				break;
			case 5:
				System.out.println("Log out Successfully");
				System.out.println(
						"\n-- Thanks for using " + LibraryManagementApplication.getInstance().getAppName() + " --");
				new LoginView().startMenu();
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}
	}

	public void deleteUser() {
		System.out.println("Enter the Name of the User you want to delete");
		System.out.println(userModel.deleteUser(sc.next()));
	}

	public void viewUsers() {
		System.out.println("\nList of Users\n");
		int i = 1;
		List<User> users = LibraryDatabase.getInstance().showUsers();
		if(users==null) {
			System.out.println("No User Found");
		}
		else {
		for (User user : users) {
			System.out.println(i++ + "." + user.getUserName());
		}
		}
	}

	public boolean searchUser(String userName, String password) {
		return userModel.searchUserDetails(userName, password);
	}

	public void manageUser() {
		while (true) {
			System.out.println("\n-------User Management Page---------");
			System.out.println("\n 1.Add User \n 2.Update User \n 3.Delete user \n 4.View User \n 5.Exit ");
			System.out.println("Enter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addNewUser();
				break;
			case 2:
				updateUser();
				break;
			case 3:
				deleteUser();
				break;
			case 4:
				viewUserDetails();
				break;
			case 5:
				new LibrarySetupView().onSetupComplete();
				return;
			default:
				System.out.println("Invalid Choice");
			}
		}
	}

	private void viewUserDetails() {
		System.out.println("--------------User Details-------------");
		System.out.println("Enter User Name");
		String userName = sc.nextLine();
		userModel.ViewUserDetails(userName);
	}

	private void updateUser() {
		while (true) {
			System.out.println("------User Updation Page--------");
			System.out.println("\n 1.Update Phone Number \n 2.Update Mail Id \n 3.Exit ");
			System.out.println("Enter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				updatePhoneNumber();
				break;
			case 2:
				UpdateMailId();
				break;
			case 3:
				manageUser();
				return;
			default:
				System.out.println("Invalid Choice");
			}
		}
	}

	private void UpdateMailId() {
		System.out.println("Enter User Name");
		String userName = sc.nextLine();
		if (userModel.searchUserName(userName)) {
			System.out.println("Enter your updated Mail Id");
			String mailId = sc.nextLine();
			userModel.setUpadatedMailId(mailId, userName);
			System.out.println("Mail Id Updated Successfully");
		}
	}

	private void updatePhoneNumber() {
		try {
		System.out.println("Enter User Name");
		String userName = sc.nextLine();
		if (userModel.searchUserName(userName)) {
			System.out.println("Enter your updated Phone Number");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			userModel.setUpadatedPhoneNumber(phoneNumber, userName);
			System.out.println("Phone Number Updated Successfully");
		}
		}
		catch(Exception e) {
			updatePhoneNumber();
		}
	}

	private void addNewUser() {
		try {
			System.out.println("-------User Adding Page-------");
			System.out.println("Enter User Name:");
			String userName = sc.nextLine();
			System.out.println("Enter your Password");
			String password = sc.nextLine();
			System.out.println("Enter user Mail ID:");
			String mailId = sc.nextLine();
			System.out.println("Enter User Phone Number :");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			if (userModel.checkMailId(mailId) && userModel.checkPhoneNumber(phoneNumber)) {
				if (userModel.searchUserName(userName)) {
					if (userModel.searchUserPassword(password)) {
						System.out.println("you already have a Account, Proceed Sign in");
						manageUser();
					}
				} else {
					userModel.setUserDetails(userName, password, mailId, phoneNumber);
				}
			} else {
				System.out.println("Invalid Mail Id or Phone Number");
				addNewUser();
			}
		} catch (Exception e) {
			sc.nextLine();
			addNewUser();
		}
	}

	public void onSucess(String massage) {
		System.out.println(massage);
		showUserfeatures(uName);
	}

	public void onExist(String massage) {
		System.out.println(massage);
		userSignin();
	}

}
