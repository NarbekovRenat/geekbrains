package ru.geekbrains.lesson3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson3.models.Product;
import ru.geekbrains.lesson3.services.ProductsService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private ProductsService productsService;

    @Autowired
    public MainController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * Выводит страницу с листом продуктов в корзине
     * @see Product
     * **/
    @GetMapping("/index")
    public String getAllProductsList(Model model){
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
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
    public String deleteUser(@PathVariable("id") long id, Model model) {
        try{
            productsService.deleteProductById(id);
        }catch (IllegalArgumentException ex){
            model.addAttribute("msgError", ex.getMessage());
            return "error";
        }
        return "redirect:/index";
    }

    @GetMapping("/set/cost/{id}")
    public String setProductCost(@PathVariable("id") long id, long value, Model model) {
        try{
            productsService.setProductCostById(id, value);
        }catch (IllegalArgumentException ex){
            model.addAttribute("msgError", ex.getMessage());
            return "error";
        }
        return "redirect:/index";
    }

    @GetMapping("/show")
    public String showProductById(@RequestParam(required = true) long id, Model model) {
        Optional<Product> product = productsService.getProductById(id);
        if (product.isEmpty()){
            model.addAttribute("msgError", "Продукт с ID:" + id + " не найден!");
            return "error";
        }
        model.addAttribute("product", product.get());
        return "product";
    }
}
