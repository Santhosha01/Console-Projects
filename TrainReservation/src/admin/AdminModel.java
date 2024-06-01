package admin;

import datalayer.DataBase;
import model.Passenger;
import model.Ticket;
import model.Train;
import model.User;

import java.util.List;


public class AdminModel {
    AdminView adminView;

    public AdminModel(AdminView adminView) {
        this.adminView = adminView;
    }

    public boolean isValid(String password) {
        return password.equals("admin");
    }

    public boolean getPassengerById(int id) {
        List<Passenger> passengers = DataBase.getInstance().getPassengers();
        List<Ticket> tickets = DataBase.getInstance().getTickets();
        Ticket t=null;
        for(Ticket ticket : tickets) {
            if(ticket.getPassenger().getId()==id){
                t=ticket;
            }
        }
        for (Passenger p : passengers) {
            if (p.getId() == id) {
                System.out.println("--------Passenger Details-------");
                System.out.println(" Ticket Number "+t.getPnr()+" "+p.toString() + "\n");
                return true;
            }
        }
        return false;
    }

    public boolean addTrainRoutes(int trainId) {
        List<Train> trains = DataBase.getInstance().gettrain();
        for (Train t : trains) {
            if (t.getId() == trainId) {
                t.setRoutes();
                return true;
            }
        }
        return false;
    }

    public boolean changeTicketStatus(int pnr) {
        List<Ticket> tickets = DataBase.getInstance().getTickets();
//        for (Ticket t : tickets) {
//            System.out.println(t.getPnr()+" "+t.getStatus());
//        }
        for (Ticket t : tickets) {
            if (t.getPnr() == pnr && t.getStatus().equals("WL")) {
                t.setStatus("CF");
                return true;
            }
        }
        return false;
    }

    public boolean getTrainDetails(int trainID) {
        List<Train> trains = DataBase.getInstance().gettrain();
        for (Train t : trains) {
            if (t.getId() == trainID) {
                System.out.println(t.toString() + "\n");
                return true;
            }
        }
        return false;
    }
}
