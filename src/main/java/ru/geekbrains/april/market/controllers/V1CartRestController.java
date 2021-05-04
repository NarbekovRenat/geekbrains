package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.error_handling.exceptions.InvalidFieldException;
import ru.geekbrains.april.market.error_handling.exceptions.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.utils.Cart;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class V1CartRestController {
    private final Cart cart;

    @GetMapping
    public List<Product> getAllProductsInCart() {
        return cart.getAllItemsInCart();
    }

    @PutMapping
    public void addProductToCart(@RequestBody Product product) {
        cart.addToCart(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cart.deleteFromCartById(id);
    }

    @GetMapping("/wipe")
    public void wipeCart(){
        cart.wipeCart();
    }

    @GetMapping("/count")
    public int countProductsInCart(){
        return cart.getAllItemsInCart().size();
    }
}
