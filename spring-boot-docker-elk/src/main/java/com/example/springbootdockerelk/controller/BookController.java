package com.example.springbootdockerelk.controller;

import com.example.springbootdockerelk.converter.Converter;
import com.example.springbootdockerelk.dto.BookDto;
import com.example.springbootdockerelk.error.ApiError;
import com.example.springbootdockerelk.model.Book;
import com.example.springbootdockerelk.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bortnik
 */
@RestController
@RequestMapping(value = "/api/v1/books")
@Api(value = "BookController", produces = "application/json", consumes = "application/json")
public class BookController {

    private final BookService bookService;
    private final Converter<BookDto, Book> bookConverter;

    @Autowired
    public BookController(final BookService bookService,
                          @Qualifier(value = "bookConverter") final Converter<BookDto, Book> bookConverter) {
        this.bookService = bookService;
        this.bookConverter = bookConverter;
    }

    @ApiOperation(value = "Get list of available books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BookDto.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Unexpected error", response = ApiError.class)
    })
    @GetMapping(value = "/")
    public List<BookDto> findAll(@PageableDefault(size = 5, sort = "name") final Pageable pageable) {
        final List<Book> books = bookService.findAll();
        return bookConverter.mapToDtos(books);
    }

    @ApiOperation(value = "Get a book by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BookDto.class),
            @ApiResponse(code = 404, message = "Entity not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Unexpected error", response = ApiError.class)
    })
    @GetMapping(value = "/{id}")
    public BookDto findById(@PathVariable(value = "id") final Long id) {
        final Book book = bookService.findById(id);
        return bookConverter.mapToDto(book);
    }

    @ApiOperation(value = "Create new book")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = BookDto.class),
            @ApiResponse(code = 500, message = "Unexpected error", response = ApiError.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public BookDto create(@RequestBody final BookDto bookDto) {
        final Book book = bookConverter.mapToEntity(bookDto);
        final Book result = bookService.create(book);
        return bookConverter.mapToDto(result);
    }

    @ApiOperation(value = "Update a book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BookDto.class),
            @ApiResponse(code = 404, message = "Entity not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Unexpected error", response = ApiError.class)
    })
    @PutMapping(value = "/")
    public BookDto update(@RequestBody final BookDto bookDto) {
        final Book book = bookConverter.mapToEntity(bookDto);
        bookService.update(book);
        return bookDto;
    }

    @ApiOperation(value = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BookDto.class),
            @ApiResponse(code = 404, message = "Entity not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Unexpected error", response = ApiError.class)
    })
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") final Long id) {
        bookService.deleteById(id);
    }

 }
