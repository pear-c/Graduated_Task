package Graduated.Task.C2C.User.Entity;


import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.core.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "Users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userNo")
    private Long no;

    @NotNull
    private String id;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "seller")
    List<Item> sellItem = new ArrayList<>();

    @OneToMany(mappedBy = "buyer")
    List<Item> buyItem = new ArrayList<>();

    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}