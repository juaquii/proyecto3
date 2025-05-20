package dao;

import model.Pedido;
import model.Producto;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class PedidoDAO {

    public boolean savePedidoWithProductos(Pedido pedido) {
        String insertPedidoQuery = "INSERT INTO pedidos (usuario_id, fecha) VALUES (?, NOW())";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pedidoStatement = connection.prepareStatement(insertPedidoQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Save the Pedido
            pedidoStatement.setInt(1, pedido.getUsuarioId());
            int rows = pedidoStatement.executeUpdate();
            if (rows > 0) {
                // Retrieve the generated pedido_id
                ResultSet keys = pedidoStatement.getGeneratedKeys();
                if (keys.next()) {
                    int pedidoId = keys.getInt(1);
                    // Save associated Productos
                    return saveProductosForPedido(pedidoId, pedido.getProductos());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveProductosForPedido(int pedidoId, List<Producto> productos) {
        String insertProductoQuery = "INSERT INTO pedido_productos (pedido_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement productoStatement = connection.prepareStatement(insertProductoQuery)) {
            // Add each Producto to the batch
            for (Producto producto : productos) {
                productoStatement.setInt(1, pedidoId);
                productoStatement.setInt(2, producto.getId());
                productoStatement.setInt(3, 1); // Example: fixed quantity
                productoStatement.addBatch();
            }
            // Execute batch insertion
            productoStatement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}