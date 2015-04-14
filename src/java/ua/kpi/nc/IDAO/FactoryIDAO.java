/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.IDAO;

/**
 *
 * @author Виктор
 */
public interface FactoryIDAO {

    public AnswersIDAO getAnswersDAO();

    public MessagesIDAO getMessagesDAO();

    public QuestionsIDAO getQuestionsDAO();

    public TopicsIDAO getTopicsDAO();

    public UsersIDAO getUsersDAO();
}
