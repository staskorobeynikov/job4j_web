package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.model.User;

public interface Action {

    String getName();

    void execute(Validate validate, User user);
}
