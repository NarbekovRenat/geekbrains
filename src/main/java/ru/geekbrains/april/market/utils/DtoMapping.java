package ru.geekbrains.april.market.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.dto.CartDto;
import ru.geekbrains.april.market.dto.ProductDto;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Product;

import java.util.stream.Collectors;

@Service
public class DtoMapping {
    //entity -> dto
    public ProductDto mapToProductDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setCategoryTitle(product.getCategory().getTitle());
        return dto;
    }

    //dto -> entity
    public Product mapToProductEntity(ProductDto dto, Category category){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        return product;
    }

    public Page<ProductDto> convertToProductDtoPage(Page<Product> productsPage){
        return new PageImpl<>(
                productsPage.getContent().stream()
                        .map(ProductDto::new)
                        .collect(Collectors.toList()),
                productsPage.getPageable(),
                productsPage.getTotalElements());
    }
}
