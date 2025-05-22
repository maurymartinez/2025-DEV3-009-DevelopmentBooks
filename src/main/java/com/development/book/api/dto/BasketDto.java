package com.development.book.api.dto;

import com.development.book.domain.Basket;
import com.development.book.domain.BasketItem;

import java.util.List;

public record BasketDto(List<Item> items) {

    public Basket toBasket() {
        return new Basket(
                items.stream().map(item -> new BasketItem(item.name(), item.amount())).toList()
        );
    }

    public record Item(String name, int amount) {
    }
}
