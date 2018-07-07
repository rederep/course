package dao.impl;

import dao.WorkerDAO;
import lombok.NoArgsConstructor;
import model.Passport;
import model.Worker;

import java.util.List;
@NoArgsConstructor
public class WorkerDAOImpl implements WorkerDAO {
    @Override
    public void addWorker(Worker worker) {

    }

    @Override
    public List<Worker> getAllWorkers() {
        return null;
    }

    @Override
    public void updateWorker(Worker worker) {

    }

    @Override
    public Worker getWorker(int workerID) {
        return null;
    }

    @Override
    public void deleteWorker(int workerID) {

    }

    @Override
    public List<Worker> getWorkersBy(Worker worker) {
        return null;
    }

    @Override
    public void addPassport(Passport passport) {

    }

    @Override
    public void deletePassport(int workerID) {

    }

    @Override
    public void updatePassport(Passport passport) {

    }

    @Override
    public List<Passport> getAllPassports() {
        return null;
    }

    @Override
    public Passport getPassport(int workerID) {
        return null;
    }
}
