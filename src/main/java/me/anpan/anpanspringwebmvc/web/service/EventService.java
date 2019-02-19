package me.anpan.anpanspringwebmvc.web.service;

import me.anpan.anpanspringwebmvc.web.dto.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    public List<Event> getEvents() {
        Event event = Event.builder()
                .name("스프링 웹 mvc 스터디 1차")
                .limitOfEnrollment(5)
                .startDateTime(LocalDateTime.of(2019, 1, 10, 14, 0))
                .endDateTime(LocalDateTime.of(2019, 1, 10, 16, 0))
                .build();

        Event event2 = Event.builder()
                .name("스프링 웹 mvc 스터디 2차")
                .limitOfEnrollment(5)
                .startDateTime(LocalDateTime.of(2019, 1, 12, 14, 0))
                .endDateTime(LocalDateTime.of(2019, 1, 12, 16, 0))
                .build();

        List<Event> eventList = new ArrayList<Event>();
        eventList.add(event);
        eventList.add(event2);

        return eventList;
    }
}
