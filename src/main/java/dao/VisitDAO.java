package dao;


import exception.BD.ChoosingClientEX;
import exception.BD.ChoosingSubscriptionEX;
import exception.BD.ChoosingWorkerEX;
import exception.BD.FileNotFoundBDConfigEX;
import model.Visit;
import model.Worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface VisitDAO {
    void addVisit(int clientId, int subscriptionId, int workerId ) throws SQLException, FileNotFoundBDConfigEX, IOException, ClassNotFoundException, ChoosingClientEX, ChoosingSubscriptionEX, ChoosingWorkerEX;
    void deleteVisit(int visitID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void updateVisit  (int visitID, int clientId, int subscriptionId, int workerId) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, ChoosingWorkerEX, ChoosingSubscriptionEX, ChoosingClientEX, SQLException;
    Visit getVisit(int visitID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Visit> getAllVisitsID() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Visit> getAllVisitsByClientID(int clientId) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Visit> getAllVisitsByWorkerID(int workerId) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Visit> getAllVisitsBySubsID(int subscriptionId) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
   }
