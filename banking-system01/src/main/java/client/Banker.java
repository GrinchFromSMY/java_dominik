package client;

import client.ClientData;
import server.db.DatabaseManager;

public class Banker {
    private DatabaseManager databaseManager;

    public Banker() {
        this.databaseManager = new DatabaseManager();
    }

    public void addClient(String firstName, String lastName, String pesel, String accountNumber, double balance) {
        ClientData client = new ClientData(firstName, lastName, pesel, accountNumber, balance);
        databaseManager.addClient(client);
    }

    public void updateClient(String pesel, String firstName, String lastName, String accountNumber, double balance) {
        ClientData client = new ClientData(firstName, lastName, pesel, accountNumber, balance);
        databaseManager.updateClient(client);
    }

    public void deleteClient(String pesel) {
        databaseManager.deleteClient(pesel);
    }

    public void showAllClients() {
        databaseManager.showAllClients();
    }
}
