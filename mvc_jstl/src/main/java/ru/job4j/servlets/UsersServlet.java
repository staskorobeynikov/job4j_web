package ru.job4j.servlets;

import ru.job4j.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("users", ValidateService.getINSTANCE().findAll());
        req.getRequestDispatcher("/WEB-INF/views/Users.jsp").forward(req, resp);
    }
}
