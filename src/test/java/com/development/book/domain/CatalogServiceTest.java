package com.development.book.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CatalogServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private CatalogService catalogService;

    @Test
    void oneBook_getNoDiscount() {
        when(bookRepository.getBooksByNames("Clean Code")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 1)
        ));

        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("50.00"), price);
    }

    @Test
    void twoDifferentBooks_get5PercentDiscount() {
        when(bookRepository.getBooksByNames("Clean Code", "The Clean Coder")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50),
                        new Book("The Clean Coder", "Robert Martin", 2011, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 1),
                new BasketItem("The Clean Coder", 1)
        ));

        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("95.00"), price);
    }

    @Test
    void threeDifferentBooks_get10PercentDiscount() {
        when(bookRepository.getBooksByNames("Clean Code", "The Clean Coder", "Clean Architecture")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50),
                        new Book("The Clean Coder", "Robert Martin", 2011, 50),
                        new Book("Clean Architecture", "Robert Martin", 2017, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 1),
                new BasketItem("The Clean Coder", 1),
                new BasketItem("Clean Architecture", 1)
        ));
        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("135.00"), price);
    }

    @Test
    void fourDifferentBooks_get20PercentDiscount() {
        when(bookRepository.getBooksByNames("Clean Code", "The Clean Coder", "Clean Architecture", "Test Driven Development by Example")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50),
                        new Book("The Clean Coder", "Robert Martin", 2011, 50),
                        new Book("Clean Architecture", "Robert Martin", 2017, 50),
                        new Book("Test Driven Development by Example", "Kent Beck", 2003, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 1),
                new BasketItem("The Clean Coder", 1),
                new BasketItem("Clean Architecture", 1),
                new BasketItem("Test Driven Development by Example", 1)
        ));
        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("160.00"), price);
    }

    @Test
    void fiveDifferentBooks_get25PercentDiscount() {
        when(bookRepository.getBooksByNames("Clean Code", "The Clean Coder", "Clean Architecture", "Test Driven Development by Example", "Working Effectively With Legacy Code")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50),
                        new Book("The Clean Coder", "Robert Martin", 2011, 50),
                        new Book("Clean Architecture", "Robert Martin", 2017, 50),
                        new Book("Test Driven Development by Example", "Kent Beck", 2003, 50),
                        new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 1),
                new BasketItem("The Clean Coder", 1),
                new BasketItem("Clean Architecture", 1),
                new BasketItem("Test Driven Development by Example", 1),
                new BasketItem("Working Effectively With Legacy Code", 1)
        ));
        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("187.50"), price);
    }

    @Test
    void eightBooksWithFourSeries_titles_discountOnlyAppliesToFour() {
        when(bookRepository.getBooksByNames("Clean Code", "The Clean Coder", "Clean Architecture", "Test Driven Development by Example", "Working Effectively With Legacy Code")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50),
                        new Book("The Clean Coder", "Robert Martin", 2011, 50),
                        new Book("Clean Architecture", "Robert Martin", 2017, 50),
                        new Book("Test Driven Development by Example", "Kent Beck", 2003, 50),
                        new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 2),
                new BasketItem("The Clean Coder", 2),
                new BasketItem("Clean Architecture", 2),
                new BasketItem("Test Driven Development by Example", 1),
                new BasketItem("Working Effectively With Legacy Code", 1)
        ));
        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("322.50"), price);
    }

    @Test
    void eightBooksWithOneSetOfFiveAndOneSetOfThree_titles_discountApplies() {
        when(bookRepository.getBooksByNames("Clean Code", "The Clean Coder", "Clean Architecture", "Test Driven Development by Example", "Working Effectively With Legacy Code")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50),
                        new Book("The Clean Coder", "Robert Martin", 2011, 50),
                        new Book("Clean Architecture", "Robert Martin", 2017, 50),
                        new Book("Test Driven Development by Example", "Kent Beck", 2003, 50),
                        new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 2),
                new BasketItem("The Clean Coder", 2),
                new BasketItem("Clean Architecture", 2),
                new BasketItem("Test Driven Development by Example", 1),
                new BasketItem("Working Effectively With Legacy Code", 1)
        ));
        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("322.50"), price);
    }

    @Test
    void fourBooksWitThreeDifferent_titles_discountApplies() {
        when(bookRepository.getBooksByNames("Clean Code", "The Clean Coder", "Clean Architecture")).thenReturn(
                List.of(
                        new Book("Clean Code", "Robert Martin", 2008, 50),
                        new Book("The Clean Coder", "Robert Martin", 2011, 50),
                        new Book("Clean Architecture", "Robert Martin", 2017, 50)
                )
        );

        Basket basket = new Basket(List.of(
                new BasketItem("Clean Code", 2),
                new BasketItem("The Clean Coder", 1),
                new BasketItem("Clean Architecture", 1)
        ));
        BigDecimal price = catalogService.getPrice(basket);

        assertEquals(new BigDecimal("185.00"), price);
    }
}