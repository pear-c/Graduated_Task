package Graduated.Task.C2C.Item.Repository;

import Graduated.Task.C2C.Item.Entity.Item;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> findByCategoryItem(Long categoryNo);
    List<Item> findBySellerItem(Long userNo);
    List<Item> findByBuyerItem(Long userNo);

}
