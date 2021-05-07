package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dto.CartDto;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductsService;
import ru.geekbrains.april.market.utils.Cart;
import ru.geekbrains.april.market.utils.DtoMapping;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class V1CartRestController {
    private final Cart cart;
    private final ProductsService productsService;
    private final DtoMapping dtoMapping;

    /**
     * Запрос текущего состояния корзины
     * @return {@link CartDto} DTO для {@link Cart}
     * **/
    @GetMapping
    public CartDto getAllProductsInCart() {
        List<Product> productsList = cart.getAllItemsInCart();
        CartDto cartDto = new CartDto();
        cartDto.setProductsDto(productsList.stream()
                .map(dtoMapping::mapToProductDto)
                .collect(Collectors.toList())
        );
        return cartDto;
    }

    /**
     * Добавление товара в корзину
     * @param id ID товара для добавления
     * @return {@link CartDto} DTO для {@link Cart}
     * **/
    @PutMapping
    public CartDto addProductToCart(@RequestParam Long id) {
        return cart.addToCartById(id);
    }

    /**
     * Удаление товара из корзины
     * @param id ID товара для удаления
     * **/
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cart.deleteFromCartById(id);
    }

    /**
     * Удаление всех товаров из корзины
     * **/
    @GetMapping("/wipe")
    public void wipeCart(){
        cart.wipeCart();
    }

    /**
     * Запрос размера корзины
     * **/
    @GetMapping("/count")
    public int countProductsInCart(){
        return cart.getSize();
    }
}
