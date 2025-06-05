package app.model.dao;

import app.model.connection.ConnectionMariaDB;
import app.model.entity.Client;
import app.model.entity.Reserve;
import app.model.entity.Reserve2;
import app.model.interfaces.DAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReserveDAO implements DAO<Reserve2, Integer> {
    private final static String FINDBYCODRESERVE = "SELECT r.codReserve FROM Reserva2 AS r WHERE r.codReserve=?";
    private final static String FINDBYROOM = "SELECT r.codRoom FROM Reserva2 AS r WHERE r.codRoom=?";
    private final static String FINDRESERVESBYCLIENT = "SELECT * FROM Dor WHERE Dni=?";
    private final static String FINDALL = "SELECT Dni, codReserve FROM Dor WHERE Dni=?";
    private final static String DELETE = "DELETE FROM Reserva2 WHERE CodReserve=? ";
    private final static String DELETERESERVE = "DELETE FROM Dor WHERE CodReserve=?";
    private final static String UPDATE = "UPDATE Reserva2 SET date=?, codRoom=? WHERE codReserve=?";
    private final static String INSERT = "INSERT INTO Reserva2 (Date,CodRoom) VALUES (?,?)";
    private final static String INSERTDOR = "INSERT INTO Dor (Dni,CodReserve) VALUES (?,?)";


    @Override
    public Reserve2 save(Reserve2 entity) {
        Reserve2 result = entity;
        if (entity != null) {
            int codReserve = entity.getCodReserve();
            if (codReserve < 1) {
                Reserve2 isInDataBase = findByCodReserve(codReserve);
                if (isInDataBase != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                        //leer y setearla
                        pst.setDate(1, (Date) entity.getDate());
                        pst.setString(2, entity.getRoom().getCodRoom());
                        pst.executeUpdate();
                        ResultSet rs = pst.getGeneratedKeys();
                        if (rs != null && rs.next()) {
                            entity.setCodReserve(rs.getInt(1));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    saveClientReserve(entity);
                }
            }
        }
        return result;
    }


    public Reserve2 saveClientReserve(Reserve2 reserve) {
        if (reserve != null && reserve.getCodReserve() < 1) {
            if (reserve.getClients() != null) {
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    Reserve2 entity = reserve;
                    pst.setDate(1, (Date) entity.getDate());
                    pst.setString(2, entity.getRoom().getCodRoom());
                    pst.executeUpdate();
                    ResultSet rs = pst.getGeneratedKeys();
                    if (rs != null && rs.next()) {
                        entity.setCodReserve(rs.getInt(1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERTDOR)) {
                    for (Client client : reserve.getClients()) {
                        if (client != null && client.getDni() != null) {
                            pst.setString(1, client.getDni());
                            pst.setInt(2, reserve.getCodReserve());
                            pst.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return reserve;
    }

    @Override
    public Reserve2 delete(Reserve2 entity) throws SQLException {
        if (entity == null || entity.getCodReserve() < 1) return entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
            pst.setInt(1, entity.getCodReserve());
            pst.executeUpdate();
        }
        return entity;
    }

    public Reserve2 deleteReserveFromList(Reserve2 entity) {
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETERESERVE)) {
            pst.setInt(1, entity.getCodReserve());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }


    public Reserve2 findByCodReserve(int codRe) {
        Reserve2 result = new Reserve2();
        if (codRe < 1) return result;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYCODRESERVE)) {
            pst.setInt(1, codRe);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                result.setCodReserve(res.getInt("CodReserve"));
                result.setDate(res.getDate("Date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Reserve findByRoom(String codRo) {
        Reserve result = new Reserve();
        if (codRo == null) return result;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYROOM)) {
            pst.setString(1, codRo);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                result.setCodReserve(res.getString("CodReserve"));
                result.setDate(res.getDate("Date"));
                //Eager
                RoomDAO rDAO = new RoomDAO();
                result.setRoom(rDAO.findByCodRoom((res.getString("Room"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Reserve2> findAll(String Dni, int codReserve) {
        List<Reserve2> reserves = new ArrayList<>();
        if (Dni != null && codReserve != 0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {
                pst.setString(1, Dni);
                pst.setInt(2, codReserve);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reserves;
    }

    public List<Reserve2> findByReservesByClient() {
        List<Reserve2> reserves = new ArrayList<>();
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDRESERVESBYCLIENT)) {
                ResultSet res = pst.executeQuery();
                while (res.next()) {
                    Reserve2 r = new Reserve2();
                    r.setCodReserve(res.getInt("CodReserve"));
                    r.setDate(res.getDate("Date"));
                    r.setRoom(RoomDAO.build().findByCodRoom(res.getString("Room")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return reserves;
    }

    public Reserve update(Reserve entity) {
        Reserve result = entity;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
            pst.setDate(1, (Date) entity.getDate());
            pst.setString(2, entity.getRoom().getCodRoom());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }
    public static ReserveDAO build(){
        return new ReserveDAO();
    }
}
class ReserveLazy extends Reserve2{

}
