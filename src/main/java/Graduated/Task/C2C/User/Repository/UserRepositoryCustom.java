package Graduated.Task.C2C.User.Repository;

import Graduated.Task.C2C.User.Entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByUserId(String userId);
}
