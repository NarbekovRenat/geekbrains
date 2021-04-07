package com.geekbrains.entity;

import com.geekbrains.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private ProductRepository productRepository;
    private List<Product> productInCart;

    @Autowired
    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Add product to Cart
     * if {@param id} not found -> ignoring
     * @param id ID product
     * See also {@link ProductRepository}
     * */
    public void addProduct(int id){
        Product prod = productRepository.getProductById(id);
        if (prod != null)
            this.productInCart.add(prod);
    }

    /**
     * Remove product from Cart
     * @param id ID product
     * See also {@link ProductRepository}
     * */
    public void deleteProduct (int id){
        this.productInCart.remove(productRepository.getProductById(id));
    }

    /**
     * Get ALL products from Cart
     * See {@link Cart}
     * */
    public List<Product> getProductInCart(){
        return productInCart;
    }

    @PostConstruct
    private void init(){
        this.productInCart = new ArrayList<>();
    }
}
