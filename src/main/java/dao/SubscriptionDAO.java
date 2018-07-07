package dao;

import model.Discount;
import model.Subscription;

import java.util.List;

public interface SubscriptionDAO {
    void addSubscription(Subscription subscription);
    void updateSubscription(int subscriptionID);
    void deleteSubscription(int subscriptionID);
    List<Subscription> getAllSubscriptions();
    Subscription getSubscription(int subscriptionID);
    List<Subscription> getSubscriptionsBy(Subscription subscription);

    void addDiscoint(Discount discount);
    void updateDiscount(int discountID);
    void deleteDiscount(int discountID);
    List<Discount> getAllDiscounts();
    Discount getDiscount(int discountID);

}
