package com.development.book.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class Basket {
    private List<BasketItem> items;
}
