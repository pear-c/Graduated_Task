package Graduated.Task.C2C.Item.Service;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.Category.Repository.CategoryRepository;
import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.Item.Repository.ItemRepository;
import Graduated.Task.C2C.User.Entity.Users;
import Graduated.Task.C2C.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public String addItem(String name, int price, String userId, Long categoryNo) throws Exception {
        Users user = userRepository.findByUserId(userId).orElseThrow(()->new Exception("존재하지않는 사용자입니다"));
        Category category = categoryRepository.findByNo(categoryNo).orElseThrow(()->new Exception("존재하지않는 카테고리입니다."));
        Item item = new Item(name,price,user,category);
        itemRepository.save(item);
        return "추가완료";
    }

    public String SellItem(Long userNo, Long itemNo) throws Exception {
        Users buyer = userRepository.findById(userNo).orElseThrow(()->new Exception("존재하지않는 사용자입니다"));
        Item item = itemRepository.findById(itemNo).orElseThrow(()->new Exception("존재하지않는 아이템입니다"));
        Users seller = item.getSeller();
        item.setSold(seller,buyer);
        return "판매완료";
    }

    public List<Item> viewCategoryItem(Long categoryNo) throws Exception {
        Category category = categoryRepository.findByNo(categoryNo).orElseThrow(()-> new Exception("존재하지않는 카테고리입니다."));
        return category.getItem();

    }

}
