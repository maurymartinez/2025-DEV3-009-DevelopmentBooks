package com.development.book.infrastructure;

import com.development.book.domain.Book;
import com.development.book.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class HardcodeBookRepository implements BookRepository {
    private List<Book> books = List.of(
            new Book("Clean Code", "Robert Martin", 2008, 50),
            new Book("The Clean Coder", "Robert Martin", 2011, 50),
            new Book("Clean Architecture", "Robert Martin", 2017, 50),
            new Book("Test Driven Development by Example", "Kent Beck", 2003, 50),
            new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50)
    );


    @Override
    public List<Book> getBooksByNames(String... names) {
        Set<String> lowerCaseNames = Arrays.stream(names)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        return books.stream()
                .filter(book -> lowerCaseNames.contains(book.name().toLowerCase()))
                .toList();
    }
}

