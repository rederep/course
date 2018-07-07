package dao.impl.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ConnectFactory {
    private static final String FILE_PASSWORD_KEY = "BD.key";
    private static final String DRV = "com.mysql.jdbc.Driver";
    private static final String CONNECT = "jdbc:mysql://178.79.134.250:33061/";
    private static final String DB = "j18test";
    private static final String LOGIN = "j18test";
    private String pass = getBDPass();

     private static ConnectFactory instance;

    public ConnectFactory() throws ClassNotFoundException, IOException {
        Class.forName(DRV);
    }

    public Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECT + DB, LOGIN, pass);
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


    public static ConnectFactory getInstance() throws ClassNotFoundException, IOException {
        if (instance == null) {
            instance = new ConnectFactory();
        }
        return instance;
    }

    private String getBDPass() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_PASSWORD_KEY));
        String currentLine = br.readLine() ;
        br.close();
        return currentLine;
    }


}
