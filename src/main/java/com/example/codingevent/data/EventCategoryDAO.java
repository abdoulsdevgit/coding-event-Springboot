package com.example.codingevent.data;

import com.example.codingevent.Models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryDAO extends CrudRepository<EventCategory, Long> {
}
