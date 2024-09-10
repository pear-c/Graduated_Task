package Graduated.Task.C2C.Item.Entity;

import Graduated.Task.C2C.BaseEntity;
import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.User.Entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemNo")
    private Long No;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @Enumerated(EnumType.STRING)
    private State type;

    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_user_no")
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_user_no")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryNo")
    private Category category;

    public enum State{
        sold,sale
    }


}
