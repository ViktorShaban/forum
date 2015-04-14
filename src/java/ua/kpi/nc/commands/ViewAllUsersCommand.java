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
import ua.kpi.nc.entities.Users;

/**
 *
 * @author Виктор
 */
public class ViewAllUsersCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
        FactoryDAO factoryDAO = new FactoryDAO();
        List<Users> usersList =  factoryDAO.getUsersDAO().findAllUsers();
        session.setAttribute("usersList", usersList);
        return "/jsp/mainAdmin.jspx";
    }
    
}
