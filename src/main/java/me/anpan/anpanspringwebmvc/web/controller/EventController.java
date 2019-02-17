package me.anpan.anpanspringwebmvc.web.controller;

import me.anpan.anpanspringwebmvc.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/events")
    public String event(Model model){
        model.addAttribute("evnets",eventService.getEvents());
        return "events";
    }

}
