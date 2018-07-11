package service;

import dao.impl.WorkerDAOImpl;
import dao.impl.factory.DAOImplFactory;
import exception.BD.FileNotFoundBDConfigEX;
import exception.DeletedGood;
import exception.ModelNotFoundEX;
import exception.NotDeletedWorkerEX;
import model.Passport;
import model.SpecByWorker;
import model.Visit;
import model.Worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerSrvc {
    private WorkerDAOImpl workerDAO;

    public WorkerSrvc() {
        workerDAO = DAOImplFactory.getWorkerInstance();
    }

    public void addWorker(Worker worker) throws FileNotFoundBDConfigEX {
        try {
            workerDAO.addWorker(worker);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Worker> getAllWorkers() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Worker> workerList = null;
        try {
            workerList = workerDAO.getAllWorkers();
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("All Workers");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workerList;
    }

    public List<Worker> getFullAllWorkers() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Worker> workerList = null;
        try {
            workerList = workerDAO.getAllWorkers();
            SpecializationSrvc specSrvc = new SpecializationSrvc();
            for (Worker worker : workerList) {
                worker.setSpecByWorkerLists(specSrvc.getSpecByWorkers(worker.getId()));
            }
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Full info All Workers");
        } catch (IOException e) {
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
        } catch (IOException e) {
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
        }
        return worker;
    }

    public Worker getFullWorker(int workerID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        Worker worker = null;
        try {
            worker = workerDAO.getWorker(workerID);
            worker.setSpecByWorkerLists(new SpecializationSrvc().getSpecByWorkers(worker.getId()));
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

    public void deleteWorker(int workerID) throws FileNotFoundBDConfigEX, ModelNotFoundEX, NotDeletedWorkerEX, DeletedGood {
        try {
            VisitSrvc visitSrvc = new VisitSrvc();
            List<Visit> visitList  = visitSrvc.getAllVisitsByWorkerID(workerID);
            if (visitList.size()==0) {
                workerDAO.deleteWorker(workerID);
                new SpecializationSrvc().deleteSpecByWorker(workerID);
                throw new DeletedGood();
            } else {
                throw new NotDeletedWorkerEX();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        }
    }

    public List<Worker> getWorkersByName(String name) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Worker> workerList = null;
        try {
            workerList = workerDAO.getWorkersByName(name);
            SpecializationSrvc specSrvc = new SpecializationSrvc();
            for (Worker worker : workerList) {
                worker.setSpecByWorkerLists(specSrvc.getSpecByWorkers(worker.getId()));
            }
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Full info All Workers by name: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workerList;
    }

    public void addPassport(Passport passport) throws FileNotFoundBDConfigEX {
        try {
            workerDAO.addPassport(passport);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePassport(int workerID) throws FileNotFoundBDConfigEX {
        try {
            workerDAO.deletePassport(workerID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassport(Passport passport) throws FileNotFoundBDConfigEX {
        try {
            workerDAO.updatePassport(passport);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Passport> getAllPassports() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Passport> passportList = null;
        try {
            passportList = workerDAO.getAllPassports();

        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Pasports");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passportList;
    }

    public Passport getPassport(int workerID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        Passport passport = null;
        try {
            passport = workerDAO.getPassport(workerID);
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Pasport");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passport;
    }
}