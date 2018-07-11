package dao;

import exception.BD.FileNotFoundBDConfigEX;

import java.io.IOException;
import java.sql.SQLException;

public interface DeleteDAO {
    void delete(int id) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX;
}
