package datalayer;

import model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DataBase {

    private static DataBase dataBase;
    private List<Credential> credentials = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Train> trains = new ArrayList<>();
    private Queue<Ticket> waitingTickets = new LinkedList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public void insertCredentials(Credential credential) {
        credentials.add(credential);
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void storeUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void storeTrain(Train b) {
        trains.add(b);
    }

    public List<Train> gettrain() {
        return trains;
    }

    public void storeWaitingList(Ticket ticket) {
        waitingTickets.add(ticket);
    }

    public Queue<Ticket> getWaitingList() {
        return waitingTickets;
    }

    public void storeTickets(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void storePassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
