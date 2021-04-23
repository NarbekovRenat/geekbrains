package ru.geekbrains.lesson6.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    public static final Long MIN_COST = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Category category;

    public void incrementCost(){
        this.price++;
    }

    public void decrementCost(){
        if (this.price > MIN_COST)
            this.price--;
    }
}
