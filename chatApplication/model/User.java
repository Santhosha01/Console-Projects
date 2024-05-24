package chatApplication.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String phoneNumber;
	private String mail;
	private String password;
	private String status;
	private List<Messages> sendedmessages=new ArrayList<>();
	private List<Messages> receivedmessages=new ArrayList<>();
	private List<String> friends = new ArrayList<>();
    private List<Request> RequestList=new ArrayList<>();

	public List<Request> getRequestList() {
		return RequestList;
	}

	public void setRequestList(Request requestList) {
		RequestList.add(requestList);
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends.add(friends) ;
	}

	public List<Messages> getSendedmessages() {
		return sendedmessages;
	}

	public void addSendedmessages(Messages message) {
		this.sendedmessages.add(message);
	}

	public List<Messages> getRecievedmessages() {
		return receivedmessages;
	}

	public void addRecievedmessages(Messages recievedmessages) {
		this.receivedmessages.add(recievedmessages);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
