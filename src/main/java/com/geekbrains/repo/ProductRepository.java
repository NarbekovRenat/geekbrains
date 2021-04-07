package com.geekbrains.repo;

import com.geekbrains.entity.Product;

import java.util.List;

public interface ProductRepository {
    /**
     * @return List of objects with type {@link Product}
     * */
    List<Product> getAllProducts();

    /**
     * @param id ID product
     * @return Object with type {@link Product}
     * */
    Product getProductById(int id);
}
