package Graduated.Task.C2C.Item.Repository;

import Graduated.Task.C2C.Category.Entity.QCategory;
import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.Item.Entity.QItem;
import Graduated.Task.C2C.User.Entity.QUsers;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Graduated.Task.C2C.Category.Entity.QCategory.category;
import static Graduated.Task.C2C.Item.Entity.QItem.item;
import static Graduated.Task.C2C.User.Entity.QUsers.users;

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
        return select(item).from(users).join(users.sellItem,item).fetchJoin().where(users.no.eq(userNo)).fetch();
    }

    @Override
    public List<Item> findByBuyerItem(Long userNo) {
        return select(item).from(users).join(users.buyItem,item).fetchJoin().where(users.no.eq(userNo)).fetch();
    }
}
