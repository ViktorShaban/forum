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
import ua.kpi.nc.entities.Answers;

/**
 *
 * @author Виктор
 */
public class GoToAnswersCommand implements Command {

    private static final String QUESTION_ID = "questionId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int questionId;
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == null) {
            request.setAttribute("isVisible", "none");
        }
        if (session.getAttribute("questionId1") != null) {
            questionId = (int) session.getAttribute("questionId1");
            session.setAttribute("questionId1", null);
        } else {
            questionId = Integer.parseInt(request.getParameter(QUESTION_ID));
            session.setAttribute("questionId", questionId);
        }
        FactoryDAO factoryDAO = new FactoryDAO();
        List<Answers> answersList = factoryDAO.getAnswersDAO().findAnswersByQuestionId(questionId);
        request.setAttribute("answersList", answersList);
        return "/jsp/answers.jspx";
    }

}
