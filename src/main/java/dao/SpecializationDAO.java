package dao;

import exception.BD.FileNotFoundBDConfigEX;
import model.SpecByWorker;
import model.Specialization;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SpecializationDAO {
    void addSpecByWorker(SpecByWorker specByWorker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void updateSpecByWorker(SpecByWorker specByWorker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<SpecByWorker> getAllSpecByWorkers() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<SpecByWorker> getSpecByWorkers(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;

    void addSpecialization(Specialization specialization) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void updateSpecialization(Specialization specialization) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void deleteSpecialization(int specID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Specialization> getAllSpecializations() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    Specialization getSpecialization(int specID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;

}
