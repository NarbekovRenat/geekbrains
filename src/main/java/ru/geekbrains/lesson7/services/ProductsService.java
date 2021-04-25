package ru.geekbrains.lesson7.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson7.repositories.ProductsRepository;
import ru.geekbrains.lesson7.models.Product;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsService {
    private ProductsRepository productsRepository;

    /**
     * Получить все продукты в репозитории
     * @param pageIndex Индекс запрашиваемой страницы
     * @param pagesSize Размер пачки страниц
     * @return Страницу продуктов типа {@link Page<Product>}
     * **/
    public Page<Product> getAllProducts(int pageIndex, int pagesSize) {
        return productsRepository.findAll(PageRequest.of(pageIndex, pagesSize));
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
        productsRepository.deleteById(id);
    }

    /**
     * Изменить цену продукта по ID
     * @param id Id товара для изменения
     * @param sign Знак для инкремента/декремента
     * **/
    @Transactional
    public void changeProductPriceByIdWithSign(Long id, Long sign) {
        Product product = productsRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        if(sign > 0)
            product.incrementCost();
        else
            product.decrementCost();
    }

    /**
     * Поиск продукта по ID
     * @param id Id товара
     * **/
    public Optional<Product> getProductById(long id) {
        return productsRepository.findById(id);
    }

    /**
     * Поиск продукта по Категории
     * @param category Категория товара
     * @param pageIndex Индекс запрашиваемой страницы
     * @param pagesSize Размер пачки страниц
     * @return Страницу продуктов типа {@link Page<Product>}
     * **/
    public Page<Product> getAllProductsByCategory(String category, int pageIndex, int pagesSize){
        try {
            return productsRepository.findAllByCategory_Title(category, PageRequest.of(pageIndex, pagesSize));
        }catch (NullPointerException ex){
            throw new IllegalArgumentException("Invalid product Category:" + category);
        }
    }

    /**
     * Поиск продуктов по названию
     * Название оборачивает в %{@param name}% для использования в LIKE операторе
     * @param name Имя для поиска
     * @param pageIndex Индекс запрашиваемой страницы
     * @param pagesSize Размер пачки страниц
     * @return Страницу продуктов типа {@link Page<Product>}
     * **/
    public Page<Product> getAllProductsByName(String name, int pageIndex, int pagesSize){
        try {
            if (name.equals("*"))
                return getAllProducts(pageIndex, pagesSize);
            return productsRepository.findAllByNameLike("%" + name + "%", PageRequest.of(pageIndex, pagesSize));
        }catch (NullPointerException ex){
            throw new IllegalArgumentException("Invalid product name:" + name);
        }
    }

    /**
     * Поиск продуктов по цене
     * Название оборачивает в %{@param name}% для использования в LIKE операторе
     * @param minCost Минимальная цена продукта
     * @param maxCost Максимальная цена продукта
     * @param pageIndex Индекс запрашиваемой страницы
     * @param pagesSize Размер пачки страниц
     * @return Страницу продуктов типа {@link Page<Product>}
     * **/
    public Page<Product> getAllProductsByPrice(Long minCost, Long maxCost, int pageIndex, int pagesSize) {
        if (maxCost == -1)
            return productsRepository.findAllByPriceGreaterThanEqual(minCost, PageRequest.of(pageIndex, pagesSize));
        return productsRepository.findAllByPriceBetween(minCost, maxCost, PageRequest.of(pageIndex, pagesSize));
    }
}
