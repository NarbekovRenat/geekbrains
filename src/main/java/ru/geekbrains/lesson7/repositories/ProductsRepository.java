package ru.geekbrains.lesson7.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson7.models.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategory_Title(String title, Pageable pageable);
    Page<Product> findAllByNameLike(String name, Pageable pageable);
    Page<Product> findAllByPriceBetween(Long min, Long max, Pageable pageable);
    Page<Product> findAllByPriceGreaterThanEqual(Long min, Pageable pageable);
}
