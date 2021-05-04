package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.error_handling.MarketError;
import ru.geekbrains.april.market.error_handling.exceptions.InvalidFieldException;
import ru.geekbrains.april.market.error_handling.exceptions.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class V1ProductsRestController {
    private final ProductsService productsService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProductById(@PathVariable Long id) {
        Product product = productsService.getProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукта с ID="+ id +", не существует!")
        );
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        List<String> errors = new ArrayList<>();
        if (product.getTitle().length() < 3) {
            errors.add("Недостаточно символов!");
        }
        if (product.getPrice() < 0) {
            errors.add("Неверная цена продукта");
        }
        if (errors.size() > 0) {
            throw new InvalidFieldException("Ошибка валидации", errors);
        }
        Product out = productsService.saveProduct(product);
        return new ResponseEntity<>(out, HttpStatus.CREATED);
    }

    @PutMapping
    public void update(@RequestBody Product product) {
        productsService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteProductById(id);
    }
}
