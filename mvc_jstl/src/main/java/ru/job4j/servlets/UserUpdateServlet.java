package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        User find = this.findUser(id);
        req.setAttribute("find", find);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        Action update = new UpdateAction();

        User user = new User(
                req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("createDate")
        );

        update.execute(validate, user);

        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }

    private User findUser(String id) {
        User result = null;
        for (User user : validate.findAll()) {
            if (user.getId().equals(id)) {
                result = user;
                break;
            }
        }
        return result;
    }
}