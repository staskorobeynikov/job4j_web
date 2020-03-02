package ru.job4j.servlets;

import com.google.gson.Gson;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CitiesServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String country = req.getParameter("town");
        List<String> list = validate.getCities(country);
        String json = new Gson().toJson(list);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }
}
