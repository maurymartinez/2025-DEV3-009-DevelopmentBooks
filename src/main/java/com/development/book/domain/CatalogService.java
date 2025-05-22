package com.development.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final BookRepository bookRepository;

    public BigDecimal getPrice(Basket basket) {
        throw  new UnsupportedOperationException("Not implemented yet.");
    }

}
