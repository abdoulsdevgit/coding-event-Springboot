package com.example.codingevent.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "eventCategory") // this is the connecting object in our map.
    private final List<Event> events = new ArrayList<>();

    public EventCategory() {

    }


    public EventCategory(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getName(){return name; }

    public void setName(String name) {
        this.name = name;
    }
}
