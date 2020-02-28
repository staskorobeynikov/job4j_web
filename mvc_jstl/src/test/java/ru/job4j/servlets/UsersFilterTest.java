package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UsersFilterTest {

    @Test
    public void whenRequestURIContainsEdit() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getRequestURI()).thenReturn("/edit");

        new UsersFilter().doFilter(req, response, chain);
        verify(chain).doFilter(req, response);
    }

    @Test
    public void whenRequestURIContainsListAndRoleIsAdmin() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("roleUser")).thenReturn("admin");
        when(req.getRequestURI()).thenReturn("/list");

        new UsersFilter().doFilter(req, response, chain);
        verify(req).getSession();
        verify(session).getAttribute("roleUser");
        verify(chain).doFilter(req, response);
    }

    @Test
    public void whenRequestURIContainsCreateAndRoleAdmin() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("roleUser")).thenReturn("admin");
        when(req.getRequestURI()).thenReturn("/create");

        new UsersFilter().doFilter(req, response, chain);
        verify(req).getSession();
        verify(session).getAttribute("roleUser");
        verify(chain).doFilter(req, response);
    }

    @Test
    public void whenRequestURIContainsListAndRoleIsUser() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("roleUser")).thenReturn("user");
        when(req.getRequestURI()).thenReturn("/list");

        new UsersFilter().doFilter(req, response, chain);
        verify(req).getSession();
        verify(session).getAttribute("roleUser");
        verify(response).sendRedirect(String.format("%s/oneuser", req.getContextPath()));
    }
}