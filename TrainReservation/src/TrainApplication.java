import login.LoginView;

public class TrainApplication {
    private static TrainApplication trainApplication;
    private static String appName = "IRCTC";
    private static String version = "0.0.1";

    private static TrainApplication getInstance() {
        if (trainApplication == null) {
            trainApplication = new TrainApplication();
        }
        return trainApplication;
    }

    private void create() {
        LoginView loginView = new LoginView();
        System.out.println("----------" + TrainApplication.getInstance().getAppName()
                + " -------- \n\t\tversion " + "("
                + TrainApplication.getInstance().getAppVersion() + ")");
        loginView.startMenu();
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return version;
    }

    public static void main(String[] arg) {
        TrainApplication.getInstance().create();
    }
}
