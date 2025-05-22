package com.development.book.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class BasketItem {
    private String name;
    private int amount;
}
