package com.development.book.domain;

import java.util.List;

public interface BookRepository {
    List<Book> getBooksByNames(String... name);
}
