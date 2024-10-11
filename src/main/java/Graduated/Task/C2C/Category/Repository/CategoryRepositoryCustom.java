package Graduated.Task.C2C.Category.Repository;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.Item.Entity.Item;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    List<Item> findCategoryWithItem(Long categoryNo,final int startPage, final int PageSize);
}
