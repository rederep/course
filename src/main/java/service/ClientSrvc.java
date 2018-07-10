package service;

import dao.impl.ClientDAOImpl;
import dao.impl.VisitDAOImpl;
import dao.impl.factory.DAOImplFactory;
import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.Client;
import model.Subscription;
import model.Visit;
import model.Worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientSrvc {
    private ClientDAOImpl clientDAO;

    public ClientSrvc() {
        clientDAO = DAOImplFactory.getClientInstance();
    }

    public void addClient(Client client) throws FileNotFoundBDConfigEX {
        try {
            clientDAO.addClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Client> getAllClients() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Client> clientList = null;
        try {
            clientList = clientDAO.getAllClients();
        }catch (NullPointerException e) {
            throw new ModelNotFoundEX("All Clients");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }
    
    public List<Client> getFullAllClients() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Client> clientList = null;
        try {
            clientList = clientDAO.getAllClients();
            VisitSrvc visitSrvc = new VisitSrvc();
            WorkerSrvc workerSrvc = new WorkerSrvc();
            SubscriptionSrvc subscriptionSrvc = new SubscriptionSrvc();

            for (Client client : clientList) {
                List<Worker> workerList = new ArrayList<>();
                List<Subscription> subscriptionList = new ArrayList<>();
                for (Visit visit : visitSrvc.getAllVisitsByClientID(client.getId())) {
                    workerList.add(workerSrvc.getFullWorker(visit.getWorkerID()));
                }
                for (Visit visit : visitSrvc.getAllVisitsBySubsID(client.getId())) {
                    subscriptionList.add(subscriptionSrvc.getSubscription(visit.getSubscriptionID()));
                }
                client.setWorkerLists(workerList);
                client.setSubscriptionLists(subscriptionList);
            }
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Full clients information");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public Client getClient(int clientID) throws ModelNotFoundEX, FileNotFoundBDConfigEX {
        Client client = null;
        try {
            client = clientDAO.getClient(clientID);

        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Subscription Type");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void updateClient(Client client) throws FileNotFoundBDConfigEX {
        try {
            clientDAO.updateClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int clientID) throws FileNotFoundBDConfigEX {
        try {
            clientDAO.deleteClient(clientID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getClientsByName(String name) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Client> clientList = null;

        try {
            clientList = clientDAO.getClientsByName(name);
            VisitSrvc visitSrvc = new VisitSrvc();
            WorkerSrvc workerSrvc = new WorkerSrvc();
            SubscriptionSrvc subscriptionSrvc = new SubscriptionSrvc();

            for (Client client : clientList) {
                List<Worker> workerList = new ArrayList<>();
                List<Subscription> subscriptionList = new ArrayList<>();
                for (Visit visit : visitSrvc.getAllVisitsByClientID(client.getId())) {
                    workerList.add(workerSrvc.getFullWorker(visit.getWorkerID()));
                }
                for (Visit visit : visitSrvc.getAllVisitsBySubsID(client.getId())) {
                    subscriptionList.add(subscriptionSrvc.getSubscription(visit.getSubscriptionID()));
                }
                client.setWorkerLists(workerList);
                client.setSubscriptionLists(subscriptionList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientList;
    }
}


