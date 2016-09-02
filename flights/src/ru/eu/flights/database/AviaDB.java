package ru.eu.flights.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AviaDB {

    private static Connection conn;
    private static InitialContext ic;
    private static DataSource ds;

    private AviaDB() {
    }

    private static AviaDB instance;

    public static AviaDB getInstance() {
        if (instance == null)
            instance = new AviaDB();
        return instance;
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                ic = new InitialContext();
                ds = (DataSource) ic.lookup("java:app/jdbc/Avia");
                conn = ds.getConnection();
            }
        } catch (SQLException e) {
            Logger.getLogger(AviaDB.class.getName()).log(Level.SEVERE, null, e);
        } catch (NamingException e) {
            Logger.getLogger(AviaDB.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(AviaDB.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
