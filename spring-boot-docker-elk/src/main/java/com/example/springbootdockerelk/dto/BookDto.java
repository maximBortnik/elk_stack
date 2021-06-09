package com.example.springbootdockerelk.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bortnik
 */
@Data
@Builder
@JsonPropertyOrder(value = { "id", "name" })
public class BookDto {

    private Long id;
    @NotEmpty
    private String name;

    @JsonCreator
    public BookDto(@JsonProperty final Long id,
                   @JsonProperty final String name) {
        this.id = id;
        this.name = name;
    }

}
