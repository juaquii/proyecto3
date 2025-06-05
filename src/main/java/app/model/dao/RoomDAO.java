package app.model.dao;

import app.model.connection.ConnectionMariaDB;
import app.model.entity.Room;
import app.model.entity.enums.TypeR;
import app.model.interfaces.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RoomDAO implements DAO<Room, String> {
    private final static String FINDBYCODROOM = "SELECT r.codRoom, r.beds, r.windows, r.TypeR, r.price FROM Room AS r WHERE r.codRoom=? ";
    private final static String FINDALLROOM = "SELECT codRoom, Beds, Windows,TypeR, Price FROM Room";
    private final static String DELETE = "DELETE from Room WHERE CodRoom=?";
    private final static String UPDATE = "UPDATE Room SET Beds=? ,Windows=?,TypeR=?, Price=? WHERE CodRoom=?";
    private final static String INSERT = "INSERT INTO Room (codRoom,Beds, Windows,TypeR,Price) VALUES (?,?,?,?,?)";


    @Override
    public Room save(Room entity) {
        Room result = entity;
        if (entity != null) {
            String codRoom = entity.getCodRoom();
            if (codRoom != null) {
                Room isInDataBase = findByCodRoom(codRoom);
                if (isInDataBase != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT)) {
                        pst.setString(1, entity.getCodRoom());
                        pst.setInt(2, entity.getBeds());
                        pst.setInt(3, entity.getWindows());
                        pst.setString(4, String.valueOf(entity.getTypeR()));
                        pst.setInt(5, entity.getPrice());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Room delete(Room entity) throws SQLException {
        if (entity != null || entity.getCodRoom() != null) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setString(1, entity.getCodRoom());
                pst.executeUpdate();
            }
        }
        return entity;
    }

    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALLROOM)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Room r = new Room();
                r.setCodRoom(res.getString("CodRoom"));
                r.setBeds(res.getInt("Beds"));
                r.setWindows(res.getInt("Windows"));
                r.setTypeR(TypeR.valueOf(res.getString("TypeR")));
                r.setPrice(res.getInt("Price"));
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public  Room findByCodRoom(String cR) {
        Room result = new Room();
        if (cR != null) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYCODROOM)) {
                pst.setString(1, cR);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result.setCodRoom(res.getString("CodRoom"));
                    result.setBeds(res.getInt("beds"));
                    result.setWindows(res.getInt("windows"));
                    result.setTypeR(TypeR.valueOf(res.getString("TypeR")));
                    result.setPrice(res.getInt("price"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Room update(Room entity) {
        Room result = entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
            pst.setInt(1, entity.getBeds());
            pst.setInt(2, entity.getWindows());
            pst.setString(3, String.valueOf(entity.getTypeR()));
            pst.setInt(4, entity.getPrice());
            pst.setString(5, entity.getCodRoom());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static RoomDAO build() {
        return new RoomDAO();
    }


    @Override
    public void close() throws IOException {

    }

}
