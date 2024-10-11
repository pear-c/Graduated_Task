package Graduated.Task.C2C.Item.Repository;

import Graduated.Task.C2C.Item.Entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepositoryCustom {
    List<Item> searchItem(String word,final int startPage, final int PageSize);
    List<Item> findCategoryWithItem(Long categoryNo,final int startPage, final int PageSize);
    Optional<Item> findItemWithCategory(Long itemId);
    List<Item> findBySellerItem(Long userNo);
    List<Item> findByBuyerItem(Long userNo);

    List<Item> findBySoldItem(Long userNo);
}
