package app.test;

import app.model.connection.ConnectionProperties;
import app.utils.XMLManager;

public class LoadConnection {
    public static void main(String[] args) {
        ConnectionProperties c = XMLManager.readXML (new ConnectionProperties(),"connection.xml");
        XMLManager.readXML(c,"connection.xml");
    }
}
