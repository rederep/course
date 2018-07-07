package dao;

import model.SpecByWorker;

import java.util.List;

public interface SpecializationDAO {
    void addSpecByWorker(SpecByWorker specByWorker);
    void updateSpecByWorker(SpecByWorker specByWorker);
    void deleteSpecByWorker(int specByWorkerID);
    List<SpecByWorker> getAllSpecByWorkers();
    List<SpecByWorker> getSpecByWorkers(int workerID);
}
