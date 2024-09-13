package Graduated.Task.C2C.User.Repository;

import Graduated.Task.C2C.User.Entity.Users;
import Graduated.Task.C2C.core.Querydsl4RepositorySupport;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<Users,Long> , UserRepositoryCustom {
}
