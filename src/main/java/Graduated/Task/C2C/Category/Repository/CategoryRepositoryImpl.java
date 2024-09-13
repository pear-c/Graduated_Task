package Graduated.Task.C2C.Category.Repository;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class CategoryRepositoryImpl extends Querydsl4RepositorySupport implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    public CategoryRepositoryImpl(EntityManager em) {
        super(Category.class);
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
}
