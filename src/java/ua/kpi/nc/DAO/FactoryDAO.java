/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.DAO;

import ua.kpi.nc.IDAO.AnswersIDAO;
import ua.kpi.nc.IDAO.FactoryIDAO;
import ua.kpi.nc.IDAO.MessagesIDAO;
import ua.kpi.nc.IDAO.QuestionsIDAO;
import ua.kpi.nc.IDAO.TopicsIDAO;
import ua.kpi.nc.IDAO.UsersIDAO;
/**
 *
 * @author Виктор
 */
public class FactoryDAO implements FactoryIDAO {

    @Override
    public AnswersIDAO getAnswersDAO() {
        return new AnswersDAO();
    }

    @Override
    public MessagesIDAO getMessagesDAO() {
        return new MessagesDAO();
    }

    @Override
    public QuestionsIDAO getQuestionsDAO() {
        return new QuestionsDAO();
    }

    @Override
    public TopicsIDAO getTopicsDAO() {
        return new TopicsDAO();
    }

    @Override
    public UsersIDAO getUsersDAO() {
        return new UsersDAO();
    }

}
