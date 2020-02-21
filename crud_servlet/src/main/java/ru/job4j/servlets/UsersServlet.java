package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        StringBuilder sb = new StringBuilder("<table>");
        sb.append("<tr>"
                + "<th>ID</th>"
                + "<th>Name</th>"
                + "<th>Login</th>"
                + "<th>Email</th>"
                + "<th>CreateDate</th>"
                + "<th>Action</th>"
                + "</tr>"
        );
        for (User user : validate.findAll()) {
            sb.append("<tr>"
                    + "<td>" + user.getId() + "</td>"
                    + "<td>" + user.getName() + "</td>"
                    + "<td>" + user.getLogin() + "</td>"
                    + "<td>" + user.getEmail() + "</td>"
                    + "<td>" + user.getCreateDate() + "</td>"
                    + "<td>"
                    + "<form action='/store/users?action=delete&id=" + user.getId() + "' method='post'>"
                    + "<input type='submit' value='Delete'>"
                    + "</form>"
                    + "</br>"
                    + "<form action='/store/edit?id=" + user.getId() + "' method='post'>"
                    + "<input type='submit' value='Update'>"
                    + "</form>"
                    + "</td>"
                    + "</tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>StoreUsers</title>"
                + "<style type=\"text/css\">"
                + "   TABLE {"
                + "    border-collapse: collapse;"
                + "    border: 2px solid #000;"
                + "   }"
                + "   TD, TH {"
                + "    padding: 5px;"
                + "    border: 1px solid #000;"
                + "   }"
                + "  </style>"
                + "</head>"
                + "<body>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
    }
}
