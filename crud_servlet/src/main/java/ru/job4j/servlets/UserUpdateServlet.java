package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        User find = null;

        String id = req.getParameter("id");

        for (User user : validate.findAll()) {
            if (user.getId().equals(id)) {
                find = user;
            }
        }

        if (find != null) {
            writer.append("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "    <meta charset=\"UTF-8\">"
                    + "    <title>UserUpdate</title>"
                    + "</head>"
                    + "<body>"
                    + "<form action='/store/users?action=update' method='post'>"
                    + "ID : <input type='text' name='id' value=" + find.getId() + ">"
                    + "Name : <input type='text' name='name' value=" + find.getName() + ">"
                    + "Login : <input type='text' name='login' value=" + find.getLogin() + ">"
                    + "Email : <input type='text' name='email' value=" + find.getEmail() + ">"
                    + "CreateDate : <input type='text' name='createDate' value=" + find.getCreateDate() + ">"
                    + "<input type='submit'>"
                    + "</form>"
                    + "</body>"
                    + "</html>"
            );
        } else {
            writer.append("User with that ID not found");
        }

        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
