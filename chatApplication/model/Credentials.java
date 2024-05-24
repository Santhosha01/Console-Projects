package chatApplication.model;

public class Credentials {
  private String name;
  private String password;
  
  public String getUserName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
