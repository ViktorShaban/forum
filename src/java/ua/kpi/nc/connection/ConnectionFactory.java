/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Виктор
 */
public class ConnectionFactory {

    private static final int maxConn = 100;
    private static final ArrayList<Connection> freeConnections = new ArrayList<>();

    public static synchronized Connection getConnection() {
        Connection con = null;
        if (!freeConnections.isEmpty()) {
            con = freeConnections.get(freeConnections.size() - 1);
            freeConnections.remove(con);
            try {
                if (con.isClosed()) {
                    // Try again recursively
                    con = getConnection();
                }
            } catch (SQLException e) {
                // Try again recursively
                con = getConnection();
            } catch (Exception e) {
                // Try again recursively
                con = getConnection();
            }
        } else {
            con = newConnection();
        }

        return con;
    }

    private static Connection newConnection() {
        ResourceBundle resource = ResourceBundle.getBundle("ua.kpi.nc.connection.database");
        String url = resource.getString("url");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        Connection con = null;

        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static synchronized void freeConnection(Connection con) {
        // Put the connection at the end of the List
        if ((con != null) && (freeConnections.size() <= maxConn)) {
            freeConnections.add(con);
        }
    }

    public synchronized void release() {
        Iterator allConnections = freeConnections.iterator();
        while (allConnections.hasNext()) {
            Connection con = (Connection) allConnections.next();
            try {
                con.close();
            } catch (SQLException e) {
                //log("Can't close connection for pool ");
            }
        }
        freeConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
        release();
    }
}
