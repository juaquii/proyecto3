package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLConfigLoader {
    public static DatabaseConfig loadConfig() {
        try {
            JAXBContext context = JAXBContext.newInstance(DatabaseConfig.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (DatabaseConfig) unmarshaller.unmarshal(new File("src/main/resources/config/database.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}