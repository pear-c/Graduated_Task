package Graduated.Task.C2C.Category.Repository;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.Category.Entity.QCategory;
import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static Graduated.Task.C2C.Category.Entity.QCategory.category;
import static Graduated.Task.C2C.Item.Entity.QItem.item;

@Repository
@Getter
public class CategoryRepositoryImpl extends Querydsl4RepositorySupport implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    public CategoryRepositoryImpl(EntityManager em) {
        super(Category.class);
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public List<Item> findCategoryWithItem(Long categoryNo,final int startPage, final int PageSize) {
        return select(item).from(category).where(category.No.eq(categoryNo)).join(category.item,item).where(item.type.eq(Item.State.sale)).orderBy(item.createdDate.desc()).fetchJoin().offset(startPage)
                .limit(PageSize).fetch();
    }


}
