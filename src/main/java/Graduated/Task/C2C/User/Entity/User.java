package Graduated.Task.C2C.User.Entity;


import Graduated.Task.C2C.Item.Entity.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
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


}