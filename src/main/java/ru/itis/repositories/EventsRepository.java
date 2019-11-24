package ru.itis.repositories;

import ru.itis.entities.Event;

import java.util.Optional;

public interface EventsRepository extends CrudRepository<Event, Long> {
    Optional<Event> findOneByName(String name);
}
