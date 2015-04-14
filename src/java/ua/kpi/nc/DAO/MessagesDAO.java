/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.kpi.nc.IDAO.MessagesIDAO;
import ua.kpi.nc.connection.ConnectionFactory;


/**
 *
 * @author Виктор
 */
public class MessagesDAO implements MessagesIDAO {

    Connection connection = null;

    @Override
    public ArrayList<Integer> getAllMessageId() {
        String sql = "select MESSAGES.MESSAGE_ID FROM FORUMADMIN.MESSAGES";
        PreparedStatement ptmt = null;
        ArrayList<Integer> list = new ArrayList();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("MESSAGE_ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    ConnectionFactory.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static void main(String[] args) {
        FactoryDAO factoryDAO = new FactoryDAO();
        ArrayList<Integer> al = factoryDAO.getMessagesDAO().getAllMessageId();
        System.out.println("");
    }
}
