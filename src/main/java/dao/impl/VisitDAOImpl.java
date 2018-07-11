package dao.impl;

import dao.DBVar;
import dao.VisitDAO;
import exception.BD.ChoosingClientEX;
import exception.BD.ChoosingSubscriptionEX;
import exception.BD.ChoosingWorkerEX;
import exception.BD.FileNotFoundBDConfigEX;
import lombok.NoArgsConstructor;
import model.Client;
import model.Discount;
import model.Visit;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.factory.ConnectFactory.getInstance;

@NoArgsConstructor
public class VisitDAOImpl implements VisitDAO {
    private static final String GET_ALL_VISITS = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_VISITS.getVar(), DBVar.DELETED.getVar());
    private static final String GET_VISITS = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_VISITS.getVar(), DBVar.ID_VISIT.getVar(), DBVar.DELETED.getVar());
    private static final String GET_VISITS_BY_CLIENT = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_VISITS.getVar(), DBVar.CLIENT_ID.getVar(), DBVar.DELETED.getVar());
    private static final String GET_VISITS_BY_SUBS = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_VISITS.getVar(), DBVar.SUBS_ID.getVar(), DBVar.DELETED.getVar());
    private static final String GET_VISITS_BY_WORKER = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_VISITS.getVar(), DBVar.WORKER_ID.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_VISITS = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);"
            , DBVar.DB_VISITS.getVar(), DBVar.CLIENT_ID.getVar(), DBVar.SUBS_ID.getVar(), DBVar.WORKER_ID.getVar());
    private static final String UPDATE_VISITS = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE %s = ?;"
            , DBVar.DB_VISITS.getVar(), DBVar.CLIENT_ID.getVar(), DBVar.SUBS_ID.getVar(), DBVar.WORKER_ID.getVar(), DBVar.ID_VISIT.getVar());
    private static final String DELETE_VISITS= String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_VISITS.getVar(), DBVar.DELETED.getVar(), DBVar.ID_VISIT.getVar());
    private static final String DELETE_VISITS_BY_CLIENT= String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_VISITS.getVar(), DBVar.DELETED.getVar(), DBVar.CLIENT_ID.getVar());

    private Connection conn;
    private PreparedStatement pst = null;
    private PreparedStatement pstTemp = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSet rsTemp = null;

    @Override
    public void addVisit(Visit visit) throws SQLException, FileNotFoundBDConfigEX, IOException, ClassNotFoundException, ChoosingClientEX, ChoosingSubscriptionEX, ChoosingWorkerEX {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_VISITS);
            try {
                pst.setInt(1, visit.getClientID());
            } catch (NullPointerException e) {
                throw new ChoosingClientEX();
            }
            try {
                pst.setInt(2, visit.getSubscriptionID());
            } catch (NullPointerException e) {
                throw new ChoosingSubscriptionEX();
            }
            try {
                pst.setInt(3, visit.getWorkerID());
            } catch (NullPointerException e) {
                throw new ChoosingWorkerEX();
            }
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void deleteVisit(int visitID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_VISITS);
            pst.setInt(1, 1);
            pst.setInt(2, visitID);
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }

    }

    @Override
    public void deleteVisitByClientID(int clientID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_VISITS_BY_CLIENT);
            pst.setInt(1, 1);
            pst.setInt(2, clientID);
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }

    }

    @Override
    public void updateVisit(Visit visit) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, ChoosingWorkerEX, ChoosingSubscriptionEX, ChoosingClientEX, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_VISITS);
            try {
                pst.setInt(1, visit.getClientID());
            } catch (NullPointerException e) {
                throw new ChoosingClientEX();
            }
            try {
                pst.setInt(2, visit.getSubscriptionID());
            } catch (NullPointerException e) {
                throw new ChoosingSubscriptionEX();
            }
            try {
                pst.setInt(3, visit.getWorkerID());
            } catch (NullPointerException e) {
                throw new ChoosingWorkerEX();
            }
            pst.setInt(4, visit.getId());
            pst.execute();
            conn.commit();
            conn.rollback();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public Visit getVisit(int visitID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Visit visit = new Visit();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_VISITS);
            pst.setInt(1, visitID);
            rs = pst.executeQuery();
            rs.first();
            visit.setId(rs.getInt(visitID));
            visit.setId(rs.getInt(DBVar.CLIENT_ID.getVar()));
            visit.setId(rs.getInt(DBVar.SUBS_ID.getVar()));
            visit.setId(rs.getInt(DBVar.WORKER_ID.getVar()));
        }  finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        return visit;
    }

    @Override
    public List<Visit> getAllVisitsID() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Visit> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_VISITS);
            while (rs.next()) {
                Visit visit = new Visit();
                visit.setId(rs.getInt(DBVar.ID_VISIT.getVar()));
                visit.setClientID(rs.getInt(DBVar.CLIENT_ID.getVar()));
                visit.setSubscriptionID(rs.getInt(DBVar.SUBS_ID.getVar()));
                visit.setWorkerID(rs.getInt(DBVar.WORKER_ID.getVar()));
                result.add(visit);
            }
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);
        }
        return result;
    }

    @Override
    public List<Visit> getAllVisitsByClientID(int clientId) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        return getVisitsBY(GET_VISITS_BY_CLIENT, clientId);
    }

    @Override
    public List<Visit> getAllVisitsByWorkerID(int workerId) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        return getVisitsBY(GET_VISITS_BY_WORKER, workerId);
    }

    @Override
    public List<Visit> getAllVisitsBySubsID(int subscriptionId) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        return getVisitsBY(GET_VISITS_BY_SUBS, subscriptionId);
    }



    private List<Visit> getVisitsBY(String sql, int id) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Visit> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Visit visit = new Visit();
                visit.setId(rs.getInt(DBVar.ID_VISIT.getVar()));
                visit.setClientID(rs.getInt(DBVar.CLIENT_ID.getVar()));
                visit.setSubscriptionID(rs.getInt(DBVar.SUBS_ID.getVar()));
                visit.setWorkerID(rs.getInt(DBVar.WORKER_ID.getVar()));
                result.add(visit);
            }
        }  finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        return result;
    }


}
