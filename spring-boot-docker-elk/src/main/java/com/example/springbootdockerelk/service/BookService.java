package com.example.springbootdockerelk.service;

import com.example.springbootdockerelk.model.Book;

import java.util.List;

/**
 * @author bortnik
 */
public interface BookService {

    List<Book> findAll();
    Book findById(Long id);
    Book create(Book book);
    void update(Book book);
    void deleteById(Long id);

}
