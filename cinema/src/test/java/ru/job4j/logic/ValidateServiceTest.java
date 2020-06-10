package ru.job4j.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.memory.DBStore;
import ru.job4j.memory.MemoryStore;
import ru.job4j.memory.Store;
import ru.job4j.model.Account;
import ru.job4j.model.Place;

import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBStore.class)
public class ValidateServiceTest {

    @Test
    public void whenTestMethodGetAllPlacesInHall() {
        Store store = new MemoryStore();
        PowerMockito.mockStatic(DBStore.class);

        when(DBStore.getInstance()).thenReturn(store);

        List<Place> placesHall = new ValidateService().getPlacesHall();

        assertThat(placesHall.size(), is(2));
        assertThat(placesHall.iterator().next().getName(), is("Ряд 1, место 2"));
        assertThat(placesHall.get(1).getPlace(), is(1));
    }

    @Test
    public void whenTestMethodGetOccupiedPlacesInHall() {
        Store store = new MemoryStore();
        PowerMockito.mockStatic(DBStore.class);

        when(DBStore.getInstance()).thenReturn(store);

        Set<Place> occupiedPlaces = new ValidateService().getOccupiedPlaces();

        assertThat(occupiedPlaces.size(), is(1));
        assertThat(occupiedPlaces.iterator().next().toString(), is("Place: name=Ряд 1, место 1, row=1, place=1"));
    }

    @Test
    public void whenTestMethodAddNewVisitor() {
        Store store = new MemoryStore();
        PowerMockito.mockStatic(DBStore.class);
        Account account = new Account("Sergey", "+375297776655");
        Place place = new Place("Ряд 1, место 3", 1, 3);

        when(DBStore.getInstance()).thenReturn(store);

        new ValidateService().addVisitorPlace(account, place);

        List<Place> placesHall = new ValidateService().getPlacesHall();

        assertThat(placesHall.size(), is(3));
        assertThat(account.toString(), is("Account: fio=Sergey, phone=+375297776655"));
        assertThat(account.getPhone(), is("+375297776655"));
    }
}