package Graduated.Task.C2C.Item.Entity;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.User.Entity.Users;
import Graduated.Task.C2C.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore //개발 과정에서만 사용 , 추후 DTO 변환과정에서는 삭제예정
    private Users seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_user_no")
    @JsonIgnore//개발 과정에서만 사용 , 추후 DTO 변환과정에서는 삭제예정
    private Users buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryNo")
    @JsonIgnore//개발 과정에서만 사용 , 추후 DTO 변환과정에서는 삭제예정
    private Category category;

    public Item(String name, int price, Users seller, Category category) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.type = State.sale;
        seller.getSellItem().add(this);
        category.getItem().add(this);
    }

    public enum State{
        sold,sale
    }

    public void setSold(Users seller, Users buyer) {
        this.seller = seller;
        this.buyer = buyer;
        this.type = State.sold;
        buyer.getBuyItem().add(this);
    }
}
