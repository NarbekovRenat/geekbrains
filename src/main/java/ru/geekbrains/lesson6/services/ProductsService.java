package ru.geekbrains.lesson6.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson6.repositories.ProductsRepository;
import ru.geekbrains.lesson6.models.Product;

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
        return productsRepository.findAll();
    }

    /**
     * Добавить новый продукт в репозиторий
     * @param product Новый товар для добавления
     * **/
    public void addNewProduct(Product product) {
        productsRepository.saveOrUpdate(product);
    }

    /**
     * Удалить продукт по ID из репозитория
     * @param id Id товара для удаления
     * **/
    public void deleteProductById(long id) {
        productsRepository.deleteById(id);
    }

    /**
     * Изменить цену продукта по ID
     * @param id Id товара для изменения
     * @param cost Sign для инкремента/декремента
     * **/
    public void setProductPriceById(Long id, Long cost) {
        Product product = productsRepository
                .findOneById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        if(cost > 0)
            product.incrementCost();
        else
            product.decrementCost();
        productsRepository.saveOrUpdate(product);
    }

    /**
     * Поиск продукта по ID
     * @param id Id товара
     * **/
    public Optional<Product> getProductById(long id) {
        return productsRepository.findOneById(id);
    }

    /**
     * Поиск продукта по Категории
     * @param category Категория товара
     * **/
    public List<Product> getAllProductsByCategory(String category){
        try {
            return productsRepository.findAllByCategory(category);
        }catch (NullPointerException ex){
               throw new IllegalArgumentException("Invalid product Category:" + category);
        }
    }
}
