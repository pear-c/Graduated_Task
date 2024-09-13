package Graduated.Task.C2C.Category.Repository;

import Graduated.Task.C2C.Category.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>,CategoryRepositoryCustom {
}
