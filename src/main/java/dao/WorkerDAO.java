package dao;

import model.Passport;
import model.Worker;

import java.util.List;

public interface WorkerDAO {
    void addWorker(Worker worker);
    List<Worker> getAllWorkers();
    void updateWorker (Worker worker);
    Worker getWorker(int workerID);
    void deleteWorker (int workerID);
    List<Worker> getWorkersBy (Worker worker);

    void addPassport (Passport passport);
    void deletePassport (int workerID);
    void updatePassport (Passport passport);
    List<Passport> getAllPassports();
    Passport getPassport (int workerID);
}
