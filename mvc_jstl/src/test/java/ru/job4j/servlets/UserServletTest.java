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

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletTest {

    @Test
    public void whenMethodDoGetAndRedirectOnPageUsers() throws IOException, ServletException {
        Validate validate = new StubValidate();
        validate.add(new User(
                "1", "name", "root",
                "root", "root@mail.ru", "28.02.2020",
                "image", "user", "Belarus", "Minsk"
        ));
        PowerMockito.mockStatic(ValidateService.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(ValidateService.getINSTANCE()).thenReturn(validate);
        when(req.getRequestDispatcher("/WEB-INF/views/Users.jsp")).thenReturn(dispatcher);

        UserServlet userServlet = new UserServlet();
        userServlet.doGet(req, response);
        verify(req).getRequestDispatcher("/WEB-INF/views/Users.jsp");
        verify(dispatcher).forward(req, response);
    }

    @Test
    public void whenMethodDoPostActionIsAddOneUser() throws IOException {
        Validate validate = new StubValidate();
        PowerMockito.mockStatic(ValidateService.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(ValidateService.getINSTANCE()).thenReturn(validate);
        when(req.getParameter("action")).thenReturn("add");
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("name2");
        when(req.getParameter("login")).thenReturn("root1");
        when(req.getParameter("password")).thenReturn("root1");
        when(req.getParameter("email")).thenReturn("root1@mail.ru");
        when(req.getParameter("createDate")).thenReturn("29.02.2020");
        when(req.getParameter("image")).thenReturn("image1");
        when(req.getParameter("rolename")).thenReturn("user");

        new UserServlet().doPost(req, response);
        User test = validate.findAll().get(0);

        verify(response).sendRedirect(String.format("%s/list", req.getContextPath()));
        assertThat(test.getName(), is("name2"));
        assertThat(test.getLogin(), is("root1"));
        assertThat(test.getPassword(), is("root1"));
        assertThat(test.getEmail(), is("root1@mail.ru"));
        assertThat(test.getCreateDate(), is("29.02.2020"));
        assertThat(test.getImage(), is("image1"));
        assertThat(test.getRoleName(), is("user"));
    }


    @Test
    public void whenMethodDoPostActionIsDeleteFirstUser() throws IOException {
        Validate validate = new StubValidate();
        validate.add(new User(
                "1", "name", "root",
                "root", "root@mail.ru", "28.02.2020",
                "image", "user", "Belarus", "Minsk"
        ));
        validate.add(new User(
                "2", "name2", "root1",
                "root1", "root1@mail.ru", "29.02.2020",
                "img1", "user", "Belarus", "Minsk"
        ));

        PowerMockito.mockStatic(ValidateService.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(ValidateService.getINSTANCE()).thenReturn(validate);
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getParameter("id")).thenReturn("1");

        new UserServlet().doPost(req, response);
        User test = validate.findAll().get(0);

        verify(response).sendRedirect(String.format("%s/list", req.getContextPath()));
        assertThat(test.getName(), is("name2"));
        assertThat(test.getLogin(), is("root1"));
        assertThat(test.getPassword(), is("root1"));
        assertThat(test.getEmail(), is("root1@mail.ru"));
        assertThat(test.getCreateDate(), is("29.02.2020"));
        assertThat(test.getImage(), is("img1"));
        assertThat(test.getRoleName(), is("user"));
    }
}