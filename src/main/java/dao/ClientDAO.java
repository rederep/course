package dao;

import model.Client;

import java.util.List;

public interface ClientDAO {
    void addClient(Client client);
    List<Client> getAllClients();
    Client getClient(int clientID);
    void updateClient (Client client);
    void deleteClient (int clientID);
    List<Client> getClientsBy (Client client);

}
