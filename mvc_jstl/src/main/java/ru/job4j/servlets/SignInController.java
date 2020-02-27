package ru.job4j.servlets;

import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User findUser = ValidateService.getINSTANCE().isCredential(login, password);
        if (findUser != null) {
            String roleUser = findUser.getRoleName();
            HttpSession session = req.getSession();
            session.setAttribute("roleUser", roleUser);
            session.setAttribute("findID", findUser.getId());
            session.setAttribute("login", login);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            req.setAttribute("error", "User not found.");
            doGet(req, resp);
        }
    }
}
