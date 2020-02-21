package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class UserServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    private final HashMap<String, Action> actions = new HashMap<>();

    public UserServlet() {
        Action addAction = new AddAction();
        actions.put(addAction.getName(), addAction);
        Action updateAction = new UpdateAction();
        actions.put(updateAction.getName(), updateAction);
        Action deleteAction = new DeleteAction();
        actions.put(deleteAction.getName(), deleteAction);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(validate.findAll().toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String createDate = req.getParameter("createDate");

        User user = new User(id, name, login, email, createDate);

        actions.get(action).execute(validate, user);

        doGet(req, resp);
    }
}
