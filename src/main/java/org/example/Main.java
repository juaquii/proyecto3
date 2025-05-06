package org.example;

import dao.PedidoDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;
import utils.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
        // Crear instancias de los DAOs
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        // Probar UsuarioDAO
        usuarioDAO.agregarUsuario("John Doe", "john@example.com");
        System.out.println("Usuarios: " + usuarioDAO.obtenerUsuarios());

        // Probar ProductoDAO
        productoDAO.agregarProducto("Laptop", 1200.50);
        System.out.println("Productos: " + productoDAO.obtenerProductos());

        // Probar PedidoDAO
        pedidoDAO.agregarPedido("Order for Laptop", 1);
        System.out.println("Pedidos: " + pedidoDAO.obtenerPedidos());
    }
}

