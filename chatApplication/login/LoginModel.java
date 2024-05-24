package chatApplication.login;

import java.util.List;
import java.util.regex.Pattern;

import chatApplication.database.Database;
import chatApplication.model.Credentials;
import chatApplication.model.User;

public class LoginModel {
	
    private Credentials credential;
    private User user;
    private LoginView loginView;
	public LoginModel(LoginView loginView) {
		this.loginView=loginView;
	}

	public void storeCredentials(String name, String password) {
		credential=new Credentials();
		credential.setUserName(name);
		credential.setPassword(password);
		Database.getInstance().storeCredentials(credential);
	}
	public void storeUsers(String name,String password,String phoneNumber, String mail) {
		user=new User();
		user.setName(name);
		user.setMail(mail);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		Database.getInstance().storeUsers(user);
	}
	
	public boolean checkContactInfo(String phoneNumber, String mail) {
		if(phoneNumber.length()==10) {
			String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
			        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
			if(Pattern.compile(regexPattern)
				      .matcher(mail)
				      .matches()) { 
				return true;
			}else {
			loginView.message("Invalid Mail ID");
			return false;}
		}
		else {
			loginView.message("Invalid Phone Number");
		return false;
		}
	}

	public boolean checkUser(String name, String password) {
		List<Credentials> users=Database.getInstance().getCredentials();
		for(Credentials c:users) {
			if(c.getUserName().equals(name)) {
				if(c.getPassword().equals(password)) {
					loginView.message("You already have an Account, Proceed Sign In");
					return true;
				}
				loginView.message("User Name Already Taken,Use Another Name");
				return true;
			}
		}
		return false;
	}
	public boolean validateUser(String name, String password) {
		List<Credentials> users=Database.getInstance().getCredentials();
		for(Credentials c:users) {
			if(c.getUserName().equals(name)) {
				if(c.getPassword().equals(password)) {
					loginView.message("\nLogin In Successful");
					return true;
				}
				loginView.message("Invalid Password");
				return false;
			}
		}
		loginView.message("User Not Found");
		return false;
	}
}
