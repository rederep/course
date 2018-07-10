package service;

import dao.impl.WorkerDAOImpl;
import dao.impl.factory.DAOImplFactory;
import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.Worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class WorkerSrvc {
    private WorkerDAOImpl workerDAO;

    public WorkerSrvc() {
        workerDAO = DAOImplFactory.getWorkerInstance();
    }

    public void addWorker(Worker worker) throws FileNotFoundBDConfigEX {
        try {
            workerDAO.addWorker(worker);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Worker> getAllWorkers() throws FileNotFoundBDConfigEX {
        List<Worker> workerList = null;
        try {
            workerList = workerDAO.getAllWorkers();
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workerList;
    }

    public void updateWorker(Worker worker) throws FileNotFoundBDConfigEX {
        try {
            workerDAO.addWorker(worker);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Worker getWorker(int workerID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        Worker worker = null;
        try {
            worker = workerDAO.getWorker(workerID);
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Worker");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    public void deleteWorker(int workerID) throws FileNotFoundBDConfigEX {
        try {
            workerDAO.deleteWorker(workerID);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Worker> getWorkersBy(Worker worker) {
        List<Worker> workerList = null;
        workerList = workerDAO.getWorkersBy(worker);
        return workerList;
    }
}
