package dao.impl.factory;

import dao.DBVar;

import java.sql.*;

import static dao.impl.factory.ConnectFactory.getInstance;

public class CreateTableFactory {

    private Connection conn;
    private Statement stmt = null;

    private final String dropAllTable = "DROP TABLE IF EXISTS " + DBVar.DB_VISITS.getVar() + ", " + DBVar.DB_SPEC_OF_WORK.getVar() + ", " +
            DBVar.DB_SUBS.getVar() + ", " + DBVar.DB_SPEC.getVar() + ", " + DBVar.DB_DISC.getVar() + ", " + DBVar.DB_PASSPORT.getVar() + ", " +
            DBVar.DB_WORKERS.getVar() +", " + DBVar.DB_CLIENTS.getVar() + ";";

    private final String crtTableClients = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_CLIENTS.getVar() + " (" +
            DBVar.ID_CLIENT.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.FIRST_NAME.getVar() + " varchar(50) default null, " +
            DBVar.LAST_NAME.getVar() + " varchar(50) not null, " +
            DBVar.ADDRESS.getVar() + " varchar(100) default null, " +
            DBVar.TELEPHONE.getVar() + " varchar(20) not null, " +
            DBVar.DATE.getVar() + " date default null, " +
            DBVar.DELETED.getVar() + " tinyint default 0" +
            ");";

    private final String crtTableVisits = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_VISITS.getVar() + " (" +
            DBVar.ID_VISIT.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.CLIENT_ID.getVar() + " int unsigned not null," +
            DBVar.SUBS_ID.getVar() + " int unsigned not null, " +
            DBVar.WORKER_ID.getVar() + " int unsigned default null, " +
            DBVar.DELETED.getVar() + " tinyint default 0, " +
            "FOREIGN KEY (" + DBVar.CLIENT_ID.getVar() + ") REFERENCES " + DBVar.DB_CLIENTS.getVar() + " (" + DBVar.ID_CLIENT.getVar() + "), " +
            "FOREIGN KEY (" + DBVar.SUBS_ID.getVar() + ") REFERENCES " + DBVar.DB_SUBS.getVar() + " (" + DBVar.ID_SUBS.getVar() + "), " +
            "FOREIGN KEY (" + DBVar.WORKER_ID.getVar() + ") REFERENCES " + DBVar.DB_WORKERS.getVar() + " (" + DBVar.ID_WORKER.getVar() + ")" +
            ");";

    private final String crtTableWorkers = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_WORKERS.getVar() + " (" +
            DBVar.ID_WORKER.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.FIRST_NAME.getVar() + " varchar(50) default null, " +
            DBVar.LAST_NAME.getVar() + " varchar(50) not null, " +
            DBVar.ADDRESS.getVar() + " varchar(100) default null, " +
            DBVar.TELEPHONE.getVar() + " varchar(20) not null, " +
            DBVar.SALARY.getVar() + " double not null default 0, " +
            DBVar.DELETED.getVar() + " tinyint default 0" +

            ");";

    private final String crtTableSubscriptions = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_SUBS.getVar() + " (" +
            DBVar.ID_SUBS.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.TITLE.getVar() + " varchar(20) not null default 'new_subs', " +
            DBVar.PRICE.getVar() + " double not null default 0, " +
            DBVar.NUMBER_VISITS.getVar() + " int not null default 0, " +
            DBVar.NUMBER_DAYS.getVar() + " int not null default 0, " +
            DBVar.DATE_BEGIN.getVar() + " date default null, " +
            DBVar.DATE_END.getVar() + " date default null, " +
            DBVar.DISC_ID.getVar() + " int unsigned default null, " +
            DBVar.DELETED.getVar() + " tinyint default 0, " +
            "FOREIGN KEY (" + DBVar.DISC_ID.getVar() + ") REFERENCES " + DBVar.DB_DISC.getVar() + " (" + DBVar.ID_DISC.getVar() + ")" +
            ");";

    private final String crtTableSpecOfWorks = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_SPEC_OF_WORK.getVar() + " (" +
            DBVar.ID_SPWORK.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.WORKER_ID.getVar() + " int unsigned not null, " +
            DBVar.SPEC_ID.getVar() + " int unsigned not null, " +
            DBVar.DATE.getVar() + " TIMESTAMP not null default CURRENT_TIMESTAMP, " +
            DBVar.NOTE.getVar() + " varchar(255) default null, " +
            DBVar.DELETED.getVar() + " tinyint default 0," +
            "FOREIGN KEY (" + DBVar.WORKER_ID.getVar() + ") REFERENCES " + DBVar.DB_WORKERS.getVar() + " (" + DBVar.ID_WORKER.getVar() + "), " +
            "FOREIGN KEY (" + DBVar.SPEC_ID.getVar() + ") REFERENCES " + DBVar.DB_SPEC.getVar() + " (" + DBVar.ID_SPEC.getVar() + ")" +
            ");";

    private final String crtTableDiscouns = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_DISC.getVar() + " (" +
            DBVar.ID_DISC.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.TITLE.getVar() + " varchar(20) not null default 'new_disc', " +
            DBVar.NOTE.getVar() + " varchar(255) default null, " +
            DBVar.DELETED.getVar() + " tinyint default 0" +
            ");";

    private final String crtTablePassport = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_PASSPORT.getVar() + " (" +
            DBVar.ID_PASSPORT.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.INFO.getVar() + " varchar(200) not null, " +
            DBVar.DELETED.getVar() + " tinyint default 0, " +
            "FOREIGN KEY (" + DBVar.ID_PASSPORT.getVar() + ") REFERENCES " + DBVar.DB_WORKERS.getVar() + " (" + DBVar.ID_WORKER.getVar() + ")" +
            ");";

    private final String crtTableSpecializations = "CREATE TABLE IF NOT EXISTS " + DBVar.DB_SPEC.getVar() + " (" +
            DBVar.ID_SPEC.getVar() + " int unsigned not null auto_increment primary key, " +
            DBVar.DENIM.getVar() + " varchar(200) not null, " +
            DBVar.DELETED.getVar() + " tinyint default 0" +
            ");";

    /**
     * Order is important! Table with FOREIGN KEY and REFERENCES
     */
    public void createAllTableIfNotExists() throws ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            stmt.execute(crtTableClients);
            stmt.execute(crtTableWorkers);
            stmt.execute(crtTablePassport);
            stmt.execute(crtTableDiscouns);
            stmt.execute(crtTableSpecializations);
            stmt.execute(crtTableSubscriptions);
            stmt.execute(crtTableSpecOfWorks);
            stmt.execute(crtTableVisits);
        } finally {
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);
        }
    }

    public void dropAllTables() throws ClassNotFoundException, SQLException {
        try {
            conn = getInstance().getConnect();
            stmt = conn.createStatement();
            stmt.execute(dropAllTable);
        } finally {
            getInstance().closeStatement(stmt);
            getInstance().closeConnect(conn);
        }
    }
}
