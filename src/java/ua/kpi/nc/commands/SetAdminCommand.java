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
public class SetAdminCommand implements Command {

    private static final String USER_RADIO_BUTTON = "chooseUserRadioButton";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter(USER_RADIO_BUTTON);
        if(userLogin == null) {
            request.setAttribute("error", "Chosse User");
            return new GoToAdminPageCommand().execute(request, response);
        }
        FactoryDAO factoryDAO = new FactoryDAO();
        Users users = new Users(userLogin, 1);
        factoryDAO.getUsersDAO().updateUserStatus(users);
        return new ViewAllUsersCommand().execute(request, response);
    }

}
