package chatApplication.homepage;

import java.util.List;
import java.util.Scanner;

import chatApplication.database.Database;
import chatApplication.model.Messages;
import chatApplication.model.Request;
import chatApplication.model.User;

public class HomeModel {

	private HomeView homeView;
	static Scanner sc = new Scanner(System.in);

	public HomeModel(HomeView homeView) {
		this.homeView = homeView;
	}

	public void storeSendedMsg(String name, String frnd, String msg) {
		Messages m = new Messages();
		m.setSendUserName(name);
		m.setRecievedUserName(frnd);
		m.setMessage(msg);
		User sender = getUser(name);
		User receiver = getUser(frnd);
		if(receiver==null) {
			System.out.println(frnd +" didnt Accept the friends request");
		}
		else {
		sender.addSendedmessages(m);
        receiver.addRecievedmessages(m);
        System.out.println("Message Sent Successfully");
		}
	}
	public User getUser(String name) {
		for(User user : Database.getInstance().getUsers()) {
			if(user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public boolean checkFriend(String name, String frnd) {
		List<User> users = Database.getInstance().getUsers();
		for (User u : users) {
			if (u.getName().equals(name)) {
				for (String s : u.getFriends()) {
					if (s.equals(frnd)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void friendRequest(String name) {
		System.out.println("Enter friend Name you want to Make a Request");
		String frnd = sc.nextLine();
		if(getUser(frnd)!=null){
		List<User> users = Database.getInstance().getUsers();
		for (User u : users) {
			if (u.getName().equals(frnd)) {
				Request r=new Request();
				r.setRequesterName(name);
				r.setReceivedName(frnd);
				u.setRequestList(r);
			}
		}
		System.out.println("Friends Request Sent Successfully");}
		else{
			System.out.println("User not found");
		}
	}

	public void viewRequestList(String name){
		User u=getUser(name);
			if(u!=null){
		        if(!u.getRequestList().isEmpty()) {
					System.out.println("Requested Friends List");
					for(Request r:u.getRequestList()){
						System.out.println("* "+r.getRequesterName());
					}
					boolean flag=true;
					while(flag) {
						System.out.println("Do you Want to Accept any friend Request? Yes/No");
						String s = sc.nextLine();
						if (s.equals("Yes")) {
							System.out.println("Enter the Name you want to Accept friend Request");
							String s1 = sc.nextLine();
							User u1 = getUser(s1);
							u1.setFriends(name);
							u.setFriends(s1);
							System.out.println(s1 + " Friend Request Accepted Successfully");
						}
						else{
							flag=false;
						}
					}
				}
				else{
					System.out.println("There is No Friend Request ");
				}
			}
			else {
				System.out.println("User not Found");
			}
	}
}
