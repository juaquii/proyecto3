package app.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Reserve {
    private String codReserve;
    private Date date;
    private Room room;
    private List<Client> clients;

    public Reserve(String codReserve,Date date, Room codRoom, List<Client> clients) {
        this.codReserve=codReserve;
        this.date = date;
        this.room = codRoom;
        this.clients = clients;
    }

    public Reserve(String codReserve, Date date, Room codRoom) {
        this.codReserve = codReserve;
        this.date = date;
        this.room = codRoom;
    }

    public Reserve() {
    }

    public String getCodReserve() {
        return codReserve;
    }

    public void setCodReserve(String codReserve) {
        this.codReserve = codReserve;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals;
        if (this == obj) {
            isEquals = true;
        } else if (obj == null || getClass() != obj.getClass()) {
            isEquals = false;
        } else {
            Reserve reserve = (Reserve) obj;
            return Objects.equals(codReserve, reserve.codReserve);
        }
        return isEquals;

    }

    @Override
    public int hashCode() {
        return Objects.hash(codReserve,date, room, clients);
    }

    @Override
    public String toString() {
        return "Reserve[" +
                "codReserve = " + codReserve +
                ", date = " + date +
                ", codRoom = " + room +
                ", clients = " + clients +
                ']';
    }
}
