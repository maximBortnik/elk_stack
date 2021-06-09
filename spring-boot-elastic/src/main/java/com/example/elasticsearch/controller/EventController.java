package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.Event;
import com.example.elasticsearch.model.Type;
import com.example.elasticsearch.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    @PostMapping
    public Event create(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @PutMapping
    public Event update(@RequestBody Event event) {
        var exist = eventRepository.existsById(event.getId());
        if (!exist) {
            throw new IllegalArgumentException("Document doesn't exist");
        }
        return eventRepository.save(event);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        eventRepository.deleteById(id);
        return "Deleted";
    }

    @GetMapping
    public List<Event> getAll(@RequestParam(required = false) String title,
                              @RequestParam(required = false) Type type) {

        if (title != null) {
            return eventRepository.findAllByTitle(title);
        }

        if (type != null) {
            return eventRepository.findAllByType(type);
        }

        var all = eventRepository.findAll();
        var result = new ArrayList<Event>();
        all.forEach(result::add);
        return result;
    }
}
