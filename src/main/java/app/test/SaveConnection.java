package app.test;

import app.model.connection.ConnectionProperties;
import app.utils.XMLManager;

public class SaveConnection {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","hotel","root","root");
        XMLManager.writeXML(c,"connection.xml");
    }
}
