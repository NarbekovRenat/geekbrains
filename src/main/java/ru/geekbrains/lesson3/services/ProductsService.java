package ru.geekbrains.lesson3.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson3.models.Product;
import ru.geekbrains.lesson3.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsService {
    private ProductsRepository productsRepository;

    /**
     * Получить все продукты в репозитории
     * @return Список продуктов типа {@link List<Product>}
     * **/
    public List<Product> getAllProducts() {
        return (List<Product>) productsRepository.findAll();
    }

    /**
     * Добавить новый продукт в репозиторий
     * @param product Новый товар для добавления
     * **/
    public void addNewProduct(Product product) {
        productsRepository.save(product);
    }

    /**
     * Удалить продукт по ID из репозитория
     * @param id Id товара для удаления
     * **/
    public void deleteProductById(long id) {
        Product prod = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        productsRepository.delete(prod);
    }

    /**
     * Изменить цену продукта по ID
     * @param id Id товара для изменения
     * @param cost Новая цена товара
     * **/
    public void setProductCostById(long id, long cost) {
        Product product = productsRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        product.setCost(cost);
        productsRepository.save(product);
    }

    /**
     * Поиск продукта по ID
     * @param id Id товара
     * **/
    public Optional<Product> getProductById(long id) {
        return productsRepository.findById(id);
    }
}
