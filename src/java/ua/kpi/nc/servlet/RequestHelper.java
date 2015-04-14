/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.servlet;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import ua.kpi.nc.commands.*;

/**
 *
 * @author Виктор
 */
public class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<String, Command>();

    private RequestHelper() {
        //заполнение таблицы командами
        commands.put("login", new LoginCommand());
        commands.put("goToRegistrationPage", new GoToRegistationCommand());
        commands.put("registrate", new RegistrationCommand());
        commands.put("chooseLink", new GoToQuestionsCommand());
        commands.put("goToAnswers", new GoToAnswersCommand());
        commands.put("addAnswer", new AddAnswerCommand());
        commands.put("addQuestion", new AddQuestionCommand());
        commands.put("enterWithOutReg", new EnterFreeCommand());
        commands.put("goBackToQuestions", new GoBackToQuestions());
        commands.put("goBackToTopics", new GoBackToTopics());
        commands.put("logOut", new LogOutCommand());
        commands.put("addTopic", new AddTopicCommand());
        commands.put("viewAllUsers", new ViewAllUsersCommand());
        commands.put("setAdmin", new SetAdminCommand());
        commands.put("setUser", new SetUserCommand());
        commands.put("deleteTopic", new DeleteTopicCommand());
        commands.put("quastionsAdminLink", new GoToQuestionsAdmin());
        commands.put("goBackToTopicsAdmin", new GoBackToTopicsAdminCommand());
        commands.put("deleteAdminQuestion", new DeleteQuastionCommand());
        commands.put("answersAdminLink", new GoToAnswersAdminCommand());
        commands.put("deleteAnswerAdmin", new DeleteAnswerAdminCommand());
        commands.put("goBackToQuestionsAdmin", new GoBackToQuastionsAdminCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        //извлечение команды из запроса
        String action = request.getParameter("command");
        System.out.println("action " + action);
        //получение объекта, соответствующего команде
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }

}
