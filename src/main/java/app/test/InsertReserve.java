package app.test;

import app.model.dao.ReserveDAO;
import app.model.entity.Reserve;
import app.model.entity.Room;

import java.sql.Date;


public class InsertReserve {
    public static void main(String[] args) {
        Date date = new Date(2010,12,3);
        Room room = new Room();
        room.setCodRoom("12345");
        Reserve r = new Reserve("333MN",date,room);
        ReserveDAO rRAO = new ReserveDAO();
        rRAO.update(r);
    }
}
