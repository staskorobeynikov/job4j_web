package ru.job4j.logic;

import ru.job4j.memory.DBStore;
import ru.job4j.memory.Store;
import ru.job4j.model.Account;
import ru.job4j.model.Place;

import java.util.List;
import java.util.Set;

public class ValidateService implements Validate {

    private static final Validate INSTANCE = new ValidateService();

    private final Store store = DBStore.getInstance();

    public ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Place> getPlacesHall() {
        return store.getPlacesHall();
    }

    @Override
    public Set<Place> getOccupiedPlaces() {
        return store.getOccupiedPlaces();
    }

    @Override
    public boolean addVisitorPlace(Account account, Place place) {
        return store.addVisitorPlace(account, place);
    }
}
