package model;

import java.util.List;

public class Pedido {
    private int id;
    private int usuarioId;
    private String fecha;
    private List<Producto> productos;

    public Pedido(int id, int usuarioId, String fecha, List<Producto> productos) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.productos = productos;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}