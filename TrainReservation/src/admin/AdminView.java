package admin;

import datalayer.DataBase;
import login.LoginView;
import model.Ticket;
import model.Train;

import java.util.Queue;
import java.util.Scanner;

public class AdminView {
    static Scanner sc = new Scanner(System.in);
    AdminModel adminModel;

    public AdminView() {
        adminModel = new AdminModel(this);
    }


    public void init() {
        System.out.println("Enter the password");
        String password = sc.nextLine();
        if (adminModel.isValid(password)) {
            startMenu();
        } else {
            System.out.println("Invalid Password");
            new LoginView().startMenu();
        }
    }

    private void startMenu() {
        System.out.println("------------Welcome to Admin Page------------");
        System.out.println("\n 1.Add Train \n 2.Search passenger \n 3.change Ticket status \n 4.Add train routes \n 5.View Train Details \n 6.Exit");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                addTrain();
                startMenu();
                break;
            case 2:
                searchPassenger();
                startMenu();
                break;
            case 3:
                changeTicketStatus();
                startMenu();
                break;
            case 4:
                AddTrainRoutes();
                startMenu();
                break;
            case 5:
                viewTrainDetails();
                startMenu();
                break;
            case 6:
                new LoginView().startMenu();
                break;
            default:
                System.out.println("invalid input");
                startMenu();
        }
    }

    private void viewTrainDetails() {
        System.out.println("Enter train ID");
        int trainID = sc.nextInt();
        if (!adminModel.getTrainDetails(trainID)) {
            System.out.println("Train not found");
        }
    }

    private void searchPassenger() {
        System.out.println("enter the passenger Id");
        int id = sc.nextInt();
        if (!adminModel.getPassengerById(id)) {
            System.out.println("Passenger does not exist");
        }
    }

    private void AddTrainRoutes() {
        System.out.println("Enter the Train Number");
        int trainId = sc.nextInt();
        if (adminModel.addTrainRoutes(trainId)) {
            System.out.println("Train Routes added");
        } else {
            System.out.println("Train Routes not added");
        }
    }
    private void changeTicketStatus() {
        Queue<Ticket> waitingLists = DataBase.getInstance().getWaitingList();
        if (!waitingLists.isEmpty()) {
            System.out.println("Enter the PNR Number of the Ticket");
            int pnr = sc.nextInt();
            if (adminModel.changeTicketStatus(pnr)) {
                System.out.println("Ticket status changed");
            } else {
                System.out.println("PNR Number not found");
            }
        }
        else{
            System.out.println("No passenger are in the Waiting List");
        }
    }

    private void addTrain() {
        System.out.println("Enter train Id");
        int trainId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter train Name");
        String trainName = sc.nextLine();
        System.out.println("Enter the departure time");
        String departureTime = sc.nextLine();
        System.out.println("Enter the arrival time");
        String arrivalTime = sc.nextLine();
        System.out.println("Enter Number of seats in the train");
        int seats = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the fare of the train");
        int fareOfTrain = sc.nextInt();
        sc.nextLine();
        Train train = new Train(seats, trainId, trainName, departureTime, arrivalTime, fareOfTrain);
        DataBase.getInstance().storeTrain(train);
        System.out.println("Train has been Added Successfully");
        System.out.println(train);
    }

}
