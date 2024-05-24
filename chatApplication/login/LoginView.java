package chatApplication.login;

import java.util.Scanner;

import chatApplication.homepage.HomeView;
import chatApplication.ChatApplication;

public class LoginView {
	    private final Scanner sc=new Scanner(System.in);
		private LoginModel loginModel;
		
		public LoginView() {
			loginModel = new LoginModel(this);
		}

		public void signup() {
			try {
				System.out.println("\n----------------Sign In Page------------------");
				System.out.println();
				System.out.println("Enter your Name");
				String name = sc.nextLine();
				System.out.println("Enter Phone Number");
				String phoneNumber=sc.nextLine();
				System.out.println("Enter yout Mail ID");
				String mail=sc.nextLine();
				System.out.println("Enter your Password");
				String password = sc.nextLine();
				if(!loginModel.checkContactInfo(phoneNumber,mail)) {
					signup();
				}
				if(loginModel.checkUser(name,password)) {
					init();
				}
				else {
				loginModel.storeCredentials(name,password);
				loginModel.storeUsers(name,password,phoneNumber,mail);
				message("\nSign Up successfully, You can Proceed Sign In");
				init();
				}
			} catch (Exception e) {
				signup();
			}
		}

		public void message(String massage) {
			System.out.println(massage);
		}
		
		
		public void init() {
			System.out.println("\n----------------------Home Page--------------------------");	
			System.out.println("\n 1.Sign Up \n 2.Sign in \n 3.Exit");
			System.out.println("\nEnter your choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				signup();
				break;
			case 2:
				signin();
				break;
			case 3:
				System.out.println(
						"\n----------- Thanks for using " + ChatApplication.getInstance().getAppName() + " ------------");
			    System.exit(0);
				break;
			default :
				System.out.println("InValid Input");
				init();
			}
		}
	private void signin() {
		System.out.println("Enter your User Name");	
		String name=sc.nextLine();
		System.out.println("Enter your Password");
		String password=sc.nextLine();
		if(loginModel.validateUser(name,password)) {
			homePage(name);
		}
		else {
			init();
		}
	}

	private void homePage(String name) {
		System.out.println("\n---------------Welcome to Convo ,Begin your Conversation----------------");
		new HomeView().homeInit(name);
	}

}
