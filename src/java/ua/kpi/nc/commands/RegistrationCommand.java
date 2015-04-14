/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.nc.DAO.FactoryDAO;
import ua.kpi.nc.entities.Users;

/**
 *
 * @author Виктор
 */
public class RegistrationCommand implements Command {

    private static final String USER_NAME = "userName";
    private static final String LOGIN_REG = "loginReg";
    private static final String PASSWORD_REG = "passwordReg";
    private static final String CONFIRM_PASSWORD = "confirmPassword";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter(USER_NAME);
        String userLogin = request.getParameter(LOGIN_REG);
        String password = request.getParameter(PASSWORD_REG);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        FactoryDAO factoryDAO = new FactoryDAO();
        Users users = new Users(userLogin, password);
        if (factoryDAO.getUsersDAO().checkLogin(users, 2)) {
            request.setAttribute("userName", userName);
            request.setAttribute("userLogin", userLogin);
            request.setAttribute("errorLog", "Try another login");
            return "/jsp/registrate.jspx";
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("userName", userName);
            request.setAttribute("userLogin", userLogin);
            request.setAttribute("errorLog", "Passwords not equals");
            return "/jsp/registrate.jspx";
        }
        users.setUserName(userName);
        factoryDAO.getUsersDAO().addUser(users);
        return "/jsp/login.jspx";
    }

}
