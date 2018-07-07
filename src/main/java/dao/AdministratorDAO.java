package dao;

import model.Administrator;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AdministratorDAO {
    void createAllTables();
    void deleteAllTables();
    void insertAllTables();
    void createPassAdmin(Administrator administrator) throws Exception;
    boolean checkAdminPass(Administrator administrator) throws Exception;
}
