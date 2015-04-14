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
import ua.kpi.nc.IDAO.QuestionsIDAO;
import ua.kpi.nc.connection.ConnectionFactory;
import ua.kpi.nc.entities.Answers;
import ua.kpi.nc.entities.Questions;

/**
 *
 * @author Виктор
 */
public class QuestionsDAO implements QuestionsIDAO {

    Connection connection = null;

    @Override
    public List<Questions> findQuestionsByTopicId(int topicId) {
        String sql = "select QUESTIONS.QUESTION_ID,MESSAGES.* from FORUMADMIN.QUESTIONS "
                + "inner join FORUMADMIN.MESSAGES "
                + "on QUESTIONS.MESSAGE_ID = MESSAGES.MESSAGE_ID WHERE QUESTIONS.TOPIC_ID = ? ";
        PreparedStatement ptmt = null;
        List<Questions> list = new ArrayList<Questions>();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setInt(1, topicId);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Questions questions = new Questions();
                questions.setQuestionId(rs.getInt("QUESTION_ID"));
                questions.setUserLogin(rs.getString("USER_LOGIN"));
                questions.setMessageId(rs.getInt("MESSAGE_ID"));
                questions.setMessageText(rs.getString("MESSAGE_TEXT"));
                questions.setMessageDate(rs.getTimestamp("MESSAGE_DATE"));
                questions.setTopicId(topicId);
                list.add(questions);
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
    public void addQuestion(Questions questions) {
        String queryString = "INSERT INTO MESSAGES (MESSAGE_ID, USER_LOGIN, MESSAGE_TEXT, MESSAGE_DATE) "
                + "VALUES (?, ?, ?, ?)";
        String queryString1 = "INSERT INTO QUESTIONS (QUESTION_ID, TOPIC_ID, MESSAGE_ID)  VALUES (?, ?, ?)";
        PreparedStatement ptmt = null;
        FactoryDAO factoryDAO = new FactoryDAO();
        int messageId = InsertHelper.getFreeId(factoryDAO.getMessagesDAO().getAllMessageId());
        int quastionId = InsertHelper.getFreeId(factoryDAO.getQuestionsDAO().getAllQuestionsId());
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, messageId);
            ptmt.setString(2, questions.getUserLogin());
            ptmt.setString(3, questions.getMessageText());
            ptmt.setTimestamp(4, new Timestamp(new Date().getTime()));
            ptmt.executeUpdate();
            ptmt = connection.prepareStatement(queryString1);
            ptmt.setInt(1, quastionId);
            ptmt.setInt(2, questions.getTopicId());
            ptmt.setInt(3, messageId);
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
    public ArrayList<Integer> getAllQuestionsId() {
        String sql = "select QUESTIONS.QUESTION_ID FROM FORUMADMIN.QUESTIONS";
        PreparedStatement ptmt = null;
        ArrayList<Integer> list = new ArrayList();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("QUESTION_ID"));
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
     public void deleteQuestion(int id) {
        PreparedStatement ptmt = null;
        String queryString = "DELETE FROM FORUMADMIN.QUESTIONS WHERE FORUMADMIN.QUESTIONS.QUESTION_ID =?";
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
    public static void main(String[] args) {
        FactoryDAO aO = new FactoryDAO();
        ArrayList<Integer> a1 = aO.getQuestionsDAO().getAllQuestionsId();
        System.out.println("");
    }
}
