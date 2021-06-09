package com.example.springbootdockerelk.repository;

import com.example.springbootdockerelk.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bortnik
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
