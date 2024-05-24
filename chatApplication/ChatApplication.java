package chatApplication;
import chatApplication.login.LoginView;

public class ChatApplication {
    
		private static ChatApplication chatApplication;
		private String appName="Convo";
		
		public static ChatApplication getInstance() {
			if (chatApplication == null) {
				chatApplication = new ChatApplication();
			}
			return chatApplication;
		}

		private void create() {
			LoginView loginView = new LoginView();
			System.out.println("----------------------" + chatApplication.getInstance().getAppName()
					+ " ----------------------");
			loginView.init();
		}

		public String getAppName() {
			return appName;
		}

	public static void main(String[] args) {
			chatApplication.getInstance().create();

	}

}
