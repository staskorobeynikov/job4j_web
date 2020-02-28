package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ExitServletTest {

    @Test
    public void whenExitAccountAndRequestDefaultPage() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/views/Login.jsp")).thenReturn(dispatcher);

        ExitServlet exit = new ExitServlet();
        exit.doPost(req, response);

        verify(req).getRequestDispatcher("/WEB-INF/views/Login.jsp");
        verify(dispatcher).forward(req, response);
    }
}