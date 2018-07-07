package dao.impl.factory;

import dao.impl.ClientDAOImpl;

public class DAOImplFactory {
    private static ClientDAOImpl instance;
    private static CreateTableFactory instanceCrtTable;

    public static CreateTableFactory getInstance1() {
        if (instanceCrtTable == null) {
            instanceCrtTable = new CreateTableFactory();
        }
        return instanceCrtTable;
    }

    public synchronized static ClientDAOImpl getInstance() {
        if (instance == null) {
            instance = new ClientDAOImpl();
        }
        return instance;

    }
}
