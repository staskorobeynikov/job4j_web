package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.model.User;

public class UpdateAction implements Action {

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public void execute(Validate validate, User user) {
        validate.update(user);
    }
}
