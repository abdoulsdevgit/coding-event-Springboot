package com.example.codingevent.data;

import com.example.codingevent.Models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventData {

    private static final Map<Long, Event> events = new HashMap<>();

    public static Collection<Event> getAll() {
        return events.values();
    }

    public static Event getById(long id) {
        return events.get(id);
    }

    public static void add(Event event) {
        events.put(event.getId(), event);
    }

    public static Event remove(long id) {
        return events.remove(id);
    }

}
