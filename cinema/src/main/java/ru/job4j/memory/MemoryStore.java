package ru.job4j.memory;

import ru.job4j.model.Account;
import ru.job4j.model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {

    private static final MemoryStore INSTANCE = new MemoryStore();

    private final Map<Place, Account> store = new ConcurrentHashMap<>();

    public MemoryStore() {
        store.put(new Place("place11", 1, 1), new Account("Stas", "+375296666666"));
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Place> getPlacesHall() {
        return new ArrayList<>(store.keySet());
    }

    @Override
    public Set<Place> getOccupiedPlaces() {
        return store.keySet();
    }

    @Override
    public boolean addVisitorPlace(Account account, Place place) {
        Account addNewAccount = store.putIfAbsent(place, account);
        return addNewAccount != null;
    }
}
