package ru.job4j.servlets;

import ru.job4j.logic.Validate;
import ru.job4j.model.User;

public class DeleteAction implements Action {

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void execute(Validate validate, User user) {
        validate.delete(user);
    }
}
