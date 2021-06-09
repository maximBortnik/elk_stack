package com.example.springbootdockerelk.service;

import com.example.springbootdockerelk.exception.EntityNotFoundException;
import com.example.springbootdockerelk.model.Book;
import com.example.springbootdockerelk.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author bortnik
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "books")
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Cacheable
    public List<Book> findAll() {
        log.debug("Getting all books from db");
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(key = "#id")
    public Book findById(final Long id) {
        log.debug("Getting a book from db by id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity does not exist: " + id));
    }

    @Override
    @CachePut(key = "#book.id")
    public Book create(final Book book) {
        log.debug("Create new book: {}", book);
        if (nonNull(book.getId())) {
            throw new IllegalStateException("Id must be null");
        }
        return bookRepository.save(book);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(key = "#book.id")
            },
            put = {
                    @CachePut(key = "#book.id")
            }
    )
    public void update(final Book book) {
        log.debug("Update a book: {}", book);
        if (isNull(book.getId())) {
            throw new IllegalStateException("Id must not be null");
        }
        bookRepository.save(book);
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteById(final Long id) {
        log.debug("Deleting book by id: {}", id);
        bookRepository.deleteById(id);
    }
}
