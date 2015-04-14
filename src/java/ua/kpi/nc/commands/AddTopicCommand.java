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
import javax.servlet.http.HttpSession;
import ua.kpi.nc.DAO.FactoryDAO;
import ua.kpi.nc.entities.Topics;

/**
 *
 * @author Виктор
 */
public class AddTopicCommand implements Command {

    private static final String TEXT_AR_QUESTION = "textArQuestion";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quastionText = request.getParameter(TEXT_AR_QUESTION);
        HttpSession session = request.getSession();
        Topics topics = new Topics((String) session.getAttribute("userLogin"), quastionText);
        FactoryDAO factoryDAO = new FactoryDAO();
        factoryDAO.getTopicsDAO().addTopic(topics);
        return new GoToAdminPageCommand().execute(request, response);
    }

}
