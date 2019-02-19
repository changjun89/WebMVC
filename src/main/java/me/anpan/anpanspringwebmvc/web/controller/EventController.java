package me.anpan.anpanspringwebmvc.web.controller;

import me.anpan.anpanspringwebmvc.web.dto.Person;
import me.anpan.anpanspringwebmvc.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/events")
    public String event(Model model){
        model.addAttribute("evnets",eventService.getEvents());
        return "events";
    }

    @GetMapping("/message")
    @ResponseBody
    public String message(@RequestBody String body) {
        return body;
    }

}
