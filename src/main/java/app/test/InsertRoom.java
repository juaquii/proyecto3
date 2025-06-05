package app.test;

import app.model.dao.RoomDAO;
import app.model.entity.Room;
import app.model.entity.enums.TypeR;


public class InsertRoom {
    public static void main(String[] args) {
        Room r = new Room();
        r.setCodRoom("8888W");
        r.setBeds(5);
        r.setWindows(3);
        r.setTypeR(TypeR.DELUXE);
        r.setPrice(500);
        RoomDAO roDAO = new RoomDAO();
        roDAO.save(r);
    }

}
