package ru.geekbrains.lesson5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson5.dao.ProductDAO;
import ru.geekbrains.lesson5.models.Product;


public class MainApp {
    private static SessionFactory factory;
    private static ProductDAO productDAO;

    public static void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try {
            init();
            // hibernate.hbm2ddl.auto = update ->
            // Не требуется предварительное создание таблиц
            productDAO = new ProductDAO(factory);

            // 5 продуктов для тестирования
            productDAO.saveOrUpdate(new Product(
               1L, "Морковь", 100L
            ));
            productDAO.saveOrUpdate(new Product(
                    2L, "Редиска", 100L
            ));
            productDAO.saveOrUpdate(new Product(
                    3L, "Тыква", 100L
            ));
            productDAO.saveOrUpdate(new Product(
                    4L, "Картошка", 100L
            ));
            productDAO.saveOrUpdate(new Product(
                    5L, "Лук", 100L
            ));

            // Получить все продукты
            System.out.println(productDAO.findAll());
            // Получить продукт по id
            System.out.println(productDAO.findById(2L));
            // Удалить продукт по id
            productDAO.deleteById(4L);
            // Снова получить все продукты
            System.out.println(productDAO.findAll());
        } finally {
            shutdown();
        }
    }

    public static void shutdown() {
        factory.close();
    }
}
