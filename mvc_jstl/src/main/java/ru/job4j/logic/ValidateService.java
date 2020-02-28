package ru.job4j.logic;

import ru.job4j.memory.DBStore;
import ru.job4j.memory.Store;
import ru.job4j.model.User;

import java.util.List;

public class ValidateService implements Validate {

    private static final Validate INSTANCE = new ValidateService();

    private final Store store = DBStore.getINSTANCE();

    public ValidateService() {
    }

    public static Validate getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        if (store.findById(user) == null) {
            store.add(user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        if (store.findById(user) != null) {
            store.update(user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        if (store.findById(user) != null) {
            store.delete(user);
            result = true;
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return store.findAll();
    }

    @Override
    public User findById(User user) {
        return store.findById(user);
    }

    @Override
    public User isCredential(String login, String password) {
        User result = null;
        for (User user : this.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
                break;
            }
        }
        return result;
    }
}
