package app.model.dao;

import app.model.connection.ConnectionMariaDB;
import app.model.entity.Client;
import app.model.interfaces.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client, String> {
    private final static String FINDBYDNI = "SELECT Dni, Name FROM Client WHERE Dni = ?";
    private final static String FINDBYMAIL = "SELECT Dni, Mail, Password, Admin FROM Client WHERE Mail = ?";
    private final static String FINDALL = "SELECT Dni, Name, Surnames, Phone, Mail, Admin FROM Client";
    private final static String DELETE = "DELETE FROM Client WHERE Dni = ?";
    private final static String UPDATE = "UPDATE Client SET Name = ?, Surnames = ?, Phone = ?, Mail = ?, Password = ?, Admin = ? WHERE Dni = ?";
    private final static String INSERT = "INSERT INTO Client (Dni, Name, Surnames, Phone, Mail, Password, Admin) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Client save(Client entity) {
        if (entity != null) {
            String dni = entity.getDni();
            if (dni != null) {
                Client existingClient = findByDni(dni);
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(
                        existingClient != null ? UPDATE : INSERT)) {
                    pst.setString(1, entity.getName());
                    pst.setString(2, entity.getSurnames());
                    pst.setString(3, entity.getPhone());
                    pst.setString(4, entity.getMail());
                    pst.setString(5, entity.getPassword());
                    pst.setInt(6, entity.getAdmin());
                    pst.setString(7, entity.getDni());
                    pst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }

    @Override
    public Client delete(Client entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setString(1, entity.getDni());
                pst.executeUpdate();
            }
        }
        return entity;
    }

    public Client findByDni(String dni) {
        Client result = null;
        if (dni != null) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYDNI)) {
                pst.setString(1, dni);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result = new Client();
                    result.setDni(res.getString("Dni"));
                    result.setName(res.getString("Name"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Client findByMail(String mail) {
        Client result = null;
        if (mail != null) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYMAIL)) {
                pst.setString(1, mail);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result = new Client();
                    result.setDni(res.getString("Dni"));
                    result.setMail(res.getString("Mail"));
                    result.setAdmin(res.getInt("Admin"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Client c = new Client();
                c.setDni(res.getString("Dni"));
                c.setName(res.getString("Name"));
                c.setSurnames(res.getString("Surnames"));
                c.setPhone(res.getString("Phone"));
                c.setMail(res.getString("Mail"));
                c.setAdmin(res.getInt("Admin"));
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client update(Client entity) {
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
            pst.setString(1, entity.getName());
            pst.setString(2, entity.getSurnames());
            pst.setString(3, entity.getPhone());
            pst.setString(4, entity.getMail());
            pst.setString(5, entity.getPassword());
            pst.setInt(6, entity.getAdmin());
            pst.setString(7, entity.getDni());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static ClientDAO build() {
        return new ClientDAO();
    }

    @Override
    public void close() throws IOException {
        // Add resource cleanup logic if necessary
    }
}