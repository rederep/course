package dao.impl;

import dao.DBVar;
import dao.WorkerDAO;
import exception.BD.FileNotFoundBDConfigEX;
import lombok.NoArgsConstructor;
import model.Passport;
import model.Worker;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.factory.ConnectFactory.getInstance;

@NoArgsConstructor
public class WorkerDAOImpl implements WorkerDAO {
    private static final String GET_ALL_PASSPORT = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_PASSPORT.getVar(), DBVar.DELETED.getVar());
    private static final String GET_PASSPORT = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_PASSPORT.getVar(), DBVar.ID_PASSPORT.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_PASSPORT = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?);"
            , DBVar.DB_PASSPORT.getVar(), DBVar.ID_PASSPORT.getVar(), DBVar.INFO.getVar());
    private static final String DELETE_PASSPORT = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_PASSPORT.getVar(), DBVar.DELETED.getVar(), DBVar.ID_PASSPORT.getVar());
    private static final String UPDATE_PASSPORT = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_PASSPORT.getVar(), DBVar.INFO.getVar(), DBVar.ID_PASSPORT.getVar());


    private static final String GET_ALL_WORKER = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_WORKERS.getVar(), DBVar.DELETED.getVar());
    private static final String GET_WORKER = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_WORKERS.getVar(), DBVar.ID_WORKER.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_WORKER = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);"
            , DBVar.DB_WORKERS.getVar(), DBVar.FIRST_NAME.getVar(), DBVar.LAST_NAME.getVar()
            , DBVar.ADDRESS.getVar(), DBVar.TELEPHONE.getVar(), DBVar.SALARY.getVar());
    private static final String UPDATE_WORKER = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?  WHERE %s = ?;"
            , DBVar.DB_WORKERS.getVar(), DBVar.FIRST_NAME.getVar(), DBVar.LAST_NAME.getVar()
            , DBVar.ADDRESS.getVar(), DBVar.TELEPHONE.getVar(), DBVar.SALARY.getVar(), DBVar.ID_WORKER.getVar());
    private static final String DELETE_WORKER = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_WORKERS.getVar(), DBVar.DELETED.getVar(), DBVar.ID_WORKER.getVar());

    private Connection conn;
    private PreparedStatement pst = null;
    private PreparedStatement pstTemp = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSet rsTemp = null;


    @Override
    public void addWorker(Worker worker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_WORKER);
            pst.setString(1, worker.getFirstName());
            pst.setString(2, worker.getLastName());
            pst.setString(3, worker.getAddress());
            pst.setString(4, worker.getTelephone());
            pst.setDouble(5, worker.getSalary());
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        addPassport(worker.getPassport());
    }

    @Override
    public List<Worker> getAllWorkers() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Worker> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_WORKER);
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setId(rs.getInt(DBVar.ID_WORKER.getVar()));
                worker.setFirstName(rs.getString(DBVar.FIRST_NAME.getVar()));
                worker.setLastName(rs.getString(DBVar.LAST_NAME.getVar()));
                worker.setAddress(rs.getString(DBVar.ADDRESS.getVar()));
                worker.setTelephone(rs.getString(DBVar.TELEPHONE.getVar()));
                worker.setSalary(rs.getDouble(DBVar.SALARY.getVar()));
                worker.setPassport(getPassportInner(worker.getId()));
                result.add(worker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);
        }
        return result;
    }

    @Override
    public void updateWorker(Worker worker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_WORKER);
            pst.setString(1, worker.getFirstName());
            pst.setString(2, worker.getLastName());
            pst.setString(3, worker.getAddress());
            pst.setString(4, worker.getTelephone());
            pst.setDouble(5, worker.getSalary());
            pst.setInt(6, worker.getId());
            pst.execute();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public Worker getWorker(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Worker worker = new Worker();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_WORKER);
            pst.setInt(1, workerID);
            rs = pst.executeQuery();
            rs.first();
            worker.setId(rs.getInt(DBVar.ID_WORKER.getVar()));
            worker.setFirstName(rs.getString(DBVar.FIRST_NAME.getVar()));
            worker.setLastName(rs.getString(DBVar.LAST_NAME.getVar()));
            worker.setAddress(rs.getString(DBVar.ADDRESS.getVar()));
            worker.setTelephone(rs.getString(DBVar.TELEPHONE.getVar()));
            worker.setSalary(rs.getDouble(DBVar.SALARY.getVar()));
            worker.setPassport(getPassportInner(workerID));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);

        }
        return worker;
    }

    @Override
    public void deleteWorker(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_WORKER);
            pst.setInt(1, 1);
            pst.setInt(2, workerID);
            pst.execute();
            pst = conn.prepareStatement(DELETE_PASSPORT);
            pst.setInt(1, 1);
            pst.setInt(2, workerID);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }


    @Override
    public List<Worker> getWorkersBy(Worker worker) {
        return null;
    }


    /**
     * Table pasport 'extend' 1 in 1 from table workers
     */

    @Override
    public void addPassport(Passport passport) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_PASSPORT);
            pst.setInt(1, passport.getWorkerID());
            pst.setString(2, passport.getInfo());
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void deletePassport(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_PASSPORT);
            pst.setInt(1, 1);
            pst.setInt(2, workerID);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void updatePassport(Passport passport) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_PASSPORT);
            pst.setString(1, passport.getInfo());
            pst.setInt(2, passport.getWorkerID());
            pst.execute();
            conn.commit();
        } catch (
                SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }

    }

    @Override
    public List<Passport> getAllPassports() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Passport> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_PASSPORT);
            while (rs.next()) {
                Passport passport = new Passport();
                passport.setWorkerID(rs.getInt(DBVar.ID_PASSPORT.getVar()));
                passport.setInfo(rs.getString(DBVar.INFO.getVar()));
                result.add(passport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);
        }
        return result;
    }

    @Override
    public Passport getPassport(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Passport passport = new Passport();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_PASSPORT);
            pst.setInt(1, workerID);
            rs = pst.executeQuery();
            rs.first();
            passport.setWorkerID(workerID);
            passport.setInfo(rs.getString(DBVar.INFO.getVar()));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        return passport;
    }

    private Passport getPassportInner(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Passport passport = new Passport();
        try {
            pstTemp = conn.prepareStatement(GET_PASSPORT);
            pstTemp.setInt(1, workerID);
            rsTemp = pstTemp.executeQuery();
            rsTemp.first();
            passport.setWorkerID(workerID);
            passport.setInfo(rsTemp.getString(DBVar.INFO.getVar()));

        } catch (SQLException e) {
        } finally {
            getInstance().closeResaultSet(rsTemp);
            getInstance().closePreparedStatement(pstTemp);
        }
        return passport;
    }
}



