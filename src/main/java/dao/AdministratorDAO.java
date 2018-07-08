package dao;

import exception.BD.FileNotFoundBDConfigEX;
import model.Administrator;

import java.io.IOException;
import java.sql.SQLException;

public interface AdministratorDAO {
    void createAllTables() throws SQLException, IOException, ClassNotFoundException, FileNotFoundBDConfigEX;
    void deleteAllTables() throws SQLException, IOException, ClassNotFoundException, FileNotFoundBDConfigEX;
    void insertAllTables() throws ClassNotFoundException, SQLException, FileNotFoundBDConfigEX, IOException;
    void createPassAdmin(Administrator administrator) throws Exception;
    boolean checkAdminPass(Administrator administrator) throws Exception;
}
