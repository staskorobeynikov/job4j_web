package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (request.getRequestURI().contains("/list") || request.getRequestURI().contains("/create")) {
            if (session.getAttribute("roleUser").equals("user")) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/oneuser", request.getContextPath()));
                return;
            }
        }
        chain.doFilter(req, resp);
    }
}
