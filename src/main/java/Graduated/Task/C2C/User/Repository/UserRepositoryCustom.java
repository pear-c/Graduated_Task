package Graduated.Task.C2C.User.Repository;

import Graduated.Task.C2C.User.Entity.Users;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<Users> findByUserEmail(String email);
}
