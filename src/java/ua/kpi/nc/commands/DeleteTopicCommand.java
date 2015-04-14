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

/**
 *
 * @author Виктор
 */
public class DeleteTopicCommand implements Command {

    private final String CHOOSE_TOPIC = "chooseTopicRadioButton";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter(CHOOSE_TOPIC));
        FactoryDAO factoryDAO = new FactoryDAO();
        factoryDAO.getTopicsDAO().deleteRecord(topicId);
        return new GoToAdminPageCommand().execute(request, response);
    }

}
