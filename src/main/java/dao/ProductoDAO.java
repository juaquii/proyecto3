package dao;

import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<String> obtenerProductos() {
        List<String> productos = new ArrayList<>();
        String query = "SELECT nombre FROM Productos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                productos.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void agregarProducto(String nombre, double precio) {
        String query = "INSERT INTO Productos (nombre, precio) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}