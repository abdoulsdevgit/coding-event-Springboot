package com.example.codingevent.controllers;

import com.example.codingevent.Models.Tag;
import com.example.codingevent.data.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagDAO tagDAO;

    @GetMapping
    public String displayTags(Model model) {
        model.addAttribute("title", "Tags");
        model.addAttribute("tags", tagDAO.findAll());
        return"tags/index";
    }

    @GetMapping("create")
    public String showCreateTagForm(Model model) {
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String handleCreateForm(@ModelAttribute @Valid Tag tag, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Tag");
            return "tags/create";
        }

        tagDAO.save(tag);
        return "redirect:";
    }


}
