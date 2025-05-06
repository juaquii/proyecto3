package dao;

import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<String> obtenerPedidos() {
        List<String> pedidos = new ArrayList<>();
        String query = "SELECT descripcion FROM Pedidos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                pedidos.add(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public void agregarPedido(String descripcion, int usuarioId) {
        String query = "INSERT INTO Pedidos (descripcion, usuario_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, descripcion);
            pstmt.setInt(2, usuarioId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
