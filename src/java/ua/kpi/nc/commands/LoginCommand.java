/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.commands;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.nc.DAO.FactoryDAO;
import ua.kpi.nc.entities.Messages;
import ua.kpi.nc.entities.Users;

/**
 *
 * @author Виктор
 */
public class LoginCommand implements Command {

    private static final String LOGIN_ST = "login";
    private static final String PASSWORD_ST = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_ST);
        String password = request.getParameter(PASSWORD_ST);
        HttpSession session = request.getSession();
        session.invalidate();
        session = request.getSession();
        FactoryDAO factoryDAO = new FactoryDAO();
        Users users = new Users(login, password);
        if (factoryDAO.getUsersDAO().checkLogin(users, 2)) {
            if (factoryDAO.getUsersDAO().checkLogin(users, 1)) {
                users = factoryDAO.getUsersDAO().findUserInfoByLogin(users);
                session.setAttribute("userName", users.getUserName());
                session.setAttribute("userLogin", users.getUserLogin());
                if (users.getUserIsadmin() == 1) {
                    return new GoToAdminPageCommand().execute(request, response);
                }
                List<Messages> topicsList = factoryDAO.getTopicsDAO().findAllTopics();
                request.setAttribute("topicsList", topicsList);
                return "/jsp/main.jspx";
            }
        }
        request.setAttribute("notFound", "Wrong Password of Login");
        return "/jsp/login.jspx";
    }

}
