package com.development.book.api;

import com.development.book.api.dto.BasketDto;
import com.development.book.api.dto.PriceDto;
import com.development.book.domain.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/price")
    @Operation(
            summary = "Calculate the total price of a basket of books",
            description = "Returns the discounted total price for a basket of books based on unique titles.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BasketDto.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Example Basket",
                                    value = """
                                            {
                                              "items": [
                                                { "name": "Clean Code", "amount": 1 },
                                                { "name": "The Clean Coder", "amount": 1 },
                                                { "name": "Clean Architecture", "amount": 1 },
                                                { "name": "Test Driven Development by Example", "amount": 1 },
                                                { "name": "Working Effectively With Legacy Code", "amount": 1 }
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully calculated price",
                            content = @Content(schema = @Schema(implementation = PriceDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    public PriceDto getBasketPrice(@RequestBody BasketDto basketDto) {
        return new PriceDto(catalogService.getPrice(basketDto.toBasket()));
    }
}