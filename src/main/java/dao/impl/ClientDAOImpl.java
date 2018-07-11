package dao.impl;

import dao.ClientDAO;
import dao.DBVar;
import dao.DeleteDAO;
import dao.impl.factory.DAOImplFactory;
import exception.BD.FileNotFoundBDConfigEX;
import lombok.NoArgsConstructor;
import model.Client;
import model.Visit;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dao.impl.factory.ConnectFactory.getInstance;


@NoArgsConstructor
public class ClientDAOImpl implements ClientDAO, DeleteDAO {
    private static final String GET_ALL_CLIENT = String.format("SELECT * FROM %s where %s != 1;"
            , DBVar.DB_CLIENTS.getVar(), DBVar.DELETED.getVar());
    private static final String GET_ALL_CLIENT_BY_NAME = String.format("SELECT * FROM %s where (%s like ? or %s like ?) and %s != 1;"
            , DBVar.DB_CLIENTS.getVar(),DBVar.FIRST_NAME.getVar(), DBVar.LAST_NAME.getVar(), DBVar.DELETED.getVar());
    private static final String GET_CLIENT = String.format("SELECT * FROM %s where %s=? and %s != 1;"
            , DBVar.DB_CLIENTS.getVar(), DBVar.ID_CLIENT, DBVar.DELETED.getVar());
    private static final String INSERT_CLIENT = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);"
            , DBVar.DB_CLIENTS.getVar(), DBVar.FIRST_NAME.getVar(), DBVar.LAST_NAME.getVar(), DBVar.ADDRESS.getVar(),
            DBVar.TELEPHONE.getVar(), DBVar.DATE.getVar());
    private static final String DELETE_CLIENT = String.format("UPDATE %s SET %s = ?  WHERE %s = ?;"
            , DBVar.DB_CLIENTS.getVar(), DBVar.DELETED.getVar(), DBVar.ID_CLIENT.getVar());
    private static final String UPDATE_CLIENT = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?  WHERE %s = ?;"
            , DBVar.DB_CLIENTS.getVar(), DBVar.FIRST_NAME.getVar(), DBVar.LAST_NAME.getVar(), DBVar.ADDRESS.getVar(),
            DBVar.TELEPHONE.getVar(), DBVar.DATE.getVar(), DBVar.ID_CLIENT.getVar());

    private Connection conn;
    private PreparedStatement pst = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    @Override
    public void addClient(Client client) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(INSERT_CLIENT);
            pst.setString(1, client.getFirstName());
            pst.setString(2, client.getLastName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getTelephone());
            pst.setDate(5, Date.valueOf(client.getDate()));
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }


    @Override
    public List<Client> getAllClients() throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX {
        List<Client> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_CLIENT);
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt(DBVar.ID_CLIENT.getVar()));
                client.setFirstName(rs.getString(DBVar.FIRST_NAME.getVar()));
                client.setLastName(rs.getString(DBVar.LAST_NAME.getVar()));
                client.setAddress(rs.getString(DBVar.ADDRESS.getVar()));
                client.setTelephone(rs.getString(DBVar.TELEPHONE.getVar()));
                client.setDate(rs.getDate(DBVar.DATE.getVar()).toLocalDate());
                result.add(client);
            }
        }  finally {
            getInstance().closeResaultSet(rs);
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);

        }
        return result;
    }

    @Override
    public Client getClient(int clientID) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX {
        Client client = new Client();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_CLIENT);
            pst.setInt(1, clientID);
            rs = pst.executeQuery();
            rs.first();
                client.setId(rs.getInt(DBVar.ID_CLIENT.getVar()));
                client.setFirstName(rs.getString(DBVar.FIRST_NAME.getVar()));
                client.setLastName(rs.getString(DBVar.LAST_NAME.getVar()));
                client.setAddress(rs.getString(DBVar.ADDRESS.getVar()));
                client.setTelephone(rs.getString(DBVar.TELEPHONE.getVar()));
                client.setDate(rs.getDate(DBVar.DATE.getVar()).toLocalDate());
        }  finally {
            getInstance().closeResaultSet(rs);
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);

        }
        return client;
    }

    @Override
    public void updateClient(Client client) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX {
        try {
            conn = getInstance().getConnect();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(UPDATE_CLIENT);
            pst.setString(1, client.getFirstName());
            pst.setString(2, client.getLastName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getTelephone());
            pst.setDate(5, Date.valueOf(client.getDate()));
            pst.setInt(6, client.getId());
            pst.execute();
            conn.commit();

        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }
    }

    @Override
    public void delete(int id) throws IOException, ClassNotFoundException, SQLException, FileNotFoundBDConfigEX {
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(DELETE_CLIENT);
            pst.setInt(1, 1);
            pst.setInt(2, id);
            pst.execute();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnect(conn);
        }

        List<Integer> lstSubsID = DAOImplFactory.getVisitInstance().getAllVisitsByClientID(id).stream()
                .map(Visit::getSubscriptionID)
                .collect(Collectors.toList());
        DAOImplFactory.getVisitInstance().deleteVisitByClientID(id);
        for (Integer integer : lstSubsID) {
            DAOImplFactory.getSubscriptionInstance().delete(integer);
        }
    }

    @Override
    public List<Client> getClientsByName(String name) throws FileNotFoundBDConfigEX, IOException, ClassNotFoundException, SQLException {
        List<Client> result = new ArrayList<>();
        try {
            conn = getInstance().getConnect();
            pst = conn.prepareStatement(GET_ALL_CLIENT_BY_NAME);
            pst.setString(1, "%" + name + "%");
            pst.setString(2, "%" + name + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt(DBVar.ID_CLIENT.getVar()));
                client.setFirstName(rs.getString(DBVar.FIRST_NAME.getVar()));
                client.setLastName(rs.getString(DBVar.LAST_NAME.getVar()));
                client.setAddress(rs.getString(DBVar.ADDRESS.getVar()));
                client.setTelephone(rs.getString(DBVar.TELEPHONE.getVar()));
                client.setDate(rs.getDate(DBVar.DATE.getVar()).toLocalDate());
                result.add(client);
            }
        }finally {
            getInstance().closeResaultSet(rs);
            getInstance().closeStatement(pst);
            getInstance().closeConnect(conn);

        }
        return result;
    }
}
