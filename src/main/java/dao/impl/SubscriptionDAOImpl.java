package dao.impl;

import dao.DBVar;
import dao.SubscriptionDAO;
import exception.BD.ChoosingSubscriptionEX;
import exception.BD.FileNotFoundBDConfigEX;
import lombok.NoArgsConstructor;
import model.Discount;
import model.SubsType;
import model.Subscription;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.factory.ConnectFactory.getInstance;

@NoArgsConstructor
public class SubscriptionDAOImpl implements SubscriptionDAO {
    private static final String GET_ALL_DISCOUNT = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_DISC.getVar(), DBVar.DELETED.getVar());
    private static final String GET_DISCOUNT = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_DISC.getVar(), DBVar.ID_DISC.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_DISCOUNT = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?);"
            , DBVar.DB_DISC.getVar(), DBVar.TITLE.getVar(), DBVar.NOTE.getVar());
    private static final String DELETE_DISCOUNT = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_DISC.getVar(), DBVar.DELETED.getVar(), DBVar.ID_DISC.getVar());
    private static final String UPDATE_DISCOUNT = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_DISC.getVar(), DBVar.TITLE.getVar(), DBVar.NOTE.getVar(), DBVar.ID_DISC.getVar());


    private static final String GET_ALL_SUBS_TYPE = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_SUBS_TYPE.getVar(), DBVar.DELETED.getVar());
    private static final String GET_SUBS_TYPE = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_SUBS_TYPE.getVar(), DBVar.ID_SUBS_TYPE.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_SUBS_TYPE = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?);"
            , DBVar.DB_SUBS_TYPE.getVar(), DBVar.TITLE.getVar(), DBVar.NUMBER_VISITS.getVar(), DBVar.NUMBER_DAYS.getVar(), DBVar.NOTE.getVar());
    private static final String DELETE_SUBS_TYPE = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_SUBS_TYPE.getVar(), DBVar.DELETED.getVar(), DBVar.ID_SUBS_TYPE.getVar());
    private static final String UPDATE_SUBS_TYPE = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_SUBS_TYPE.getVar(), DBVar.TITLE.getVar(), DBVar.NUMBER_VISITS.getVar(), DBVar.NUMBER_DAYS.getVar(), DBVar.NOTE.getVar(), DBVar.ID_SUBS_TYPE.getVar());


    private static final String GET_ALL_SUBS = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_SUBS.getVar(), DBVar.DELETED.getVar());
    private static final String GET_SUBS = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_SUBS.getVar(), DBVar.ID_SUBS.getVar(), DBVar.DELETED.getVar());
    private static final String INSERT_SUBS = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);"
            , DBVar.DB_SUBS.getVar(), DBVar.SUBS_TYPE_ID.getVar(), DBVar.PRICE.getVar(), DBVar.NUMBER_VISITS_LEFT.getVar()
            , DBVar.DATE_BEGIN.getVar(), DBVar.DATE_END.getVar(), DBVar.DISC_ID.getVar());
    private static final String UPDATE_SUBS = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?    WHERE %s = ?;"
            , DBVar.DB_SUBS.getVar(), DBVar.SUBS_TYPE_ID.getVar(), DBVar.PRICE.getVar(), DBVar.NUMBER_VISITS_LEFT.getVar()
            , DBVar.DATE_BEGIN.getVar(), DBVar.DATE_END.getVar(), DBVar.DISC_ID.getVar(), DBVar.ID_SUBS.getVar());
    private static final String DELETE_SUBS = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_SUBS.getVar(), DBVar.DELETED.getVar(), DBVar.ID_SUBS.getVar());

    private Connection conn;
    private PreparedStatement pst = null;
    private PreparedStatement pstTemp = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSet rsTemp = null;

    @Override
    public void addSubscription(Subscription subscription) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException, ChoosingSubscriptionEX {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_SUBS);
            try {
            pst.setInt(1, subscription.getSubsType().getId());
            }catch (NullPointerException e){
               throw new ChoosingSubscriptionEX();
            }
            pst.setDouble(2, subscription.getPrice());
            pst.setInt(3, subscription.getNumberVisitsLeft());
            pst.setDate(4, Date.valueOf(subscription.getDateBegin()));
            pst.setDate(5, Date.valueOf(subscription.getDateEnd()));
            try {
                pst.setInt(6, subscription.getDiscount().getId());
            }catch (NullPointerException e){
                pst.setInt(6, 1);
            }
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void updateSubscription(Subscription subscription) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_SUBS);
            pst.setInt(1, subscription.getSubsType().getId());
            pst.setDouble(2, subscription.getPrice());
            pst.setInt(3, subscription.getNumberVisitsLeft());
            pst.setDate(4, Date.valueOf(subscription.getDateBegin()));
            pst.setDate(5, Date.valueOf(subscription.getDateEnd()));
            pst.setInt(6, subscription.getDiscount().getId());
            pst.setInt(7, subscription.getId());
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
    public void deleteSubscription(int subscriptionID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_SUBS);
            pst.setInt(1, 1);
            pst.setInt(2, subscriptionID);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Subscription> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_SUBS);
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getInt(DBVar.ID_SUBS.getVar()));
                subscription.setSubsType(getSubsTypeInner(rs.getInt(DBVar.SUBS_TYPE_ID.getVar())));
                subscription.setPrice(rs.getDouble(DBVar.PRICE.getVar()));
                subscription.setNumberVisitsLeft(rs.getInt(DBVar.NUMBER_VISITS_LEFT.getVar()));
                 subscription.setDateBegin(rs.getDate(DBVar.DATE_BEGIN.getVar()).toLocalDate());
                subscription.setDateEnd(rs.getDate(DBVar.DATE_END.getVar()).toLocalDate());
                subscription.setDiscount(getDiscountInner(rs.getInt(DBVar.DISC_ID.getVar())));
                result.add(subscription);
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
    public Subscription getSubscription(int subscriptionID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Subscription subscription = new Subscription();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_SUBS);
            pst.setInt(1, subscriptionID);
            rs = pst.executeQuery();
            rs.first();
            subscription.setId(rs.getInt(DBVar.ID_SUBS.getVar()));
            subscription.setSubsType(getSubsTypeInner(rs.getInt(DBVar.SUBS_TYPE_ID.getVar())));
            subscription.setPrice(rs.getDouble(DBVar.PRICE.getVar()));
            subscription.setNumberVisitsLeft(rs.getInt(DBVar.NUMBER_VISITS_LEFT.getVar()));
            subscription.setDateBegin(rs.getDate(DBVar.DATE_BEGIN.getVar()).toLocalDate());
            subscription.setDateEnd(rs.getDate(DBVar.DATE_END.getVar()).toLocalDate());
            subscription.setDiscount(getDiscountInner(rs.getInt(DBVar.DISC_ID.getVar())));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        return subscription;
    }

    @Override
    public List<Subscription> getSubscriptionsBy(Subscription subscription) {
        return null;
    }


    @Override
    public void addDiscount(Discount discount) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_DISCOUNT);
            pst.setString(1, discount.getTitle());
            pst.setString(2, discount.getNote());
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void updateDiscount(Discount discount) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_DISCOUNT);
            pst.setString(1, discount.getTitle());
            pst.setString(2, discount.getNote());
            pst.setInt(3, discount.getId());
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
    public void deleteDiscount(int discountID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_DISCOUNT);
            pst.setInt(1, 1);
            pst.setInt(2, discountID);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public List<Discount> getAllDiscounts() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Discount> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_DISCOUNT);
            while (rs.next()) {
                Discount discount = new Discount();
                discount.setId(rs.getInt(DBVar.ID_DISC.getVar()));
                discount.setTitle(rs.getString(DBVar.TITLE.getVar()));
                discount.setNote(rs.getString(DBVar.NOTE.getVar()));
                result.add(discount);
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
    public Discount getDiscount(int discountID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Discount discount = new Discount();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_DISCOUNT);
            pst.setInt(1, discountID);
            rs = pst.executeQuery();
            rs.first();
            discount.setId(discountID);
            discount.setTitle(rs.getString(DBVar.TITLE.getVar()));
            discount.setNote(rs.getString(DBVar.NOTE.getVar()));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        return discount;
    }

    @Override
    public void addSubsType(SubsType subsType) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_SUBS_TYPE);
            pst.setString(1, subsType.getTitle());
            pst.setInt(2, subsType.getNumber_visits());
            pst.setInt(3, subsType.getNumber_days());
            pst.setString(4, subsType.getNote());
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void updateSubsType(SubsType subsType) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_SUBS_TYPE);
            pst.setString(1, subsType.getTitle());
            pst.setInt(2, subsType.getNumber_visits());
            pst.setInt(3, subsType.getNumber_days());
            pst.setString(4, subsType.getNote());
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
    public void deleteSubsType(int subTypeID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_SUBS_TYPE);
            pst.setInt(1, 1);
            pst.setInt(2, subTypeID);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public List<SubsType> getAllSubsType() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<SubsType> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_SUBS_TYPE);
            while (rs.next()) {
                SubsType subsType = new SubsType();
                subsType.setId(rs.getInt(DBVar.ID_SUBS_TYPE.getVar()));
                subsType.setTitle(rs.getString(DBVar.TITLE.getVar()));
                subsType.setNumber_visits(rs.getInt(DBVar.NUMBER_VISITS.getVar()));
                subsType.setNumber_days(rs.getInt(DBVar.NUMBER_DAYS.getVar()));
                subsType.setNote(rs.getString(DBVar.NOTE.getVar()));
                result.add(subsType);
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
    public SubsType getSubsType(int subTypeID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        SubsType subsType = new SubsType();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_SUBS_TYPE);
            pst.setInt(1, subTypeID);
            rs = pst.executeQuery();
            rs.first();
            subsType.setId(subTypeID);
            subsType.setTitle(rs.getString(DBVar.TITLE.getVar()));
            subsType.setNumber_visits(rs.getInt(DBVar.NUMBER_VISITS.getVar()));
            subsType.setNumber_days(rs.getInt(DBVar.NUMBER_DAYS.getVar()));
            subsType.setNote(rs.getString(DBVar.NOTE.getVar()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
        return subsType;
    }


    private SubsType getSubsTypeInner(int subTypeID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        SubsType subsType = new SubsType();
        try {
            pstTemp = conn.prepareStatement(GET_SUBS_TYPE);
            pstTemp.setInt(1, subTypeID);
            rsTemp =  pstTemp.executeQuery();
            rsTemp.first();
            subsType.setId(subTypeID);
            subsType.setTitle(rsTemp.getString(DBVar.TITLE.getVar()));
            subsType.setNumber_visits(rsTemp.getInt(DBVar.NUMBER_VISITS.getVar()));
            subsType.setNumber_days(rsTemp.getInt(DBVar.NUMBER_DAYS.getVar()));
            subsType.setNote(rsTemp.getString(DBVar.NOTE.getVar()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rsTemp);
            getInstance().closePreparedStatement(pstTemp);
        }
        return subsType;
    }

    private Discount getDiscountInner(int discountID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        Discount discount = new Discount();
        try {
            pstTemp = conn.prepareStatement(GET_DISCOUNT);
            pstTemp.setInt(1, discountID);
            rsTemp = pstTemp.executeQuery();
            rsTemp.first();
            discount.setId(discountID);
            discount.setTitle(rsTemp.getString(DBVar.TITLE.getVar()));
            discount.setNote(rsTemp.getString(DBVar.NOTE.getVar()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResaultSet(rsTemp);
            getInstance().closePreparedStatement(pstTemp);
        }
        return discount;
    }


}
