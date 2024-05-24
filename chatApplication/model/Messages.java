package chatApplication.model;

public class Messages {
	private String message;
	private String sendUserName;
	private String recievedUserName;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getRecievedUserName() {
		return recievedUserName;
	}

	public void setRecievedUserName(String recievedUserName) {
		this.recievedUserName = recievedUserName;
	}

}
