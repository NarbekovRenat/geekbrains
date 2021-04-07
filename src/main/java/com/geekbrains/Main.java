package com.geekbrains;

import com.geekbrains.entity.Cart;
import com.geekbrains.repo.ProductRepository;
import com.geekbrains.repo.StubProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Корзина не должна давать методов доступа к товарам в репозитории
        // Поэтому вернем из контекста репозиторий здесь для показа всех товаров
        ProductRepository productRepository = context.getBean("stubProductRepository", StubProductRepository.class);
        System.out.println("Available products: " + productRepository.getAllProducts());

        // Создадим для примера 3 пользовательские корзины
        Cart consumerCart1 = context.getBean("cart", Cart.class);
        Cart consumerCart2 = context.getBean("cart", Cart.class);
        Cart consumerCart3 = context.getBean("cart", Cart.class);

        // Проделаем операции с корзинами
        // Если продукта с таким id не существует, то он проигнорирует это
        // По рекомендациям не стал организовывать интерактив
        consumerCart1.addProduct(1);
        consumerCart1.addProduct(4);
        consumerCart1.addProduct(3);
        consumerCart1.deleteProduct(1);
        System.out.println("Cart 1: " + consumerCart1.getProductInCart());

        consumerCart2.addProduct(5);
        consumerCart2.addProduct(2);
        consumerCart2.addProduct(3);
        consumerCart2.deleteProduct(2);
        System.out.println("Cart 2: " + consumerCart2.getProductInCart());

        consumerCart3.addProduct(2);
        consumerCart3.addProduct(2);
        consumerCart3.addProduct(2);
        consumerCart3.deleteProduct(1);
        System.out.println("Cart 3: " + consumerCart3.getProductInCart());

        context.close();
    }
}
