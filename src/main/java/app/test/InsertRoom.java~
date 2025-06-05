package com.github.ManoloCosano72.test;

import com.github.ManoloCosano72.model.dao.RoomDAO;
import com.github.ManoloCosano72.model.entity.Room;
import com.github.ManoloCosano72.model.entity.enums.TypeR;


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
