package ru.geekbrains.aprilmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.aprilmarket.models.Product;
import ru.geekbrains.aprilmarket.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> saveProduct(Product product) {
        return Optional.of(productRepository.save(product));
    }

    @Transactional
    public Optional<Product> setProductById(Long id, Product editedProduct) {
        try{
            Product product = productRepository.findById(id).orElseThrow(
                    ()->{
                        throw new IllegalArgumentException("Incorrect id");
                    }
            );
            product.getCategory().setTitle(editedProduct.getCategory().getTitle());
            product.setTitle(editedProduct.getTitle());
            product.setPrice(editedProduct.getPrice());
            return Optional.of(product);
        }catch (IllegalArgumentException e){
            System.out.println("not found");
            return Optional.empty();
        }
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
