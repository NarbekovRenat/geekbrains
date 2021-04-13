package ru.geekbrains.lesson3.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson3.models.Product;

/**
 * Интерфейс CRUD репозитория JPA
 * Spring сам подкидывает реализацию по умолчанию
 * */
@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {

}
