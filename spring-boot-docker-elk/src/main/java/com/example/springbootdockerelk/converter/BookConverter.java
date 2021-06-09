package com.example.springbootdockerelk.converter;

import com.example.springbootdockerelk.dto.BookDto;
import com.example.springbootdockerelk.model.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author bortnik
 */
@Component
@Qualifier(value = "bookConverter")
public class BookConverter implements Converter<BookDto, Book>{

    @Override
    public Book mapToEntity(final BookDto dto) {
        final Book book = new Book();
        book.setId(dto.getId());
        book.setName(dto.getName());
        return book;
    }

    @Override
    public BookDto mapToDto(final Book entity) {
        return BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
