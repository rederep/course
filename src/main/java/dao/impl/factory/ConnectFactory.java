package dao.impl.factory;

import java.sql.*;

public class ConnectFactory {

    private static final String DRV = "com.mysql.jdbc.Driver";
    private static final String CONNECT = "jdbc:mysql://178.79.134.250:33061/";
    private static final String DB = "j18test";
    private static final String LOGIN = "j18test";
    private static final String PASS = "j18parolj18";
    private static ConnectFactory instance;

    public ConnectFactory() throws ClassNotFoundException {
        Class.forName(DRV);
    }

    public Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECT + DB, LOGIN, PASS);
    }

    public void closeConnect(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }


    public void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {

            statement.close();

        }
    }

    public void closeResaultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }


    public static ConnectFactory getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectFactory();
        }
        return instance;
    }


}
