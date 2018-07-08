package dao.impl;

import dao.AdministratorDAO;
import dao.Password;
import dao.impl.factory.DAOImplFactory;
import exception.BD.FileNotFoundBDConfigEX;
import exception.FileNotFoundBDConfigAdm;
import lombok.NoArgsConstructor;
import model.*;

import java.io.*;
import java.sql.SQLException;

@NoArgsConstructor
public class AdministratorDAOImpl implements AdministratorDAO {
    private static final String FILE_ADMIN_KEY = "admin.key";
    private Password password;

    @Override
    public void createAllTables() throws SQLException, IOException, ClassNotFoundException, FileNotFoundBDConfigEX {
        DAOImplFactory.getCreateTableFactoryInstance().createAllTableIfNotExists();
    }

    @Override
    public void deleteAllTables() throws SQLException, IOException, ClassNotFoundException, FileNotFoundBDConfigEX {
        DAOImplFactory.getCreateTableFactoryInstance().dropAllTables();
    }

    @Override
    public void insertAllTables() throws ClassNotFoundException, SQLException, FileNotFoundBDConfigEX, IOException {
        SpecializationDAOImpl sp = new SpecializationDAOImpl();
        SubscriptionDAOImpl subs = new SubscriptionDAOImpl();
        sp.addSpecialization(new Specialization(1,"Trainer"));
        sp.addSpecialization(new Specialization(2,"Bodybuilder"));
        sp.addSpecialization(new Specialization(3,"Masseur"));
        sp.addSpecialization(new Specialization(4,"Fitness trainer"));
        sp.addSpecialization(new Specialization(5,"Boxer"));
        sp.addSpecialization(new Specialization(6,"Kick boxer"));
        sp.addSpecialization(new Specialization(7,"Judoka"));
        sp.addSpecialization(new Specialization(8,"Cleaner"));
        sp.addSpecialization(new Specialization(9,"Manager"));
        sp.addSpecialization(new Specialization(10,"Accountant"));
        sp.addSpecialization(new Specialization(11,"Director"));
        sp.addSpecialization(new Specialization(12,"Seller"));


        subs.addDiscount(new Discount(1,"none", ""));
        subs.addDiscount(new Discount(2,"Summer", "-20%"));
        subs.addDiscount(new Discount(3,"Summer-forever", "Every second towel is free of charge"));
        subs.addDiscount(new Discount(1,"Be a strong", "-15%"));
        subs.addDiscount(new Discount(1,"King lower", "-30% for private lessons"));
        subs.addDiscount(new Discount(1,"Money back", "-50% for the next month"));
        subs.addDiscount(new Discount(1,"Last champion", "+25% time for classes training"));

        subs.addSubsType(new SubsType(1, "Lite subs", 8,0,"Homeless subscription" ));
        subs.addSubsType(new SubsType(1, "Middle subs", 18,0,"" ));
        subs.addSubsType(new SubsType(1, "Pro subs", 0,30,"" ));
        subs.addSubsType(new SubsType(1, "Unlim subs", 0,90,"" ));
        subs.addSubsType(new SubsType(1, "King subs", 0,180,"" ));
        subs.addSubsType(new SubsType(1, "Master subs", 10,10,"" ));
        subs.addSubsType(new SubsType(1, "Student subs", 10,0,"" ));
    }

    @Override
    public void createPassAdmin(Administrator administrator) throws FileNotFoundBDConfigAdm {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(FILE_ADMIN_KEY));
            bw.write(getInstancePass().getSaltedHash(administrator.getPassword()));
            bw.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundBDConfigAdm();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkAdminPass(Administrator administrator) throws FileNotFoundBDConfigAdm {
        boolean isPass = false;
        try {
        BufferedReader br = new BufferedReader(new FileReader(FILE_ADMIN_KEY));
        isPass = Password.check(administrator.getPassword(), br.readLine());
        br.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundBDConfigAdm();
        } catch (Exception e){
            e.printStackTrace();
        }
        return isPass;
    }

    private Password getInstancePass() {
        if (password == null) {
            password = new Password();
        }
        return password;
    }

}
