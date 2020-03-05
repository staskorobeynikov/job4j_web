package ru.job4j.memory;

import ru.job4j.model.Place;

import java.util.List;
import java.util.Set;

public interface Store {

    List<Place> getPlacesHall();

    Set<Place> getOccupiedPlaces();

    void addVisitorPlace(String fio, String phone, int id);
}
