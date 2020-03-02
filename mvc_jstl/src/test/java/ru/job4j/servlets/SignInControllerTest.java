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

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class SignInControllerTest {

    @Test
    public void whenRedirectOnValidatePage() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/WEB-INF/views/Login.jsp")).thenReturn(dispatcher);

        SignInController signInController = new SignInController();
        signInController.doGet(req, response);

        verify(req).getRequestDispatcher("/WEB-INF/views/Login.jsp");
        verify(dispatcher).forward(req, response);
    }

    @Test
    public void whenUserIsValidateAndInAccountUser() throws ServletException, IOException {
        Validate validate = new StubValidate();
        validate.add(new User(
                "1", "name", "root",
                "root", "root@mail.ru", "28.02.2020",
                "image", "user", "Belarus", "Minsk"
        ));
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getINSTANCE()).thenReturn(validate);

        User user = validate.isCredential("root", "root");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("root");
        when(req.getParameter("password")).thenReturn("root");

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        SignInController signInController = new SignInController();
        signInController.doPost(req, response);

        verify(session).setAttribute("login", user.getLogin());
        verify(session).setAttribute("roleUser", user.getRoleName());
        verify(session).setAttribute("findID", user.getId());
    }

    @Test
    public void whenUserIsNotValidateAndNotInAccountUser() throws ServletException, IOException {
        Validate validate = new StubValidate();
        validate.add(new User(
                "1", "name", "root",
                "root", "root@mail.ru", "28.02.2020",
                "image", "user", "Belarus", "Minsk"
        ));
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getINSTANCE()).thenReturn(validate);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn(" ");
        when(req.getParameter("password")).thenReturn(" ");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/views/Login.jsp")).thenReturn(dispatcher);

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        SignInController signInController = new SignInController();
        signInController.doPost(req, response);
    }
}