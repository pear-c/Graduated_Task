package Graduated.Task.C2C.Item.Repository;

import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public List<Item> findCategoryWithItem(Long categoryNo,final int startPage, final int PageSize) {
        System.out.println(startPage);
        return select(item).from(item).where(item.category.No.eq(categoryNo)).where(item.type.eq(Item.State.sale)).orderBy(item.createdDate.desc()).offset(startPage)
                .limit(PageSize).fetch();
    }
    public Optional<Item> findItemWithCategory(Long itemId){
        return Optional.ofNullable(selectFrom(item).where(item.No.eq(itemId)).join(item.category).fetchJoin().fetchOne());
    }
    public List<Item> searchItem(String word,final int startPage, final int PageSize){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(word!=null){
            booleanBuilder.and(item.name.like("%" + word + "%"));
        }
        return selectFrom(item).where(booleanBuilder,item.type.eq(Item.State.sale)).offset(startPage).limit(PageSize).fetch();
    }
    public List<Item> findPopularItem(){
        return selectFrom(item).orderBy(item.viewCount.desc()).limit(4).fetch();
    }
    public List<Item> findRecentItem(){
        return selectFrom(item).orderBy(item.createdDate.asc()).limit(4).fetch();
    }


    @Override
    public List<Item> findBySellerItem(String userid) {
        return select(item).from(user).join(user.sellItem,item).fetchJoin().where(user.id.eq(userid),item.type.eq(Item.State.sale)).fetch();
    }

    @Override
    public List<Item> findBySoldItem(String userid) {
        return select(item).from(user).join(user.sellItem,item).fetchJoin().where(user.id.eq(userid),item.type.eq(Item.State.sold)).fetch();
    }

    @Override
    public List<Item> findByBuyerItem(String userid) {
        return select(item).from(user).join(user.buyItem, item).fetchJoin().where(user.id.eq(userid)).fetch();
    }

}
