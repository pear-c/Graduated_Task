package Graduated.Task.C2C.User.Repository;

import Graduated.Task.C2C.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> , UserRepositoryCustom {
}
