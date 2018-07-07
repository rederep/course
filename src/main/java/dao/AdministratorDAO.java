package dao;

import model.Administrator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface AdministratorDAO {
    void createAllTables() throws SQLException, IOException, ClassNotFoundException;
    void deleteAllTables() throws SQLException, IOException, ClassNotFoundException;
    void insertAllTables();
    void createPassAdmin(Administrator administrator) throws Exception;
    boolean checkAdminPass(Administrator administrator) throws Exception;
}
