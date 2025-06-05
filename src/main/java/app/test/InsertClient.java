package app.test;

import app.model.dao.ClientDAO;
import app.model.entity.Client;

public class InsertClient {
    public static void main(String[] args) {
      Client c = new Client("CCC90","Juan","Agustin","644112299","juan@gmail.com","Holas000-",7);
      ClientDAO cDAO = new ClientDAO();
      cDAO.save(c);
    }



}
