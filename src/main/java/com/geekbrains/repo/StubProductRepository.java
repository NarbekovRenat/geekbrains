package com.geekbrains.repo;

import com.geekbrains.entity.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StubProductRepository implements ProductRepository{
    private List<Product> products;

    /**
     * See {@link ProductRepository}
     * */
    public List<Product> getAllProducts() {
        return products;
    }

    /**
     * Get product by input id
     * @return null if not present
     * See {@link ProductRepository}
     * */
    public Product getProductById(int id) {
        return products.stream().filter((p) ->
            p.getId() == id
        ).findFirst().orElse(null);
    }

    @PostConstruct
    private void init(){
        products = new ArrayList<>(Arrays.asList(
                new Product(1, "Banana", 25),
                new Product(2, "Wine", 250),
                new Product(3, "Beer", 35),
                new Product(4, "Nut", 55),
                new Product(5, "Snikers", 100)
        ));
    }
}
