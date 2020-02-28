package ru.job4j.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.logic.StubValidate;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class OneUserServletTest {

    @Test
    public void whenRedirectOnPageOneUserView() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        Validate validate = new StubValidate();
        validate.add(new User("1", "name", "root", "root", "root@mail.ru", "28.02.2020", "image", "user"));
        PowerMockito.mockStatic(ValidateService.class);

        when(req.getSession()).thenReturn(session);
        when(ValidateService.getINSTANCE()).thenReturn(validate);
        when(session.getAttribute("findID")).thenReturn(validate.findAll().get(0).getId());
        when(req.getRequestDispatcher("/WEB-INF/views/OneUserView.jsp")).thenReturn(dispatcher);

        new OneUserServlet().doGet(req, response);

        verify(req).getRequestDispatcher("/WEB-INF/views/OneUserView.jsp");
        verify(dispatcher).forward(req, response);
    }
}