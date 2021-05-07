package ru.geekbrains.april.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.april.market.models.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.nio.charset.MalformedInputException;

@Data
@NoArgsConstructor
public class ProductDto {
    private static final int MIN_PRICE = 0;
    private static final int MAX_PRICE = 1000;
    private static final int MIN_TITLE_SIZE = 2;
    private static final int MAX_TITLE_SIZE = 20;

    private Long id;

    @Size(min = MIN_TITLE_SIZE, max = MAX_TITLE_SIZE, message = "Title length in range from " + MIN_TITLE_SIZE + " to " + MAX_TITLE_SIZE)
    private String title;

    @Min(value = MIN_PRICE, message = "Price must be greater than" + MIN_PRICE)
    private int price;

    private String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
