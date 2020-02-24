package ru.job4j.memory;

import ru.job4j.model.User;

import java.util.List;

public interface Store {

    void add(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(User user);
}
