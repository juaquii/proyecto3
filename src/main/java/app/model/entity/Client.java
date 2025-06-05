package app.model.entity;

import java.util.List;

public class Client extends User {
    /** Lista de reservas asociadas al cliente */
    private List<Reserve2> reserves;

    /**
     * Constructor con parámetros para inicializar un cliente.
     *
     * @param dni Documento de identidad del cliente.
     * @param name Nombre del cliente.
     * @param surnames Apellidos del cliente.
     * @param phone Teléfono del cliente.
     * @param mail Correo electrónico del cliente.
     * @param password Contraseña del cliente.
     * @param admin Nivel de permisos del cliente.
     */
    public Client(String dni, String name, String surnames, String phone, String mail, String password, int admin) {
        super(dni, name, surnames, phone, mail, password, admin);
    }

    /**
     * Constructor para inicializar un cliente con una lista de reservas.
     *
     * @param reserves Lista de reservas asociadas al cliente.
     */
    public Client(List<Reserve2> reserves) {
        this.reserves = reserves;
    }

    /** Constructor vacío */
    public Client() {
    }

    /** @return Lista de reservas del cliente */
    public List<Reserve2> getReserves() {
        return reserves;
    }

    /** @param reserves Establece la lista de reservas del cliente */
    public void setReserves(List<Reserve2> reserves) {
        this.reserves = reserves;
    }

    /** @return Nivel de permisos del cliente */
    public int getAdmin() {
        return admin;
    }
}