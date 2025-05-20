package dao;

import model.Producto;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                productos.add(new Producto(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}