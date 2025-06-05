package app.model.connection;

import app.utils.XMLManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMariaDB {
    /** Archivo XML que contiene las propiedades de conexión */
    private static final String FILE = "connection.xml";

    /** Instancia única de la clase (Singleton) */
    private static ConnectionMariaDB _instance;

    /** Objeto Connection para gestionar la conexión a la base de datos */
    private static Connection conn;

    /** Constructor privado para implementar el patrón Singleton */
    private ConnectionMariaDB() {
        try {
            // Leer las propiedades de conexión desde el archivo XML
            ConnectionProperties properties = (ConnectionProperties) XMLManager.readXML(new ConnectionProperties(), FILE);

            // Establecer la conexión con la base de datos
            conn = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            // Manejo de errores en caso de fallo al conectar
            e.printStackTrace();
            conn = null;
        } catch (Exception e) {
            // Manejo de errores generales (por ejemplo, problemas al leer el archivo XML)
            e.printStackTrace();
            conn = null;
        }
    }

    /**
     * Método para obtener la conexión a la base de datos.
     * Si no existe una instancia, se crea una nueva.
     *
     * @return Objeto Connection para interactuar con la base de datos
     */
    public static Connection getConnection() {
        if (_instance == null) {
            _instance = new ConnectionMariaDB();
        }
        return conn;
    }

    /**
     * Método para cerrar la conexión a la base de datos.
     * Se recomienda llamarlo al finalizar el uso de la conexión.
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                _instance = null;
            } catch (SQLException e) {
                // Manejo de errores al cerrar la conexión
                e.printStackTrace();
            }
        }
    }
}