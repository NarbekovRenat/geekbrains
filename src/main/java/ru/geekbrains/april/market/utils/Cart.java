package ru.geekbrains.april.market.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.dto.CartDto;
import ru.geekbrains.april.market.error_handling.exceptions.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductsService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Cart {
    private final ProductsService productsService;
    private final DtoMapping dtoMapping;
    private List<Product> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public List<Product> getAllItemsInCart() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Добавляет товар в корзину по ID
     * @param id ID товара для добавления
     * @return {@link CartDto}
     * **/
    @Transactional
    public CartDto addToCartById(Long id){
        Product product = productsService.getProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Не найден ID")
        );
        items.add(product);
        CartDto cartDto = new CartDto();
        cartDto.setProductsDto(items.stream()
                .map(dtoMapping::mapToProductDto)
                .collect(Collectors.toList())
        );
        return cartDto;
    }

    /**
     * Удаляет один товар из корзины
     * @param id ID товара для удаления
     * **/
    public void deleteFromCartById(Long id){
        Product product = items.stream().filter((p)->p.getId().equals(id)).findFirst().orElseThrow(
                ()-> new ResourceNotFoundException("Не найден ID")
        );
        items.remove(product);
    }

    /**
     * Размер корзины
     * @return Количество элементов в корзине
     * **/
    public int getSize() {
        return items.size();
    }

    /**
     * Очистка корзины
     * **/
    public void wipeCart(){
        items.clear();
    }
}
