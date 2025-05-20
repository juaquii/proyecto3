package controller;

import dao.PedidoDAO;
import dao.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Pedido;
import model.Producto;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    @FXML
    private TextField usuarioIdField;
    @FXML
    private ListView<Producto> productosListView;

    private PedidoDAO pedidoDAO;
    private ProductoDAO productoDAO;
    private ObservableList<Producto> productos;

    public PedidoController() {
        pedidoDAO = new PedidoDAO();
        productoDAO = new ProductoDAO();
        productos = FXCollections.observableArrayList(productoDAO.findAll());
    }

    @FXML
    public void initialize() {
        productosListView.setItems(productos);
    }

    public void handleAddPedido() {
        int usuarioId = Integer.parseInt(usuarioIdField.getText());
        List<Producto> selectedProductos = new ArrayList<>(productosListView.getSelectionModel().getSelectedItems());

        Pedido pedido = new Pedido(0, usuarioId, null, selectedProductos);
        if (pedidoDAO.savePedidoWithProductos(pedido)) { // Usamos el método correcto
            System.out.println("Pedido saved successfully!");
        } else {
            System.out.println("Failed to save Pedido.");
        }
    }
}