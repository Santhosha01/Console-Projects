package user;

import model.Credential;
import model.Ticket;
import model.Train;
import model.User;
import datalayer.DataBase;
import java.util.List;

public class UserModel {
    private UserView userView;

    public UserModel(UserView userView) {
        this.userView = userView;
    }

    public void createCredentials(Credential credentials) {
        DataBase.getInstance().insertCredentials(credentials);
    }

    public void storeUser(User user) {
        DataBase.getInstance().storeUser(user);
    }

    public boolean isValidUser(String email, String password) {
        List<Credential> credentials = DataBase.getInstance().getCredentials();
        for (Credential c : credentials) {
            if ((c.getEmail()).equals(email)) {
                if (c.getPassword().equals(password)) {
                    return true;
                }

            }
        }
        return false;
    }

    public User getUser(String email) {
        List<User> users = DataBase.getInstance().getUsers();
        for (User s : users) {
            if (s.getEmail().equals(email)) {
                return s;
            }
        }
        return null;
    }

    public Train getTrain(String sLocation, String dLocation) {
        List<Train> trains = DataBase.getInstance().gettrain();
        for (Train t : trains) {
            if ((t.getDeparturelocation().equals(sLocation))) {
                if(t.getArrivallocation().equals(dLocation)) {
                    return t;
                }
                else{
                    List<String> routes=t.getRoutes();
                    for(String s:routes){
                        if(s.equals(dLocation)){
                            return t;
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<String> getTrainRoutes(String trainName) {
        List<Train> trains = DataBase.getInstance().gettrain();
        for (Train t : trains) {
            if (t.getName().equals(trainName)) {
                return t.getRoutes();
            }
        }
        return null;
    }

    public Ticket cancelTicket(User u,int pnr) {
        List<Ticket> tickets = u.getTickets();
        if (!tickets.isEmpty()) {
            for(Ticket ticket:tickets) {
              if(ticket.getPnr()==pnr) {
                  Train t = ticket.getTrain();
                  List<Train> trains = DataBase.getInstance().gettrain();
                  for (Train train : trains) {
                      if (train.getName().equals(t.getName())) {
                          train.setNumberofseats(train.getNumberofseats() + 1);
                          return ticket;
                      }
                  }
                  return null;
              }
            }
        }
        return null;
    }
}
