package ru.job4j.servlets;

import com.google.gson.Gson;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginValidServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String response = "";
        for (String log : validate.getLogins()) {
            if (log.equals(login)) {
                response = "Enter another login";
                break;
            }
        }
        String json = new Gson().toJson(response);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }
}
