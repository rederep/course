package dao.impl;

import dao.DBVar;
import dao.SpecializationDAO;
import exception.BD.FileNotFoundBDConfigEX;
import lombok.NoArgsConstructor;
import model.SpecByWorker;
import model.Specialization;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.factory.ConnectFactory.getInstance;

@NoArgsConstructor
public class SpecializationDAOImpl implements SpecializationDAO {
    private static final String GET_ALL_SPEC = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_SPEC.getVar(), DBVar.DELETED.getVar());
    private static final String GET_SPEC = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_SPEC.getVar(), DBVar.ID_SPEC.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_SPEC = String.format("INSERT INTO %s (%s) VALUES (?);"
            , DBVar.DB_SPEC.getVar(), DBVar.DENIM.getVar());
    private static final String DELETE_SPEC = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_SPEC.getVar(), DBVar.DELETED.getVar(), DBVar.ID_SPEC.getVar());
    private static final String UPDATE_SPEC = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_SPEC.getVar(), DBVar.DENIM.getVar(), DBVar.ID_SPEC.getVar());

    private static final String GET_ALL_SPEC_BY_WORK_INNER = String.format("SELECT %s, %s, %s, %s, %s FROM %s as tb1 " +
                    "INNER JOIN %s as tb2 on tb1.%s = tb2.%s where %s != 1;"
            , DBVar.SPWORK_ID.getVar(), DBVar.WORKER_ID.getVar(), DBVar.DATE.getVar(), DBVar.NOTE.getVar(), DBVar.DENIM.getVar()
            , DBVar.DB_SPEC_OF_WORK.getVar(), DBVar.DB_SPEC.getVar(), DBVar.SPEC_ID.getVar(), DBVar.ID_SPEC.getVar(), DBVar.DELETED.getVar());
    private static final String GET_ALL_SPEC_BY_WORK = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_SPEC_OF_WORK.getVar(), DBVar.DELETED.getVar());
    private static final String GET_SPEC_BY_WORK_INNER = String.format("SELECT %s, %s, %s, %s, %s FROM %s as tb1 " +
                    "INNER JOIN %s as tb2 on tb1.%s = tb2.%s where %s=? and %s != 1;"
            , DBVar.SPWORK_ID.getVar(), DBVar.WORKER_ID.getVar(), DBVar.DATE.getVar(), DBVar.NOTE.getVar(), DBVar.DENIM.getVar()
            , DBVar.DB_SPEC_OF_WORK.getVar(), DBVar.DB_SPEC.getVar(), DBVar.SPEC_ID.getVar(), DBVar.ID_SPEC.getVar(), DBVar.WORKER_ID.getVar(), DBVar.DELETED.getVar());
    private static final String GET_SPEC_BY_WORK = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_SPEC_OF_WORK.getVar(), DBVar.WORKER_ID.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_SPEC_BY_WORK = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?);"
            , DBVar.DB_SPEC_OF_WORK.getVar(), DBVar.WORKER_ID.getVar(), DBVar.SPEC_ID.getVar(),
            DBVar.DATE.getVar(), DBVar.NOTE.getVar());
    private static final String UPDATE_SPEC_BY_WORK = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?  WHERE %s = ?;"
            , DBVar.DB_SPEC_OF_WORK.getVar(), DBVar.WORKER_ID.getVar(), DBVar.SPEC_ID.getVar(),
            DBVar.DATE.getVar(), DBVar.NOTE.getVar(), DBVar.ID_SPWORK.getVar());
    private static final String DELETE_SPEC_BY_WORK = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_SPEC_OF_WORK.getVar(), DBVar.DELETED.getVar(), DBVar.ID_SPWORK.getVar());

    private Connection conn;
    private PreparedStatement pst = null;
    private PreparedStatement pstTemp = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSet rsTemp = null;


    @Override
    public void addSpecByWorker(SpecByWorker specByWorker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_SPEC_BY_WORK);
            pst.setInt(1, specByWorker.getWorkerID());
            pst.setInt(2, specByWorker.getSpecialization().getId());
            pst.setDate(3, Date.valueOf(specByWorker.getDate()));
            pst.setString(4, specByWorker.getNote());
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }

    }

    @Override
    public void updateSpecByWorker(SpecByWorker specByWorker) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_SPEC_BY_WORK);
            pst.setInt(1, specByWorker.getWorkerID());
            pst.setInt(2, specByWorker.getSpecialization().getId());
            pst.setDate(3, Date.valueOf(specByWorker.getDate()));
            pst.setString(4, specByWorker.getNote());

            pst.execute();
            conn.commit();
            conn.rollback();

        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void deleteSpecByWorker(int specByWorkerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_SPEC_BY_WORK);
            pst.setInt(1, 1);
            pst.setInt(2, specByWorkerID);
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }

    }

    @Override
    public List<SpecByWorker> getAllSpecByWorkers() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<SpecByWorker> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_SPEC_BY_WORK);
            while (rs.next()) {
                SpecByWorker spc = new SpecByWorker();
                spc.setId(rs.getInt(DBVar.ID_SPWORK.getVar()));
                spc.setWorkerID(rs.getInt(DBVar.WORKER_ID.getVar()));
                spc.setDate(rs.getDate(DBVar.DATE.getVar()).toLocalDate());
                spc.setNote(rs.getString(DBVar.NOTE.getVar()));
                spc.setSpecialization(getSpecializationInner(rs.getInt(DBVar.SPEC_ID.getVar())));
                result.add(spc);
            }
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);
        }
        return result;
    }

    @Override
    public List<SpecByWorker> getSpecByWorkers(int workerID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<SpecByWorker> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_SPEC_BY_WORK);
            pst.setInt(1, workerID);
            rs = pst.executeQuery();
            while (rs.next()) {
                SpecByWorker spc = new SpecByWorker();
                spc.setId(rs.getInt(DBVar.ID_SPWORK.getVar()));
                spc.setWorkerID(rs.getInt(DBVar.WORKER_ID.getVar()));
                spc.setDate(rs.getDate(DBVar.DATE.getVar()).toLocalDate());
                spc.setNote(rs.getString(DBVar.NOTE.getVar()));
                spc.setSpecialization(getSpecializationInner(rs.getInt(DBVar.SPEC_ID.getVar())));
                result.add(spc);
            }
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);

        }
        return result;
    }


    /**
     * Table specializations 'extend' from table specialization_of_workers
     */
    @Override
    public void addSpecialization(Specialization specialization) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_SPEC);
            pst.setString(1, specialization.getDenomination());
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void updateSpecialization(Specialization specialization) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_SPEC);
            pst.setString(1, specialization.getDenomination());
            pst.setInt(2, specialization.getId());
            pst.execute();
            conn.commit();
            conn.rollback();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void deleteSpecialization(int specID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_SPEC);
            pst.setInt(1, 1);
            pst.setInt(2, specID);
            pst.execute();
        }  finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public List<Specialization> getAllSpecializations() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Specialization> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_SPEC);
            while (rs.next()) {
                Specialization spec = new Specialization();
                spec.setId(rs.getInt(DBVar.ID_SPEC.getVar()));
                spec.setDenomination(rs.getString(DBVar.DENIM.getVar()));
                result.add(spec);
            }
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);
        }
        return result;
    }

    @Override
    public Specialization getSpecialization(int specID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Specialization spec = new Specialization();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_SPEC);
            pst.setInt(1, specID);
            rs = pst.executeQuery();
            rs.first();
            spec.setId(rs.getInt(DBVar.ID_SPEC.getVar()));
            spec.setDenomination(rs.getString(DBVar.DENIM.getVar()));

        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        return spec;
    }

    private Specialization getSpecializationInner(int specID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Specialization spec = new Specialization();
        try {
            pstTemp = conn.prepareStatement(GET_SPEC);
            pstTemp.setInt(1, specID);
            rsTemp = pstTemp.executeQuery();
            rsTemp.first();
            spec.setId(rsTemp.getInt(DBVar.ID_SPEC.getVar()));
            spec.setDenomination(rsTemp.getString(DBVar.DENIM.getVar()));

        } finally {
            getInstance().closeResaultSet(rsTemp);
            getInstance().closePreparedStatement(pstTemp);
        }
        return spec;
    }

}
