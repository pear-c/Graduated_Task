package Graduated.Task.C2C.Category.Repository;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.Category.Entity.categoryPrice;
import Graduated.Task.C2C.Item.Entity.Item;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    Optional<categoryPrice> findCategoryPrice(Long categoryNo, int state);
}
