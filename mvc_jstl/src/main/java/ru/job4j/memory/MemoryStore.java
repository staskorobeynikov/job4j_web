package ru.job4j.memory;

import ru.job4j.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {

    private static final MemoryStore INSTANCE = new MemoryStore();

    private final Map<String, User> store = new ConcurrentHashMap<>();

    public MemoryStore() {
    }

    public static MemoryStore getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        store.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public User findById(User user) {
        return store.get(user.getId());
    }

    @Override
    public List<String> getCountries() {
        return null;
    }

    @Override
    public List<String> getCities(String country) {
        return null;
    }
}
