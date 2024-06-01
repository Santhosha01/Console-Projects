package login;

import admin.AdminView;
import user.UserView;

import java.util.Scanner;

public class LoginView {
    AdminView adminView = new AdminView();
    UserView userView = new UserView();
    static Scanner sc = new Scanner(System.in);


    public void startMenu() {
        System.out.println("\nWelcome to Home Page");
        System.out.println("\n 1. Admin   \n 2. User \n 3. Exit");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                adminView.init();
                break;
            case 2:
                userView.init();
                break;
            case 3:
                System.out.println("Thank for Using IRTCT Application");
                System.exit(0);
            default:
                System.out.println("Invalid input");
                startMenu();
        }
    }
}
