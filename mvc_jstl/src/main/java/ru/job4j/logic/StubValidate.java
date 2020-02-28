package ru.job4j.logic;

import ru.job4j.memory.MemoryStore;
import ru.job4j.memory.Store;
import ru.job4j.model.User;

import java.util.List;

public class StubValidate implements Validate {

    private final Store store = MemoryStore.getINSTANCE();

    @Override
    public boolean add(User user) {
        store.add(user);
        return false;
    }

    @Override
    public boolean update(User user) {
        store.update(user);
        return false;
    }

    @Override
    public boolean delete(User user) {
        store.delete(user);
        return false;
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
