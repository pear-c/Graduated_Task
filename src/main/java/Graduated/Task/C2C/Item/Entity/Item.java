package Graduated.Task.C2C.Item.Entity;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.User.Entity.User;
import Graduated.Task.C2C.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
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

    private String image;
    private Boolean priceSimilar;

    private int itemState;
    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_user_no")
    @JsonIgnore //개발 과정에서만 사용 , 추후 DTO 변환과정에서는 삭제예정
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_user_no")
    @JsonIgnore//개발 과정에서만 사용 , 추후 DTO 변환과정에서는 삭제예정
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryNo")
    @JsonIgnore//개발 과정에서만 사용 , 추후 DTO 변환과정에서는 삭제예정
    private Category category;

    public Item(String name, String image,int price, User seller, Category category,int itemState,boolean priceSimilar) {
        this.name = name;
        this.image=image;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.type = State.sale;
        this.itemState = itemState;
        this.priceSimilar=priceSimilar;
        seller.getSellItem().add(this);
        category.getItem().add(this);
    }
    public void plusView(){
        this.viewCount+=1;
    }
    public void changeItem(String name, int price, Boolean priceSimilar, int itemState, Category category) {
        this.name = name;
        this.image="";
        this.price = price;
        this.priceSimilar = priceSimilar;
        this.itemState = itemState;
        this.category = category;

    }

    public enum State{
        sold,sale
    }

    public void setSold(User seller, User buyer) {
        this.seller = seller;
        this.buyer = buyer;
        this.type = State.sold;
        buyer.getBuyItem().add(this);
    }
}
