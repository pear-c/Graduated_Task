package Graduated.Task.C2C.Category.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class categoryPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryNo")
    private Category category;
}
