package dao.impl.factory;

import dao.AdministratorDAO;
import dao.impl.*;

public class DAOImplFactory {
    private static CreateTableFactory createTableFactoryIns;
    private static AdministratorDAOImpl administratorIns;
    private static ClientDAOImpl clientIns;
    private static SpecializationDAOImpl specializationIns;
    private static SubscriptionDAOImpl subscriptionIns;
    private static VisitDAOImpl visitIns;
    private static WorkerDAOImpl workerIns;


    public static CreateTableFactory getCreateTableFactoryInstance() {
        if (createTableFactoryIns == null) {
            createTableFactoryIns = new CreateTableFactory();
        }
        return createTableFactoryIns;
    }


    public synchronized static ClientDAOImpl getClientInstance() {
        if (clientIns == null) {
            clientIns = new ClientDAOImpl();
        }
        return clientIns;
    }

    public synchronized static AdministratorDAOImpl getAdministratorInstance() {
        if (administratorIns == null) {
            administratorIns = new AdministratorDAOImpl();
        }
        return administratorIns;
    }

    public synchronized static SpecializationDAOImpl getSpecializationInstance() {
        if (specializationIns == null) {
            specializationIns = new SpecializationDAOImpl();
        }
        return specializationIns;
    }

    public synchronized static VisitDAOImpl getVisitInstance() {
        if (visitIns == null) {
            visitIns = new VisitDAOImpl();
        }
        return visitIns;
    }


    public synchronized static SubscriptionDAOImpl getSubscriptionInstance() {
        if (subscriptionIns == null) {
            subscriptionIns = new SubscriptionDAOImpl();
        }
        return subscriptionIns;
    }

    public synchronized static WorkerDAOImpl getWorkerInstance() {
        if (workerIns == null) {
            workerIns = new WorkerDAOImpl();
        }
        return workerIns;
    }


}
