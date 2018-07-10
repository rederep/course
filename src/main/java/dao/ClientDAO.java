package dao;

import exception.BD.FileNotFoundBDConfigEX;
import model.Client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    void addClient(Client client) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX;
    List<Client> getAllClients() throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX;
    Client getClient(int clientID) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX;
    void updateClient (Client client) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX;
    void deleteClient (int clientID) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX;
    List<Client> getClientsByName (String name) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;



}
