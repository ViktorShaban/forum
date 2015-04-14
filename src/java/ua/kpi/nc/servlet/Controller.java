/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.nc.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.nc.commands.Command;

/**
 *
 * @author Виктор
 */
public class Controller extends HttpServlet {

    RequestHelper requestHelper = RequestHelper.getInstance();

    public Controller() {
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        try {
            
            //определение команды, пришедшей из JSP
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
            //метод возвращает страницу ответа
        } catch (ServletException e) {
//            e.printStackTrace();
//            request.setAttribute("errorMessage",
//                    MessageManager.getInstance().getProperty(
//                            MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
//            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
//            e.printStackTrace();
//            request.setAttribute("errorMessage",
//                    MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
//            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        //вызов страницы ответа на запрос
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
