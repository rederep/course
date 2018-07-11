package dao;

import exception.BD.ChoosingSubscriptionEX;
import exception.BD.FileNotFoundBDConfigEX;
import model.Discount;
import model.SubsType;
import model.Subscription;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDAO {
    void addSubscription(Subscription subscription) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException, ChoosingSubscriptionEX;
    void updateSubscription(Subscription subscription) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Subscription> getAllSubscriptions() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    Subscription getSubscription(int subscriptionID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Subscription> getSubscriptionsBy(Subscription subscription);

    void addDiscount(Discount discount) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void updateDiscount(Discount discount) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void deleteDiscount(int discountID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<Discount> getAllDiscounts() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    Discount getDiscount(int discountID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;

    void addSubsType(SubsType subsType) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void updateSubsType(SubsType subsType) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    void deleteSubsType(int subTypeID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    List<SubsType> getAllSubsType() throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;
    SubsType getSubsType(int subTypeID) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException;


}
