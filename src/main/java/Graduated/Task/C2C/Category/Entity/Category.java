package Graduated.Task.C2C.Category.Entity;

import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.core.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryNo")
    private Long No;
    private int minPrice;
    private int maxPrice;

    @OneToMany(mappedBy = "category")
    private List<Item> item = new ArrayList<>();

    public Category(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    public void setPrice(int minPrice,int maxPrice){
        this.minPrice=minPrice;
        this.maxPrice=maxPrice;
    }
}
