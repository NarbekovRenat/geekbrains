package ru.geekbrains.lesson7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson7.models.*;
import ru.geekbrains.lesson7.services.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private int PAGE_SIZE = 5;
    private ProductsService productsService;

    @Autowired
    public MainController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * Выводит страницу с листом продуктов
     * @param pageIndex Индекс запрашиваемой страницы
     * @return Главную страницу (index.html)
     * **/
    @GetMapping("/index")
    public String getProductsList(@RequestParam(name = "p", defaultValue = "1") int pageIndex, Model model){
        Page<Product> page = productsService.getAllProducts(pageIndex - 1, PAGE_SIZE);
        model.addAttribute("page", page);
        return "index";
    }

    /**
     * Redirect from root / to /index.html
     * **/
    @GetMapping("/")
    public String redirectFromRoot(Model model){
        return "redirect:/index";
    }

    /**
     * GET запрос для отображения страницы добавления товара
     * @return HTML форму добавления товара
     * **/
    @GetMapping("/addproduct")
    public String showNewProductForm(Product product){
        return "add-product";
    }

    /**
     * POST запрос для добавления товара
     * @param product Данные с формы
     * @return Redirect на главную страницу
     * **/
    @PostMapping("/addproduct")
    public String addNewProduct(Product product){
        productsService.addNewProduct(product);
        return "redirect:/index";
    }

    /**
     * GET запрос для удаления товара по {@param id}
     * @param id Id товара
     * @param model ModelView
     * @return Redirect на главную страницу
     * **/
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        try{
            productsService.deleteProductById(id);
        }catch (IllegalArgumentException ex){
            model.addAttribute("msgError", ex.getMessage());
            return "error";
        }
        return "redirect:/index";
    }

    /**
     * GET запрос для изменения цены товара по {@param id}
     * @param id Id товара
     * @param sign +1 или -1 (для инкр./декр. цены товара)
     * @return Redirect на главную страницу
     * **/
    @GetMapping("/set/price")
    public String changeProductPriceBySign(Long id, Long sign, Model model) {
        try{
            productsService.changeProductPriceByIdWithSign(id, sign);
        }catch (IllegalArgumentException ex){
            model.addAttribute("msgError", ex.getMessage());
            return "error";
        }
        return "redirect:/index";
    }

    /**
     * GET запрос для показа инфо о товаре по {@param id}
     * @param value Id товара
     * @return Страницу с описанием продукта (product.html)
     * **/
    @GetMapping("/filter/id")
    public String showProductById(Long value, Model model) {
        Optional<Product> product = productsService.getProductById(value);
        if (product.isEmpty()){
            model.addAttribute("msgError", "Продукт с ID:" + value + " не найден!");
            return "error";
        }
        model.addAttribute("product", product.get());
        return "product";
    }

    /**
     * GET запрос для показа товаров с категорией {@param value}
     * @param value Категория товара
     * @param pageIndex Индекс запрашиваемой страницы
     * @return Главную страницу (index.html)
     * **/
    @GetMapping("/filter/category")
    public String showProductsListWithCategoryFilter(
            @RequestParam(defaultValue = "") String value,
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            Model model) {
        try{
            Page<Product> page = productsService.getAllProductsByCategory(value, pageIndex - 1, PAGE_SIZE);
            model.addAttribute("page", page);
        }catch (IllegalArgumentException ex){
            model.addAttribute("msgError", ex.getMessage());
            return "error";
        }
        return "index";
    }

    /**
     * Выводит страницу с листом продуктов отфильтрованных по определенной цене
     * @param minPrice Минимальная запрашиваемая цена
     * @param maxPrice Максимальная запрашиваемая цена
     * @param pageIndex Индекс запрашиваемой страницы
     * @return Главную страницу (index.html)
     * **/
    @GetMapping("/filter/price")
    public String showProductsListWithPriceFilter(
            @RequestParam(name = "min", defaultValue = "0") Long minPrice,
            @RequestParam(name = "max", defaultValue = "-1") Long maxPrice,
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            Model model){
        try{
            model.addAttribute("page", productsService.getAllProductsByPrice(minPrice, maxPrice, pageIndex - 1, PAGE_SIZE));
        }catch (IllegalArgumentException ex){
            model.addAttribute("msgError", ex.getMessage());
            return "error";
        }
        return "index";
    }

    /**
     * Поиск продуктов по заданному шаблону
     * @param name Шаблон поиска. Сервис оборачивает в %name%
     * @param pageIndex Индекс запрашиваемой страницы
     * @return Главную страницу (index.html)
     * **/
    @GetMapping("/filter/name")
    public String showProductsByName(
            @RequestParam(name = "value", defaultValue = "*") String name,
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            Model model){
        try{
            model.addAttribute("page", productsService.getAllProductsByName(name, pageIndex - 1, PAGE_SIZE));
        }catch (IllegalArgumentException ex){
            model.addAttribute("msgError", ex.getMessage());
            return "error";
        }
        return "index";
    }
}
