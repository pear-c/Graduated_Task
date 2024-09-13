package Graduated.Task.C2C.Item.Repository;

import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class ItemRepositoryImpl extends Querydsl4RepositorySupport implements ItemRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        super(Item.class);
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
}
