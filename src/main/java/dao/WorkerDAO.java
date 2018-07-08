package dao;

import exception.BD.FileNotFoundBDConfigEX;
import model.Passport;
import model.Worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface WorkerDAO {
    void addWorker(Worker worker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Worker> getAllWorkers() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void updateWorker (Worker worker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    Worker getWorker(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void deleteWorker (int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Worker> getWorkersBy (Worker worker);

    void addPassport (Passport passport) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void deletePassport (int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void updatePassport (Passport passport) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Passport> getAllPassports() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    Passport getPassport (int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
}
