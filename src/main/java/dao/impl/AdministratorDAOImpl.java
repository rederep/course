package dao.impl;

import dao.AdministratorDAO;
import dao.Password;
import dao.impl.factory.DAOImplFactory;
import lombok.NoArgsConstructor;
import model.Administrator;

import java.io.*;
import java.sql.SQLException;
@NoArgsConstructor
public class AdministratorDAOImpl implements AdministratorDAO {
    private static final String FILE_ADMIN_KEY = "admin.key";

    @Override
    public void createAllTables() throws SQLException, IOException, ClassNotFoundException {
        DAOImplFactory.getCreateTableFactoryInstance().createAllTableIfNotExists();
    }

    @Override
    public void deleteAllTables() throws SQLException, IOException, ClassNotFoundException {
        DAOImplFactory.getCreateTableFactoryInstance().dropAllTables();
    }

    @Override
    public void insertAllTables() {

    }

    @Override
    public void createPassAdmin(Administrator administrator) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_ADMIN_KEY));
        bw.write(new Password().getSaltedHash(administrator.getPassword()));
        bw.close();
    }

    @Override
    public boolean checkAdminPass(Administrator administrator) throws Exception {
        boolean isPass;
        BufferedReader br = new BufferedReader(new FileReader(FILE_ADMIN_KEY));
        isPass = Password.check(administrator.getPassword(), br.readLine());
        br.close();
        return isPass;
    }


}
