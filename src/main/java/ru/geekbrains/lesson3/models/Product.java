package ru.geekbrains.lesson3.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Product {
    public static final Long MIN_COST = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long cost;

    public void incrementCost(){
        this.cost++;
    }

    public void decrementCost(){
        if (this.cost > MIN_COST)
            this.cost--;
    }
}
