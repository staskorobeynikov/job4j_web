package ru.job4j.servlets;

import ru.job4j.logic.Config;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class UserServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    private final HashMap<String, Action> actions = new HashMap<>();

    private final Config config = Config.getInstance();

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

        User user = new User(
                req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("createDate"),
                req.getParameter("image")
        );

        if (action.equals("delete")) {
            File folder = new File(config.getProperty("photoID"));
            User userDelete = validate.findById(user);
            Path path = Paths.get(folder + File.separator + userDelete.getImage());
            Files.delete(path);
        }

        actions.get(action).execute(validate, user);

        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
