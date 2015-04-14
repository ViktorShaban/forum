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
import ua.kpi.nc.entities.Questions;

/**
 *
 * @author Виктор
 */
public class GoToQuestionsAdmin implements Command {

    private static final String TOPIC_ID = "topicId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId;
        HttpSession session = request.getSession();
        if (session.getAttribute("topicId1") != null) {
            topicId = (int) session.getAttribute("topicId1");
            session.setAttribute("topicId1", null);
        } else {
            if (request.getParameter(TOPIC_ID) != null) {
                topicId = Integer.parseInt(request.getParameter(TOPIC_ID));
                session.setAttribute("topicId", topicId);
            } else {
                topicId = (int) session.getAttribute("topicId");
            }
        }
        FactoryDAO factoryDAO = new FactoryDAO();
        List<Questions> quastionsList = factoryDAO.getQuestionsDAO().findQuestionsByTopicId(topicId);
        request.setAttribute("quastionsList", quastionsList);
        return "/jsp/questionsAdmin.jspx";
    }

}
