package com.development.book.api;

import com.development.book.api.dto.BasketDto;
import com.development.book.api.dto.PriceDto;
import com.development.book.domain.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/price")
    public PriceDto getBasketPrice(@RequestBody BasketDto basketDto) {
        return new PriceDto(catalogService.getPrice(basketDto.toBasket()));
    }
}