package ru.geekbrains.aprilmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.aprilmarket.models.Product;
import ru.geekbrains.aprilmarket.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class V1ProductsRestController {
    private final ProductService productService;

    /**
     * Получить список всех продуктов
     * @return ResponseEntity<Product> + HttpStatus
     * **/
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получить список продукта по id
     * @param id ID продукта
     * @return ResponseEntity<Product> + HttpStatus
     * **/
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id){
        Optional<Product> product = productService.getProductById(id);
        return product.isPresent()
                ? new ResponseEntity<>(product.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавить продукт
     * @param product Добавляемый продукт (без id)
     * @return ResponseEntity<Product> + HttpStatus
     * **/
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Optional<Product> savedProduct = productService.saveProduct(product);
        return savedProduct.isPresent()
                ? new ResponseEntity<>(savedProduct.get(), HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Изменить существующий продукт
     * @param id ID существующего продукта
     * @param editedProduct Новый объект продукта
     * @return ResponseEntity<Product> + HttpStatus
     * **/
    @PutMapping("/{id}")
    public ResponseEntity<Product> setProductById(@PathVariable(name = "id") Long id, @RequestBody Product editedProduct){
        Optional<Product> product = productService.setProductById(id, editedProduct);
        return product.isPresent()
                ? new ResponseEntity<>(product.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Удалить продукт по ID
     * @param id ID удаляемого продукта
     * @return ResponseEntity<Product> + HttpStatus
     * **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable(name = "id") Long id){
        try{
            productService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
