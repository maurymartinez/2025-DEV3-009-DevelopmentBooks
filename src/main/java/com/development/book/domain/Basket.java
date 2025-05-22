package com.development.book.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class Basket {
    private List<BasketItem> items;

    public String[] getNames() {
        return items.stream()
                .map(BasketItem::getName)
                .distinct()
                .toArray(String[]::new);
    }

    public Map<Book, Integer> getBookAmountMap(List<Book> books) {
        Map<Book, Integer> bookAmount = new HashMap<>();

        for (Book book : books) {
            Optional<BasketItem> bookItem = getItems().stream()
                    .filter(basketItem -> basketItem.getName().equalsIgnoreCase(book.name()))
                    .findFirst();
            bookItem.ifPresent(basketItem -> bookAmount.put(book, basketItem.getAmount()));
        }

        return bookAmount;
    }
}
