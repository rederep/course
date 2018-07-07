package dao;


public interface VisitDAO {
    void addVisit(int clientId, int subscriptionId, int workerId );
    void deleteVisit(int clientId, int subscriptionId, int workerId);
    void updateVisit  (int clientId, int subscriptionId, int workerId );
   }
