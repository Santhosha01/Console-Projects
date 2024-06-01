package model;

public class Ticket {
    private static int nextId = 1;
    private int pnr;
    private Train train;
    private Passenger passenger;
    private String status;

    public Ticket(Train train, Passenger passenger) {
        this.pnr = nextId++;
        this.train = train;
        this.passenger = passenger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPnr() {
        return pnr;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
