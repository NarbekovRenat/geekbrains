package ru.geekbrains.april.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    /**
     * Список ProductDto для обмена с фронтом
     * **/
    private List<ProductDto> productsDto;
}
