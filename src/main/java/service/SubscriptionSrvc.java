package service;

import dao.impl.SubscriptionDAOImpl;
import dao.impl.factory.DAOImplFactory;
import exception.BD.ChoosingSubscriptionEX;
import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.Discount;
import model.SubsType;
import model.Subscription;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionSrvc {
    private SubscriptionDAOImpl subscriptionDAO;

    public SubscriptionSrvc() {
        subscriptionDAO = DAOImplFactory.getSubscriptionInstance();
    }

    public void addSubscription(Subscription subscription) throws FileNotFoundBDConfigEX, ChoosingSubscriptionEX {
        try {
            subscriptionDAO.addSubscription(subscription);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSubscription(Subscription subscription) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.updateSubscription(subscription);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubscription(int subscriptionID) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.deleteSubscription(subscriptionID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subscription> getAllSubscriptions() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Subscription> subscriptionList = null;
        try {
            subscriptionList = subscriptionDAO.getAllSubscriptions();
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("All Subscriptions");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptionList;
    }

    public Subscription getSubscription(int subscriptionID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        Subscription subscription = null;
        try {
            subscription = subscriptionDAO.getSubscription(subscriptionID);
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Subscription");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscription;
    }

    public List<Subscription> getSubscriptionsBy(Subscription subscription) {
        List<Subscription> subscriptionList = null;
        subscriptionDAO.getSubscriptionsBy(subscription);
        return subscriptionList;
    }

    public void addDiscount(Discount discount) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.addDiscount(discount);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiscount(Discount discount) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.updateDiscount(discount);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDiscount(int discountID) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.deleteDiscount(discountID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Discount> getAllDiscounts() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<Discount> discountList = null;
        try {
            discountList = subscriptionDAO.getAllDiscounts();
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("All Discounts");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return discountList;
    }

    public Discount getDiscount(int discountID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        Discount discount = null;
        try {
            discount = subscriptionDAO.getDiscount(discountID);
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Discount");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }

    public void addSubsType(SubsType subsType) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.addSubsType(subsType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSubsType(SubsType subsType) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.updateSubsType(subsType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubsType(int subTypeID) throws FileNotFoundBDConfigEX {
        try {
            subscriptionDAO.deleteSubsType(subTypeID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SubsType> getAllSubsType() throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        List<SubsType> subsTypeList = null;
        try {
            subsTypeList = subscriptionDAO.getAllSubsType();
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("All Subscriptions Type");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subsTypeList;
    }

    public SubsType getSubsType(int subTypeID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        SubsType subsTypet = null;
        try {
            subsTypet = subscriptionDAO.getSubsType(subTypeID);
        } catch (NullPointerException e) {
            throw new ModelNotFoundEX("Subscription Type");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subsTypet;
    }
}
