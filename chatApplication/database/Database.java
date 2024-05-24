package chatApplication.database;

import java.util.ArrayList;
import java.util.List;

import chatApplication.model.Credentials;
import chatApplication.model.Messages;
import chatApplication.model.User;

public class Database {
	private static Database database;

	private List<Credentials> credentials = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private List<User> friends = new ArrayList<>();
	private List<Messages> usersendedMessages = new ArrayList<>();

	public static Database getInstance() {
		if (database == null) {
			database = new Database();
		}
		return database;
	}

	public List<User> getUsers() {
		return users;
	}

	public void storeUsers(User users) {
		this.users.add(users);
	}
	
	public void storeCredentials(Credentials credential) {
		credentials.add(credential);
	}

	public List<Credentials> getCredentials() {
		return credentials;
	}

	public void storeUserfriends(User user) {
		friends.add(user);
	}

	public List<User> getUsersfriends() {
		return friends;
	}

//	public void storeMessage(User u) {
//		messages.add(u);
//	}
//	
//	public List<User> getmessages() {
//		return messages;
//	}
//
//	public void storeMessage(Message message) {
//		usersMessages.add(message);
//	}
//	public List<Message> getMessages(){
//		return usersMessages;
//	}

//	public void storeSendedMessages(Messages sendedMessage) {
//		usersendedMessages.add(sendedMessage);
//	}
//
//	public List<Messages> getSendedMessages() {
//		return usersendedMessages;
//	}

	public void setUsersfriends(User frnds) {
	   friends.add(frnds);
	}
}
