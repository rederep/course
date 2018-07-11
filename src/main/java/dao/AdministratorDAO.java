package dao;

import exception.BD.*;
import model.Administrator;


import java.io.IOException;
import java.sql.SQLException;

public interface AdministratorDAO {
    void createAllTables() throws SQLException, IOException, ClassNotFoundException, FileNotFoundBDConfigEX;
    void deleteAllTables() throws SQLException, IOException, ClassNotFoundException, FileNotFoundBDConfigEX;
    void insertAllTables(GenericObjectT object) throws ClassNotFoundException, SQLException, FileNotFoundBDConfigEX, IOException, NoPramToInsertBDEX, ChoosingSubscriptionEX, ChoosingWorkerEX, ChoosingClientEX;
}
