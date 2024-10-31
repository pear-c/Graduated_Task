package Graduated.Task.C2C.User.Repository;

import Graduated.Task.C2C.User.Entity.User;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static Graduated.Task.C2C.User.Entity.QUser.user;


@Repository
@Getter
public class UserRepositoryImpl extends Querydsl4RepositorySupport implements UserRepositoryCustom{
    private final JPAQueryFactory query;
    public UserRepositoryImpl(EntityManager em) {
        super(User.class);
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(selectFrom(user).where(user.id.eq(userId)).fetchOne());
    }
}
