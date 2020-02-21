package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>UserCreate</title>"
                + "</head>"
                + "<body>"
                + "<form action='/store/users?action=add' method=post>"
                + "ID : <input type='text' name='id'/>"
                + "Name : <input type='text' name='name'/>"
                + "Login : <input type='text' name='login'/>"
                + "Email : <input type='text' name='email'/>"
                + "CreateDate : <input type='text' name='createDate'/>"
                + "<input type='submit'>"
                + "</form>"
                + "</body>"
                + "</html>"
        );

        writer.flush();
    }
}
