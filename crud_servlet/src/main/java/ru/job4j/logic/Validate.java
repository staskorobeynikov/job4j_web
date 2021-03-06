package ru.job4j.logic;

import ru.job4j.model.User;

import java.util.List;

public interface Validate {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(User user);
}
