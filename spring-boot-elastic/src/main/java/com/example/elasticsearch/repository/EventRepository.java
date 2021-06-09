package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.Event;
import com.example.elasticsearch.model.Type;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends ElasticsearchRepository<Event, String> {

    List<Event> findAllByTitle(String title);
    List<Event> findAllByType(Type type);
    List<Event> findAllByTitleAndTimeGreaterThan(String title, LocalDateTime time);
}
