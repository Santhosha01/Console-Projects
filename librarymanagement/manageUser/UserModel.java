package com.santhosh.librarymanagement.manageUser;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.Credentials;
import com.santhosh.librarymanagement.model.User;

public class UserModel {
	private UserView userView;
	private static int userId=1;
	private User user = new User();

	UserModel(UserView userView) {
		this.userView = userView;
	}

	public void addNewUser(User user) {
		if (LibraryDatabase.getInstance().insertUser(user)) {
			userView.onUserAdded(user);
		} else {
			userView.onUserExist(user);
		}
	}

	public String deleteUser(String deleteUser) {
		List<User> users = LibraryDatabase.getInstance().showUsers();
//		System.out.println(users);
		for (User user : users) {
			if (user.getUserName().equals(deleteUser)) {
				users.remove(user);
				LibraryDatabase.getInstance().removeandUpdateUser(user);
				return "User Delete Successfully";
			}
		}
		
		return "User Not Found";
	}

	public boolean checkPhoneNumber(long phoneNumber) {
		String number = "" + phoneNumber;
		String phoneNumberFormat = "\\d{10}";
		return number.matches(phoneNumberFormat);
	}

	public boolean checkMailId(String mailId) {
		String mail = mailId;
		String mailformat = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+$";
		return mail.matches(mailformat);
	}

	public boolean searchUserDetails(String userName, String password) {
		List<Credentials> userCredentials = LibraryDatabase.getInstance().getUserCredentials();
		for (Credentials credential : userCredentials) {
			if (userName.equals(credential.getUserName()) && password.equals(credential.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public void setUserDetails(String userName, String password, String mailId, long phoneNumber) {
		Credentials userCredentials = new Credentials();
		user.setUserName(userName);
		user.setPassword(password);
		user.setMailId(mailId);
		user.setUserId(userId++);
		user.setPhoneNumber(phoneNumber);
		userCredentials.setUserName(userName);
		userCredentials.setPassword(password);
		LibraryDatabase.getInstance().insertUserCredentials(userCredentials);
		addNewUser(user);
	}

	public boolean searchUserName(String userName) {
		List<Credentials> users = LibraryDatabase.getInstance().getUserCredentials();
//		System.out.println(userName);
		for (Credentials user : users) {
//			System.out.println(user.getUserName());
			if ((user.getUserName()).equals(userName)) {
//				System.out.println("hello");
				return true;
			}
		}
		return false;
	}

	public User searchUser(String userName) {
		List<User> users = LibraryDatabase.getInstance().showUsers();
		for (User user : users) {
			if (userName.equals(user.getUserName())) {
				return user;
			}
		}
		return null;
	}

	public void setUpadatedMailId(String mailId, String userName) {
		User user = searchUser(userName);
		if(user!=null) {
		user.setMailId(mailId);
		List<User> users=LibraryDatabase.getInstance().showUsers();
		System.out.println(users);
		  LibraryDatabase.getInstance().removeandUpdateUser(user);
		}
		else {
			System.out.println("User Not Found");
		}
	}

	public void setUpadatedPhoneNumber(long phoneNumber, String userName) throws JsonMappingException, JsonProcessingException {
		User user = searchUser(userName);
		if(user!=null) {
			user.setPhoneNumber(phoneNumber);
	        LibraryDatabase.getInstance().removeandUpdateUser(user);	
 		}
		else {
		  System.out.println("User Not Found");	
		}
	}

	public void ViewUserDetails(String userName) {
		User user = searchUser(userName);
		if (user != null) {
			System.out.println(user.toString());
		} else {
			System.out.println("User Not Found");
		}
	}

	public boolean searchUserPassword(String password) {
		List<Credentials> users = LibraryDatabase.getInstance().getUserCredentials();
//		System.out.println(password);
		for (Credentials userCredential : users) {
			if (password.equals(userCredential.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
