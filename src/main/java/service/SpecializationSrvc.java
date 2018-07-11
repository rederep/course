package service;

import dao.impl.SpecializationDAOImpl;
import dao.impl.factory.DAOImplFactory;
import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.SpecByWorker;
import model.Specialization;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SpecializationSrvc {
    private SpecializationDAOImpl specDAO;

    public SpecializationSrvc() {
        specDAO = DAOImplFactory.getSpecializationInstance();
    }

    public void addSpecByWorker(SpecByWorker specByWorker) throws FileNotFoundBDConfigEX {
        try {
            specDAO.addSpecByWorker(specByWorker);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSpecByWorker(SpecByWorker specByWorker) throws FileNotFoundBDConfigEX {
        try {
            specDAO.updateSpecByWorker(specByWorker);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecByWorker(int specByWorkerID) throws FileNotFoundBDConfigEX {
        try {
            specDAO.delete(specByWorkerID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SpecByWorker> getAllSpecByWorkers() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<SpecByWorker> specByWorkerList = null;
        try {
            specByWorkerList = specDAO.getAllSpecByWorkers();
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Specializations by All Workers");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specByWorkerList;
    }

    public List<SpecByWorker> getSpecByWorkers(int workerID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<SpecByWorker> specByWorkerList = null;
        try {
            specByWorkerList = specDAO.getSpecByWorkers(workerID);
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Specializations by one Worker (id Worker is " + workerID + ")");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specByWorkerList;
    }

    public void addSpecialization(Specialization specialization) throws FileNotFoundBDConfigEX {
        try {
            specDAO.addSpecialization(specialization);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSpecialization(Specialization specialization) throws FileNotFoundBDConfigEX {
        try {
            specDAO.updateSpecialization(specialization);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecialization(int specID) throws FileNotFoundBDConfigEX {
        try {
            specDAO.deleteSpecialization(specID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Specialization> getAllSpecializations() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Specialization> specializationList = null;
        try {
            specializationList = specDAO.getAllSpecializations();
        }catch (NullPointerException e) {
            throw new ModelNotFoundEX("Specializations");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specializationList;
    }

    public Specialization getSpecialization(int specID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        Specialization specialization = null;
        try {
            specialization = specDAO.getSpecialization(specID);
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Specialization");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialization;
    }

}
