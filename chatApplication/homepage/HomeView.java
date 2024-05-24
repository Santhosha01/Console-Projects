package chatApplication.homepage;

import java.util.List;
import java.util.Scanner;

import chatApplication.database.Database;
import chatApplication.login.LoginView;
import chatApplication.model.Messages;
import chatApplication.model.User;

public class HomeView {
	static Scanner sc = new Scanner(System.in);
	private HomeModel homeModel;
	private LoginView loginView;

	public HomeView() {
		this.homeModel = new HomeModel(this);
		loginView =new LoginView();
	}

	public void homeInit(String name) {
		System.out.println("\n-------- Home Page -------------\n 1.View My friends \n 2.Chat With Friend \n 3.View Send Message \n 4.View Received Message \n 5.Friend Request \n 6.View Friend Request \n 7.Logout");
		System.out.println("\nEnter your choice");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			viewFriends(name);
			homeInit(name);
			break;
		case 2:
			chatWithFriend(name);
			homeInit(name);
			break;
		case 3:
			viewSendedMessages(name);
			homeInit(name);
			break;
		case 4:
			viewReceivedMessages(name);
			homeInit(name);
			break;
		case 5:
			homeModel.friendRequest(name);
			homeInit(name);
			break;
			case 6:
				homeModel.viewRequestList(name);
				homeInit(name);
				break;
		case 7:
			System.out.println("--------"+name + " Logout Successfully--------");
			loginView.init();
			break;
		default:
			System.out.println("InValid Input");
			homeInit(name);
		}
	}


	private void viewSendedMessages(String name) {
		System.out.println("Enter your Friends Name to View the Sended Messages");
		String frnd = sc.nextLine();
		if(homeModel.checkFriend(name,frnd)) {
			if(homeModel.getUser(name)!=null) {
		    System.out.println("\n--------View Chat of " + frnd+"--------");
		    User curuser=homeModel.getUser(name);
		    List<Messages> msg=curuser.getSendedmessages();
		    if(msg.isEmpty()) {
		    	System.out.println("\nNo Messages Found");
		    }
		    else {
			for(Messages m:msg) {
		    if (m.getRecievedUserName().equals(frnd)) {
				System.out.println("\n  * " + m.getMessage());
				}
		    else {
		    	System.out.println("\nNo Message Found to the User ");
		    }
			}
		}
			}
		}
		else {
			System.out.println("Friend Not found");
		}
	}
 
	private void viewReceivedMessages(String name) {
		    User curuser=homeModel.getUser(name);
		System.out.println("Enter friend Name to view Received Messages");
		String frnd = sc.nextLine();
		if (homeModel.checkFriend(name,frnd)) {
			List<Messages> msg=curuser.getRecievedmessages();
			System.out.println("\nReceived Messages from " + frnd);
			if(msg.isEmpty()) {
				System.out.println("\n   No Message Found");
			}
			else {
				for(Messages m:msg) {
					if(m.getSendUserName().equals(frnd)) {
						System.out.println("\n  * " + m.getMessage());
					}
					}
			}
		}
		else{
			System.out.println("\n  friend not found");
		}

	}
	
	private void chatWithFriend(String name) {
		System.out.println("Enter the your friend Name to start Convo");
		String frnd = sc.nextLine();
		if(homeModel.checkFriend(name,frnd)) {
		System.out.println("Enter your Message to " + frnd);
		String msg = sc.nextLine();
		homeModel.storeSendedMsg(name, frnd, msg);
		}
		else {
			System.out.println("  Friend Not Found");
		}
	}

	
	private void viewFriends(String name) {
		System.out.println("------Friends List------\n");
		List<User> users = Database.getInstance().getUsers();
		int index = 1;
		for (User u : users) {
			if ((u.getName()).equals(name)) {
				for (String s : u.getFriends()) {
					System.out.println(index++ + ". " + s);
				}
			}
		}
		if (index == 1) {
			System.out.println("  No Friends Found");
		}
	}
}
