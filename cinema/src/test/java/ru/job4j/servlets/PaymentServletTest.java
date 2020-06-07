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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class PaymentServletTest {

    @Test
    public void whenCallMethodDoPostResponseContainsJSONTypeDataReservationSuccessful() throws IOException {
        Validate validate = new StubValidate();

        StringWriter stringWriter = new StringWriter();
        stringWriter.write("");
        PrintWriter writer = new PrintWriter(stringWriter);

        PowerMockito.mockStatic(ValidateService.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(ValidateService.getInstance()).thenReturn(validate);
        when(response.getWriter()).thenReturn(writer);
        when(req.getParameter("id")).thenReturn("13");
        when(req.getParameter("name")).thenReturn("Sergey");
        when(req.getParameter("phone")).thenReturn("+375297776655");

        new PaymentServlet().doPost(req, response);

        String string = stringWriter.toString();
        writer.flush();

        String expected = "{\"answer\":\"Reservation successful.\"}";

        assertThat(string, is(expected));
    }

    @Test
    public void whenCallMethodDoPostResponseContainsJSONTypeDataPlaceIsOccupied() throws IOException {
        Validate validate = new StubValidate();

        StringWriter stringWriter = new StringWriter();
        stringWriter.write("");
        PrintWriter writer = new PrintWriter(stringWriter);

        PowerMockito.mockStatic(ValidateService.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(ValidateService.getInstance()).thenReturn(validate);
        when(response.getWriter()).thenReturn(writer);
        when(req.getParameter("id")).thenReturn("12");
        when(req.getParameter("name")).thenReturn("Sergey");
        when(req.getParameter("phone")).thenReturn("+375297776655");

        new PaymentServlet().doPost(req, response);

        String string = stringWriter.toString();
        writer.flush();

        String expected = "{\"answer\":\"Place is occupied. Please, try again.\"}";

        assertThat(string, is(expected));
    }
}