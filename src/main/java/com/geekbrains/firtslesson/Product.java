package com.geekbrains.firtslesson;


import java.util.ArrayList;
import java.util.List;

public class Product {
    public static final Product[] PRODUCTS = {
            new Product(1, "Mandarins",10),
            new Product(2, "Coco",20),
            new Product(3, "Grape",25),
            new Product(4, "Banana",45),
            new Product(5, "Cheburek",78),
            new Product(6, "Fish",80),
            new Product(7, "Cherry",500),
            new Product(8, "Milk",50),
            new Product(9, "Kvas",65),
            new Product(10, "Herring",90),
            new Product(11, "Potato",25),
            new Product(12, "Carrot",23),
            new Product(13, "Pilaf",320),
            new Product(14, "Onion",71),
            new Product(15, "Garlic",63),
    };

    private int id;
    private String title;
    private int cost;

    public Product(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  "<ul><li>id=" + id + "</li>" +
                "<li>Name=" + title + "</li>" +
                "<li>Cost=" + cost + "</li></ul>";
    }

    /**
     * @param size Количество продуктов
     * @return Лист с заданным количеством продуктов
     * **/
    public static List<Product> getListByVolume(int size){
        ArrayList<Product> prodlist = new ArrayList<>();
        for (int i = 0; i < size; i++){
            prodlist.add(PRODUCTS[i]);
        }
        return prodlist;
    }
}
