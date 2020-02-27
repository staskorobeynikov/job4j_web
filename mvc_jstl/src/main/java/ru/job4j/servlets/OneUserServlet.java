package ru.job4j.servlets;

import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User oneUser = this.findUser((String) session.getAttribute("findID"));
        req.setAttribute("oneUser", oneUser);
        req.getRequestDispatcher("/WEB-INF/views/OneUserView.jsp").forward(req, resp);
    }

    private User findUser(String id) {
        User result = null;
        for (User user : ValidateService.getINSTANCE().findAll()) {
            if (user.getId().equals(id)) {
                result = user;
                break;
            }
        }
        return result;
    }
}
