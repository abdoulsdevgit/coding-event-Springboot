package com.example.codingevent.controllers;

import com.example.codingevent.Models.Event;
import com.example.codingevent.Models.EventCategory;
import com.example.codingevent.Models.Tag;
import com.example.codingevent.Models.dto.EventTagDTO;
import com.example.codingevent.data.EventCategoryDAO;
import com.example.codingevent.data.EventDAO;
import com.example.codingevent.data.EventData;
import com.example.codingevent.data.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private EventCategoryDAO eventCategoryDAO;

    @Autowired
    private TagDAO tagDAO;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Long categoryId,  Model model) {
        model.addAttribute("title", "Home");

        if(categoryId == null) {
            model.addAttribute("events", eventDAO.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryDAO.findById(categoryId);

            System.out.println(categoryId);

            if(result.isPresent()) {
                EventCategory category = result.get();
                List<Event> events = category.getEvents();
                model.addAttribute("events", events);
            } else {

            }

        }

        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateForm(Model model) {
        model.addAttribute("title", "Create");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryDAO.findAll());

        return"events/create";
    }

    @PostMapping("create")
    public String handleCreateForm(@ModelAttribute @Valid Event event, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad Data");
            model.addAttribute("categories", eventCategoryDAO.findAll());

            return "events/create";
        }

        eventDAO.save(event);

        return "redirect:";
    }

    @GetMapping("delete")
    public String showDeleteForm(@RequestParam long id, Model model) {
        model.addAttribute("title", "DELETE");
        Optional<Event> eventResponse = eventDAO.findById(id);
        Event event = eventResponse.get();
        System.out.println(event);
        model.addAttribute("event", event);
        return "events/delete";
    }

    @PostMapping("delete")
    public String handleDelete(@RequestParam long id) {
        eventDAO.deleteById(id);
        return "redirect:";
    }

    @GetMapping("add-tag")
    public String addTagForm(@RequestParam Long eventId, Model model) {
        Optional<Event> result = eventDAO.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("event", event);
        model.addAttribute("tags", tagDAO.findAll());
        model.addAttribute("eventTag", new EventTagDTO());
        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String handleAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
                                   Errors errors, Model modle) {

        if(!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();

            if(!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventDAO.save(event);
            }

            return "redirect:detail?eventId=" + event.getId();
        }

        return "redirect:add-tag";
    }
}
