package com.example.codingevent.data;

import com.example.codingevent.Models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDAO extends CrudRepository<Event, Long> {
    // CrudRepository<ModelName, PrimaryKey dataType> it has all the methods to do crud Ops
}
