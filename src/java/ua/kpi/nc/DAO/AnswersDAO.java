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
import ua.kpi.nc.IDAO.AnswersIDAO;
import ua.kpi.nc.connection.ConnectionFactory;
import ua.kpi.nc.entities.Answers;


/**
 *
 * @author Виктор
 */
public class AnswersDAO implements AnswersIDAO {

    Connection connection = null;

    @Override
    public List<Answers> findAnswersByQuestionId(int questionId) {
        String sql = "select ANSWERS.ANSWER_ID,MESSAGES.* from FORUMADMIN.ANSWERS "
                + "inner join FORUMADMIN.MESSAGES "
                + "on ANSWERS.MESSAGE_ID = MESSAGES.MESSAGE_ID WHERE ANSWERS.QUESTION_ID = ? ";
        PreparedStatement ptmt = null;
        List<Answers> list = new ArrayList<Answers>();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setInt(1, questionId);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Answers answers = new Answers();
                answers.setAnswerId(rs.getInt("ANSWER_ID"));
                answers.setQuestionId(questionId);
                answers.setUserLogin(rs.getString("USER_LOGIN"));
                answers.setMessageId(rs.getInt("MESSAGE_ID"));
                answers.setMessageText(rs.getString("MESSAGE_TEXT"));
                answers.setMessageDate(rs.getTimestamp("MESSAGE_DATE"));
                list.add(answers);
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
    public void addAnswer(Answers answers) {
        String queryString = "INSERT INTO MESSAGES (MESSAGE_ID, USER_LOGIN, MESSAGE_TEXT, MESSAGE_DATE) "
                + "VALUES (?, ?, ?, ?)";
        String queryString1 = "INSERT INTO ANSWERS (ANSWER_ID, QUESTION_ID, MESSAGE_ID) VALUES (?, ?, ?)";
        PreparedStatement ptmt = null;
        FactoryDAO factoryDAO = new FactoryDAO();
        int messageId = InsertHelper.getFreeId(factoryDAO.getMessagesDAO().getAllMessageId());
        int answerId = InsertHelper.getFreeId(factoryDAO.getAnswersDAO().getAllAnswersId());
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, messageId);
            ptmt.setString(2, answers.getUserLogin());
            ptmt.setString(3, answers.getMessageText());
            ptmt.setTimestamp(4, new Timestamp(new Date().getTime()));
            ptmt.executeUpdate();
            ptmt = connection.prepareStatement(queryString1);
            ptmt.setInt(1, answerId);
            ptmt.setInt(2, answers.getQuestionId());
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
    public ArrayList<Integer> getAllAnswersId() {
        String sql = "select ANSWERS.ANSWER_ID FROM FORUMADMIN.ANSWERS";
        PreparedStatement ptmt = null;
        ArrayList<Integer> list = new ArrayList();
        try {
            connection = ConnectionFactory.getConnection();
            ptmt = connection.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("ANSWER_ID"));
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
    public void deleteAnswer(int id) {
        PreparedStatement ptmt = null;
        String queryString = "DELETE FROM FORUMADMIN.ANSWERS WHERE FORUMADMIN.ANSWERS.ANSWER_ID =?";
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
