package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dto.ProductDto;
import ru.geekbrains.april.market.error_handling.exceptions.InvalidFieldException;
import ru.geekbrains.april.market.error_handling.exceptions.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductsService;
import ru.geekbrains.april.market.utils.DtoMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
public class V1ProductsRestController {
    private final int PAGE_SIZE = 5;
    private final ProductsService productsService;
    private final DtoMapping dtoMapping;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        Page<Product> productsPage = productsService.getAllProducts(pageIndex - 1, PAGE_SIZE);
        return dtoMapping.convertToProductDtoPage(productsPage);
    }

    @GetMapping("/{id}")
    public ProductDto getOneProductById(@PathVariable Long id) {
        Product product = productsService.getProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукта с ID="+ id +", не существует!")
        );
        return dtoMapping.mapToProductDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(
                    "Create new product error",
                    bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return productsService.saveProduct(productDto);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productsService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteProductById(id);
    }
}
