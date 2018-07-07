package dao.impl.factory;

import dao.impl.ClientDAOImpl;

public class DAOImplFactory {
    private static ClientDAOImpl instance;








    public synchronized static ClientDAOImpl getInstance() {
        if (instance == null) {
            instance = new ClientDAOImpl();
        }
        return instance;
    }
}
