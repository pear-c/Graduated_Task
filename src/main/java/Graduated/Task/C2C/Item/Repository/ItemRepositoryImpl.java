package Graduated.Task.C2C.Item.Repository;

import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Graduated.Task.C2C.Category.Entity.QCategory.category;
import static Graduated.Task.C2C.Item.Entity.QItem.item;
import static Graduated.Task.C2C.User.Entity.QUser.user;


@Repository
@Getter
public class ItemRepositoryImpl extends Querydsl4RepositorySupport implements ItemRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        super(Item.class);
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Item> findByCategoryItem(Long categoryNo) {
        return select(item).from(category).join(category.item, item).fetchJoin().where(category.No.eq(categoryNo)).fetch();
    }

    @Override
    public List<Item> findBySellerItem(Long userNo) {
        return select(item).from(user).join(user.sellItem,item).fetchJoin().where(user.no.eq(userNo),item.type.eq(Item.State.sale)).fetch();
    }

    @Override
    public List<Item> findBySoldItem(Long userNo) {
        return select(item).from(user).join(user.sellItem,item).fetchJoin().where(user.no.eq(userNo),item.type.eq(Item.State.sold)).fetch();
    }

    @Override
    public List<Item> findByBuyerItem(Long userNo) {
        return select(item).from(user).join(user.buyItem,item).fetchJoin().where(user.no.eq(userNo)).fetch();
    }

}
