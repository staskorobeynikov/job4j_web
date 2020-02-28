package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AuthFilterTest {

    @Test
    public void whenRequestURIContainsSignIn() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getRequestURI()).thenReturn("/signin");

        new AuthFilter().doFilter(req, response, chain);
        verify(chain).doFilter(req, response);
    }

    @Test
    public void whenRequestURINotContainsSignInAndLoginIsNull() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);
        when(req.getRequestURI()).thenReturn("/edit");

        new AuthFilter().doFilter(req, response, chain);
        verify(req).getSession();
        verify(session).getAttribute("login");
        verify(response).sendRedirect(String.format("%s/signin", req.getContextPath()));
    }

    @Test
    public void whenRequestURINotContainsSignInAndLoginIsNotNull() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("root");
        when(req.getRequestURI()).thenReturn("/edit");

        new AuthFilter().doFilter(req, response, chain);
        verify(req).getSession();
        verify(session).getAttribute("login");
        verify(chain).doFilter(req, response);
    }

}