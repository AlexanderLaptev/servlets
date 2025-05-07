package ru.vsu.cs.event;

import java.util.List;

public interface EventRepository {
    List<Event> findAll();

    Event findById(int id);

    void save(Event event);

    void delete(int id);
}
