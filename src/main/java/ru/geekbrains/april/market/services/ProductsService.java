package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.dto.ProductDto;
import ru.geekbrains.april.market.error_handling.exceptions.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.ProductsRepository;
import ru.geekbrains.april.market.utils.DtoMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoryService categoryService;
    private final DtoMapping dtoMapping;

    public Page<Product> getAllProducts(int page, int pageSize) {
        return productsRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(
                () -> new ResourceNotFoundException("Такой категории не существует")
        );
        Product product = dtoMapping.mapToProductEntity(productDto, category);
        productsRepository.save(product);
        return dtoMapping.mapToProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto){
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Продукта с таким ID не существует")
        );
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(
                () -> new ResourceNotFoundException("Такой категории не существует")
        );
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        return dtoMapping.mapToProductDto(product);
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }
}
