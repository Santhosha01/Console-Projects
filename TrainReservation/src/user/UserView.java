package user;

import datalayer.DataBase;
import login.LoginView;
import model.*;

import java.util.List;
import java.util.Scanner;

public class UserView {
    static Scanner sc = new Scanner(System.in);
    UserModel userModel;

    public UserView() {
        userModel = new UserModel(this);
    }

    public void init() {
        System.out.println("\n----------------------User Page--------------------------");
        System.out.println("\n 1.Sign Up \n 2.Sign in \n 3.Exit");
        System.out.println("\nEnter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                signUp();
                init();
                break;
            case 2:
                String username = signIn();
                if (username != null) {
                    startMenu(username);
                } else {
                    init();
                }
                break;
            case 3:
                new LoginView().startMenu();
                break;
            default:
                System.out.println("InValid Input");
                init();
        }
    }

    private void startMenu(String username) {
        System.out.println("\n--------------Welcome to User Page--------------");
        System.out.println(" 1. Booking \n 2. Get PNR status \n 3. Booked Tickets \n 4. Cancel Ticket  \n 5. List train Routes \n 6. Search Train \n 7. Exit");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                booking(username);
                startMenu(username);
                break;
            case 2:
                getPnrStatus(username);
                startMenu(username);
                break;
            case 3:
                viewBookedTickets(username);
                startMenu(username);
                break;
            case 4:
                cancelTicket(username);
                startMenu(username);
                break;
            case 5:
                viewTrainRoutes();
                startMenu(username);
                break;
            case 6:
                searchTrainInit(username);
                startMenu(username);
            case 7:
                System.out.println("Thank you for using IRTCT Application");
                init();
                break;
            default:
                System.out.println("Invalid input");
                init();
                break;
        }
    }

    private void searchTrainInit(String username) {
        System.out.println("-----------Searching Train-----------");
        System.out.println(" 1. Search By departure Location \n 2. Search by Arrival Location \n 3. Exit");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                searchBydeparture();
                searchTrainInit(username);
                break;
            case 2:
                searchByArrival();
                searchTrainInit(username);
                break;
            case 3:
                startMenu(username);
                break;
        }
    }

    private void searchBydeparture() {
        System.out.println("Enter your Starting location");
        String startLocation = sc.nextLine();
        boolean flag=true;
        List<Train> trains = DataBase.getInstance().gettrain();
        for (Train t : trains) {
            if (t.getDeparturelocation().equals(startLocation)) {
                System.out.println(t.toString());
                flag=false;
            }
        }
        if(flag){
            System.out.println("Train not Found");
        }
    }

    private void searchByArrival() {
        System.out.println("Enter your destination location");
        String desLocation = sc.nextLine();
        List<Train> trains = DataBase.getInstance().gettrain();
        boolean flag=true;
        for (Train t : trains) {
            if (t.getArrivallocation().equals(desLocation)) {
                System.out.println(t.toString());
                flag=false;
            }
        }
        if(flag){
            System.out.println("Train not found");
        }
    }


    private void getPnrStatus(String email) {
        User u = userModel.getUser(email);
            List<Ticket> tickets = u.getTickets();
            if (!tickets.isEmpty()) {
                System.out.println("----------Ticket Details----------");
                for (Ticket t : tickets) {
                    System.out.println("Ticket Number - " + t.getPnr());
                    System.out.println("Status of the Ticket - " + t.getStatus());
                }
            } else {
                System.out.println("you haven't book Any Train yet");
            }
    }

    private void booking(String email) {
        User user = userModel.getUser(email);
        System.out.println("Enter your starting Location");
        String sLocation = sc.nextLine();
        System.out.println("Enter destination location");
        String dLocation = sc.nextLine();
        Train t = userModel.getTrain(sLocation, dLocation);
        if (t != null) {
            System.out.println("Enter passenger Name");
            String name = sc.nextLine();
            System.out.println("Enter passenger Age");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Passenger gender");
            String gender = sc.nextLine();
            System.out.println("Enter Passenger Phone Number");
            String number=sc.nextLine();
            Passenger passenger = new Passenger(name, age, gender,number);
            if (t.getNumberofseats() != 0) {
                System.out.println("Train Name - " + t.getName() + " And Price of the seat in the train is - Rs " + t.getFare());
                System.out.println("Do you want to Book the train? y/n");
                String choice = sc.nextLine();
                if (choice.equals("y")) {
                    Ticket ticket = new Ticket(t, passenger);
                    ticket.setStatus("CF");
                    user.setTickets(ticket);
                    t.setNumberofseats(t.getNumberofseats() - 1);
                    System.out.println("Train Details");
                    System.out.println(t.toString());
                    DataBase.getInstance().storeTickets(ticket);
                    System.out.println("Train Booked Successfully");
                }
            } else {
                System.out.println("Train is fully booked");
                System.out.println("Train Name - " + t.getName() + " And Price of the seat in the train is - Rs " + t.getFare());
                System.out.println("Do you want to Book the train? y/n");
                String choice = sc.nextLine();
                if (choice.equals("y")) {
                    Ticket ticket = new Ticket(t, passenger);
                    ticket.setStatus("WL");
                    user.setTickets(ticket);
                    DataBase.getInstance().storeWaitingList(ticket);
                    System.out.println("Train Details");
                    System.out.println(t.toString());
                    DataBase.getInstance().storeTickets(ticket);
                    System.out.println("Train Booked Successfully,Currently you are in Waiting List");
                }
            }
            DataBase.getInstance().storePassenger(passenger);
        } else {
            System.out.println("Train not found");
        }
    }

    private void viewTrainRoutes() {
        System.out.println("Enter the Train Name");
        String trainName = sc.nextLine();
        List<String> routes = userModel.getTrainRoutes(trainName);
        if (routes!=null) {
            System.out.println("Train Routes of " + trainName);
            System.out.println(routes);
        } else {
            System.out.println("Train not found");
        }
    }

    private void cancelTicket(String email) {
        User u = userModel.getUser(email);
        if(u!=null){
            System.out.println("Enter Ticket Number or PNR Number");
            int pnr= sc.nextInt();
            sc.nextLine();
            Ticket ticket=userModel.cancelTicket(u,pnr);
            if (ticket!=null) {
                Passenger p=ticket.getPassenger();
                List<Passenger> passengers=DataBase.getInstance().getPassengers();
                passengers.remove(p);
                List<Ticket> tickets=u.getTickets();
                tickets.remove(ticket);
                System.out.println("Your Ticket Number " + ticket.getPnr() + " And The Ticket has been Cancelled Successfully");
            } else {
                System.out.println("Ticket not found");
            }}
        else{
            System.out.println("you haven't booked any ticket yet");}

    }


    private void viewBookedTickets(String email) {
        User user = userModel.getUser(email);
        System.out.println("-------Ticket Details--------");
        List<Ticket> tickets=user.getTickets();
        if (!tickets.isEmpty()) {
            for(Ticket t:tickets) {
                Train train = t.getTrain();
                System.out.println("Ticket Number - " + t.getPnr());
                System.out.println("--------Train Details--------");
                System.out.println("Train Number - " + train.getId() + ", Train Name - " + train.getName());
            }
        } else {
            System.out.println("you haven't book Any Train yet");
        }
    }

    private void signUp() {
        User user = new User();
        System.out.println("Enter your username");
        String username = sc.nextLine();
        user.setName(username);
        System.out.println("Enter your password");
        String password = sc.nextLine();
        user.setPassword(password);
        System.out.println("Enter your Phone Number");
        String phoneNumber = sc.nextLine();
        user.setPhoneNumber(phoneNumber);
        System.out.println("Enter your email");
        String email = sc.nextLine();
        user.setEmail(email);
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setPassword(password);
        userModel.createCredentials(credential);
        userModel.storeUser(user);
        System.out.println("--------------User Sign Up Successfully--------------");
    }

    public String signIn() {
        System.out.println("Enter your Email Id");
        String email = sc.nextLine();
        System.out.println("Enter your password");
        String password = sc.nextLine();
        if (userModel.isValidUser(email, password)) {
            System.out.println("-------------Login Successful----------------");
            return email;
        } else {
            System.out.println("Invalid User Name or Password");
        }
        return null;
    }
}

