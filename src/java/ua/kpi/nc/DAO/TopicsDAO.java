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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.kpi.nc.IDAO.TopicsIDAO;
import ua.kpi.nc.connection.ConnectionFactory;
import ua.kpi.nc.entities.Messages;
import ua.kpi.nc.entities.Questions;
import ua.kpi.nc.entities.Topics;

/**
 *
 * @author Виктор
 */
public class TopicsDAO implements TopicsIDAO {

    Connection connection = null;

    @Override
    public List<Messages> findAllTopics() {
        String sql = "select TOPICS.TOPIC_ID,MESSAGES.* from FORUMADMIN.TOPICS "
                + "inner join FORUMADMIN.MESSAGES "
                + "on TOPICS.MESSAGE_ID = MESSAGES.MESSAGE_ID";
        PreparedStatement ptmt = null;
        List<Messages> list = new ArrayList<Messages>();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Messages messages = new Messages();
                messages.setRecordId(rs.getInt("TOPIC_ID"));
                messages.setUserLogin(rs.getString("USER_LOGIN"));
                messages.setMessageId(rs.getInt("MESSAGE_ID"));
                messages.setMessageText(rs.getString("MESSAGE_TEXT"));
                messages.setMessageDate(rs.getTimestamp("MESSAGE_DATE"));
                list.add(messages);
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

    @Override
    public void addTopic(Topics topics) {
        String queryString = "INSERT INTO MESSAGES (MESSAGE_ID, USER_LOGIN, MESSAGE_TEXT, MESSAGE_DATE) "
                + "VALUES (?, ?, ?, ?)";
        String queryString1 = "INSERT INTO TOPICS (TOPIC_ID, MESSAGE_ID) VALUES (?, ?)";
        PreparedStatement ptmt = null;
        FactoryDAO factoryDAO = new FactoryDAO();
        int messageId = InsertHelper.getFreeId(factoryDAO.getMessagesDAO().getAllMessageId());
        int topicsId = InsertHelper.getFreeId(factoryDAO.getTopicsDAO().getAllTopicsId());
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, messageId);
            ptmt.setString(2, topics.getUserLogin());
            ptmt.setString(3, topics.getMessageText());
            ptmt.setTimestamp(4, new Timestamp(new Date().getTime()));
            ptmt.executeUpdate();
            ptmt = connection.prepareStatement(queryString1);
            ptmt.setInt(1, topicsId);
            ptmt.setInt(2, messageId);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
    }

    @Override
    public ArrayList<Integer> getAllTopicsId() {
        String sql = "select TOPICS.TOPIC_ID FROM FORUMADMIN.TOPICS";
        PreparedStatement ptmt = null;
        ArrayList<Integer> list = new ArrayList();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("TOPIC_ID"));
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

    @Override
    public void deleteRecord(int id) {
        PreparedStatement ptmt = null;
        String queryString = "DELETE FROM FORUMADMIN.TOPICS WHERE FORUMADMIN.TOPICS.TOPIC_ID=?";
        try {
            connection = ConnectionFactory.getConnection();

            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

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
    }

}
