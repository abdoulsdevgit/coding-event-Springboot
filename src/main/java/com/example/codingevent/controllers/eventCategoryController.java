package com.example.codingevent.controllers;

import com.example.codingevent.Models.EventCategory;
import com.example.codingevent.data.EventCategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("events/categories")
public class eventCategoryController {

    @Autowired
    private EventCategoryDAO eventCategoryDAO;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", eventCategoryDAO.findAll());

        return "eventCategories/index";
    }

    @GetMapping("create")
    public String showCreateForm(Model model) {
        model.addAttribute("title", " Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String handleCreateForm(@ModelAttribute @Valid EventCategory eventCategory, Errors error) {

        if(error.hasErrors()) {
            return "eventCategories/create";
        }

        eventCategoryDAO.save(eventCategory);
        return "redirect:";
    }
}
