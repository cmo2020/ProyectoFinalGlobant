package com.Book.projectBook.Repository;


import com.Book.projectBook.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByOrderByTitleAsc();

    Book findByTitle(String title);

    List<Book> findByBookingIsNull();

    List<Book> findByBookingNotNull();



}
