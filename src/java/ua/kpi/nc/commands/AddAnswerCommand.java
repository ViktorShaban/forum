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
import ua.kpi.nc.entities.Answers;

/**
 *
 * @author Виктор
 */
public class AddAnswerCommand implements Command {

    private static final String TEXT_AR = "textAr";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String answerText = request.getParameter(TEXT_AR);
        System.out.println("answerText  "+answerText);
        HttpSession session = request.getSession();
        int questionId =  (int) session.getAttribute("questionId");
        session.setAttribute("questionId1", questionId);
        Answers answers = new Answers(questionId, (String) session.getAttribute("userLogin"), answerText);
        FactoryDAO factoryDAO = new FactoryDAO();
        factoryDAO.getAnswersDAO().addAnswer(answers);
        return new GoToAnswersCommand().execute(request, response);

    }

}
