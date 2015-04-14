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
import ua.kpi.nc.DAO.FactoryDAO;
import ua.kpi.nc.entities.Messages;

/**
 *
 * @author Виктор
 */
public class GoBackToTopicsAdminCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAO();
        List<Messages> topicsList = factoryDAO.getTopicsDAO().findAllTopics();
        request.setAttribute("topicsList", topicsList);
        return "/jsp/mainAdmin.jspx";
    }

}
