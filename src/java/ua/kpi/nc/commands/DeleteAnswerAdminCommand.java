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
public class DeleteAnswerAdminCommand implements Command {

    private static final String CHOOSE_ANSWER  = "chooseAnswerAdminRadioButton";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int answerId = Integer.parseInt(request.getParameter(CHOOSE_ANSWER));
        FactoryDAO factoryDAO = new FactoryDAO();
        factoryDAO.getAnswersDAO().deleteAnswer(answerId);
        return new GoToAnswersAdminCommand().execute(request, response);
    }

}
