package Graduated.Task.C2C.Category.Repository;

import Graduated.Task.C2C.Category.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>,CategoryRepositoryCustom {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO category_price (max_price, min_price, status, category_no, id)" +
            "VALUES " +
            "    (879097, 586064, 5, 1, 11)," +
            "    (814445, 542963, 4, 1, 12)," +
            "    (765314, 510209, 3, 1, 13)," +
            "    (760524, 507016, 2, 1, 14)," +
            "    (584241, 389494, 1, 1, 15)", nativeQuery = true)
    int findAllPost();
}