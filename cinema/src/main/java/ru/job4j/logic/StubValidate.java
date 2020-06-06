package ru.job4j.logic;

import ru.job4j.memory.MemoryStore;
import ru.job4j.memory.Store;
import ru.job4j.model.Account;
import ru.job4j.model.Place;

import java.util.List;
import java.util.Set;

public class StubValidate implements Validate {

    private final Store store = MemoryStore.getInstance();

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
