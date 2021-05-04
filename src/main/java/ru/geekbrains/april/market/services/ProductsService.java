package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productsRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }
}
