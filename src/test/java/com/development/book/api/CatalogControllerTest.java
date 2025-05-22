package com.development.book.api;

import com.development.book.api.dto.BasketDto;
import com.development.book.domain.Basket;
import com.development.book.domain.CatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogController.class)
class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnPriceDto() throws Exception {
        BasketDto basketDto = new BasketDto(List.of(
                new BasketDto.Item("Clean Code", 1),
                new BasketDto.Item("The Clean Coder", 1)
        ));

        Mockito.when(catalogService.getPrice(Mockito.any(Basket.class)))
                .thenReturn(new BigDecimal("95.00"));

        mockMvc.perform(post("/api/catalog/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basketDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(95.00));
    }
}
