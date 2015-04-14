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
public class DeleteQuastionCommand implements Command {
private final String CHOOSE_QUASTION = "chooseQuestionRadioButton";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter(CHOOSE_QUASTION));
        FactoryDAO factoryDAO = new FactoryDAO();
        factoryDAO.getQuestionsDAO().deleteQuestion(questionId);
        return new GoToQuestionsAdmin().execute(request, response);
    }

}
