package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Cargar configuración desde el archivo XML
                JAXBContext context = JAXBContext.newInstance(DatabaseConfig.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                DatabaseConfig config = (DatabaseConfig) unmarshaller.unmarshal(new File("src/main/resources/config/database.xml"));

                // Establecer conexión
                connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}