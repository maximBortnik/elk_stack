package com.example.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(indexName = "elk")
public class Event implements Serializable {
    @Id
    private String id;
    private String title;
    private Type type;
    private LocalDateTime time;
    private String place;
    private String description;
    private List<String> topics;
}
