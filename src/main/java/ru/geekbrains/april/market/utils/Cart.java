package ru.geekbrains.april.market.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.april.market.error_handling.exceptions.InvalidFieldException;
import ru.geekbrains.april.market.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addToCart(Product product){
        items.add(product);
    }

    public void deleteFromCartById(Long id){
        Product product = items.stream().filter((p)->p.getId().equals(id)).findFirst().orElseThrow(
                ()-> new InvalidFieldException("Не найден ID")
        );
        items.remove(product);
    }

    public List<Product> getAllItemsInCart() {
        return items;
    }

    public void wipeCart(){
        items.clear();
    }
}
