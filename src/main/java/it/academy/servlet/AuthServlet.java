package it.academy.servlet;


import it.academy.commands.Command;
import it.academy.commands.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.academy.utils.constants.DataUI.ATTR_COMMAND;
import static it.academy.utils.constants.DataUI.ATTR_MESSAGE;
import static it.academy.utils.constants.DataUI.AUTH_JSP;
import static it.academy.utils.constants.DataUI.ERROR_ACCESS;
import static it.academy.utils.constants.DataUI.ERROR_JSP;
import static it.academy.utils.constants.DataUI.URL_AUTH_SERVLET;

@WebServlet(urlPatterns = URL_AUTH_SERVLET)
public class AuthServlet extends HttpServlet {
    private final CommandFactory receiver = CommandFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter(ATTR_COMMAND);
        if (isNotAuthCommand(value)) {
            request.setAttribute(ATTR_MESSAGE, ERROR_ACCESS);
            request.getRequestDispatcher(ERROR_JSP).forward(request, response);
        } else {
            Command command = receiver.getCommand(value);
            String path = command.execute(request, response);
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    private boolean isNotAuthCommand(String value) {
        return !AUTH_JSP.contains(value);
    }
}
