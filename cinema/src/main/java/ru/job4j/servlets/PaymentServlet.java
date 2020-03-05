package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentServlet extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String fio = req.getParameter("name");
        String phone = req.getParameter("phone");
        validate.addVisitorPlace(fio, phone, Integer.parseInt(id));
    }
}
