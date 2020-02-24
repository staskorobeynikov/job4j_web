package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.model.User;

public class AddAction implements Action {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void execute(Validate validate, User user) {
        validate.add(user);
    }
}
