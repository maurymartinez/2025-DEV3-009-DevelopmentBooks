package com.development.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final BookRepository bookRepository;

    public BigDecimal getPrice(Basket basket) {
        List<Book> books = bookRepository.getBooksByNames(basket.getNames());
        Map<Book, Integer> remainingBooks = basket.getBookAmountMap(books);

        BigDecimal totalPrice = BigDecimal.ZERO;

        while (!remainingBooks.isEmpty()) {
            totalPrice = totalPrice.add(calculateDiscountedSetPrice(remainingBooks));
            remainingBooks.entrySet().removeIf(entry -> entry.getValue() == 0);
        }

        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateDiscountedSetPrice(Map<Book, Integer> bookCounts) {
        BigDecimal setPrice = BigDecimal.ZERO;
        int uniqueBooksInSet = 0;

        for (Map.Entry<Book, Integer> entry : bookCounts.entrySet()) {
            if (entry.getValue() > 0) {
                setPrice = setPrice.add(BigDecimal.valueOf(entry.getKey().price()));
                entry.setValue(entry.getValue() - 1);
                uniqueBooksInSet++;
            }
        }

        return setPrice.multiply(getDiscountMultiplier(uniqueBooksInSet));
    }

    private BigDecimal getDiscountMultiplier(int uniqueCount) {
        return switch (uniqueCount) {
            case 1 -> BigDecimal.ONE;
            case 2 -> BigDecimal.valueOf(0.95);
            case 3 -> BigDecimal.valueOf(0.90);
            case 4 -> BigDecimal.valueOf(0.80);
            default -> BigDecimal.valueOf(0.75);
        };
    }

}
