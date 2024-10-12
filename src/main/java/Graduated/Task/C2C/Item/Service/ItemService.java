package Graduated.Task.C2C.Item.Service;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.Category.Entity.categoryPrice;
import Graduated.Task.C2C.Category.Repository.CategoryRepository;
import Graduated.Task.C2C.Item.Dto.ItemDetailDto;
import Graduated.Task.C2C.Item.Dto.ItemDto;
import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.Item.Repository.ItemRepository;
import Graduated.Task.C2C.User.Entity.User;
import Graduated.Task.C2C.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long addItem(String name, int price, String userId, Long categoryNo, int itemState, boolean priceSimilar)  {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new NullPointerException("존재하지않는 사용자입니다"));
        Category category = categoryRepository.findById(categoryNo).orElseThrow(()->new NullPointerException("존재하지않는 카테고리입니다."));
        Item item = new Item(name,price,user,category,itemState,priceSimilar);
        itemRepository.save(item);
        return item.getNo();
    }
    @Transactional
    public void deleteItem(Long itemId){
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NullPointerException("존재하지않는 아이템입니다."));
        itemRepository.delete(item);
    }

    @Transactional
    public void SellItem(Long userNo, Long itemNo) throws Exception {
        User buyer = userRepository.findById(userNo).orElseThrow(()->new Exception("존재하지않는 사용자입니다"));
        Item item = itemRepository.findById(itemNo).orElseThrow(()->new Exception("존재하지않는 아이템입니다"));
        User seller = item.getSeller();
        item.setSold(seller,buyer);
    }

    @Transactional
    public void changeItem(Long itemId,String name, int price, Long categoryNo,int itemState,boolean priceSimilar)  {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NullPointerException("존재하지않는 아이템입니다."));
        Category category = categoryRepository.findById(categoryNo).orElseThrow(()->new NullPointerException("존재하지않는 카테고리입니다."));
        item.changeItem(name,price,priceSimilar,itemState,category);
        itemRepository.save(item);
    }

    public List<ItemDto> viewCategoryItem(Long categoryNo, final int startPage, final int PageSize) {
        List<Item> categoryItem = itemRepository.findCategoryWithItem(categoryNo, startPage, PageSize);
        return categoryItem.stream().map(this::getItemDto).toList();
    }
    public List<ItemDto> searchItem(String word,final int startPage, final int PageSize){
        List<Item> searchItem = itemRepository.searchItem(word,startPage, PageSize);
        return searchItem.stream().map(this::getItemDto).toList();
    }
    public ItemDetailDto itemInformation(Long itemId){
        Item item = itemRepository.findItemWithCategory(itemId).orElseThrow(() -> new NullPointerException("존재하지 않는 아이템입니다"));
        int state = item.getItemState();
        categoryPrice categoryPrice = categoryRepository.findCategoryPrice(item.getCategory().getNo(), state).orElseThrow(() -> new NoSuchElementException("잘못된 접근입니다."));
        int maxPrice = categoryPrice.getMaxPrice();
        int minPrice = categoryPrice.getMinPrice();
        String name = item.getCategory().getName();
        return new ItemDetailDto(item.getNo(),item.getName(),item.getPrice(),name,minPrice,maxPrice,item.getItemState());


    }
    private ItemDto getItemDto(Item item) {
        return new ItemDto(item.getNo(),item.getName(),item.getPrice(),item.getPriceSimilar(),item.getCreatedDate());
    }


    public List<Item> AllItem(){
        return itemRepository.findAll();
    }
    public List<Item> userBuyItem(Long userNo){
        return itemRepository.findByBuyerItem(userNo);
    }
    public List<Item> userSellerItem(Long userNo){
        return itemRepository.findBySellerItem(userNo);
    }
    public List<Item> userSoldItem(Long userNo){
        return itemRepository.findBySoldItem(userNo);
    }




}
