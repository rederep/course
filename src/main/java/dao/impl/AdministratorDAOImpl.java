package dao.impl;

import dao.AdministratorDAO;
import dao.Password;
import dao.impl.factory.DAOImplFactory;
import exception.BD.*;
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
    public void insertAllTables(GenericObjectT object) throws ClassNotFoundException, SQLException, FileNotFoundBDConfigEX, IOException, NoPramToInsertBDEX, ChoosingSubscriptionEX, ChoosingWorkerEX, ChoosingClientEX {
        String objName = object.getT().getClass().getSimpleName();
        if (objName.equals(SubsType.class.getSimpleName())) {
            SubscriptionDAOImpl result = DAOImplFactory.getSubscriptionInstance();
            result.addSubsType((SubsType) object.getT());
        } else if (objName.equals(Specialization.class.getSimpleName())) {
            SpecializationDAOImpl result = DAOImplFactory.getSpecializationInstance();
            result.addSpecialization((Specialization) object.getT());
        } else if (objName.equals(Discount.class.getSimpleName())) {
            SubscriptionDAOImpl result = DAOImplFactory.getSubscriptionInstance();
            result.addDiscount((Discount) object.getT());

        } else if (objName.equals(Client.class.getSimpleName())) {
            ClientDAOImpl result = DAOImplFactory.getClientInstance();
            result.addClient((Client) object.getT());
        } else if (objName.equals(Worker.class.getSimpleName())) {
            WorkerDAOImpl result = DAOImplFactory.getWorkerInstance();
            result.addWorker((Worker) object.getT());
        } else if (objName.equals(Subscription.class.getSimpleName())) {
            SubscriptionDAOImpl result = DAOImplFactory.getSubscriptionInstance();
            result.addSubscription((Subscription) object.getT());
        } else if (objName.equals(SpecByWorker.class.getSimpleName())) {
            SpecializationDAOImpl result = DAOImplFactory.getSpecializationInstance();
            result.addSpecByWorker((SpecByWorker) object.getT());
        } else if (objName.equals(Visit.class.getSimpleName())) {
            VisitDAOImpl result = DAOImplFactory.getVisitInstance();
            result.addVisit((Visit) object.getT());
        } else {
            throw new NoPramToInsertBDEX();
        }


        //  }


//
//

//

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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
