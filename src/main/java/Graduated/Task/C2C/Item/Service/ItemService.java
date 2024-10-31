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
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long addItem(String name, String image,int price, String userId, Long categoryNo, int itemState, boolean priceSimilar) throws Exception {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new NullPointerException("존재하지않는 사용자입니다"));
        Category category = categoryRepository.findById(categoryNo).orElseThrow(()->new NullPointerException("존재하지않는 카테고리입니다."));
        Item item = new Item(name,image,price,user,category,itemState,priceSimilar);
        itemRepository.save(item);
        if (category.getItemCount()%20 ==0 ){
            String category_no = String.valueOf(category.getNo());
            Future<ProcessResult> python3 = new ProcessExecutor().command("python3", "/home/ubuntu/Random_Forest_Model.py",category_no)
                    .redirectOutput(System.out)
                    .redirectError(System.out).start().getFuture();
        }
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
        List<Item> categoryItem = itemRepository.findCategoryWithItem(categoryNo, startPage*10, PageSize);
        return categoryItem.stream().map(this::getItemDto).toList();
    }
    public List<ItemDto> searchItem(String word,final int startPage, final int PageSize){
        List<Item> searchItem = itemRepository.searchItem(word,startPage*10, PageSize);
        return searchItem.stream().map(this::getItemDto).toList();
    }
    @Transactional
    public ItemDetailDto itemInformation(Long itemId){
        Item item = itemRepository.findItemWithCategory(itemId).orElseThrow(() -> new NullPointerException("존재하지 않는 아이템입니다"));
        item.plusView();
        int state = item.getItemState();
        categoryPrice categoryPrice = categoryRepository.findCategoryPrice(item.getCategory().getNo(), state).orElseThrow(() -> new NoSuchElementException("잘못된 접근입니다."));
        int maxPrice = categoryPrice.getMaxPrice();
        int minPrice = categoryPrice.getMinPrice();
        String name = item.getCategory().getName();
        return new ItemDetailDto(item.getNo(),item.getImage(),item.getName(),item.getPrice(),name,minPrice,maxPrice,item.getItemState());
    }
    private ItemDto getItemDto(Item item) {
        return new ItemDto(item.getNo(),item.getImage(),item.getName(),item.getPrice(),item.getPriceSimilar(),item.getCreatedDate());
    }
    public List<ItemDto> findPopularItem(){
        return itemRepository.findPopularItem().stream().map(this::getItemDto).toList();
    }
    public List<ItemDto> findRecentItem(){
        return itemRepository.findPopularItem().stream().map(this::getItemDto).toList();
    }

    public List<Item> AllItem(){
        return itemRepository.findAll();
    }
    public List<ItemDto> userBuyItem(String userId){
        return itemRepository.findByBuyerItem(userId).stream().map(this::getItemDto).toList();
    }
    public List<ItemDto> userSellerItem(String userNo){
        return itemRepository.findBySellerItem(userNo).stream().map(this::getItemDto).toList();
    }
    public List<ItemDto> userSoldItem(String userNo){
        return itemRepository.findBySoldItem(userNo).stream().map(this::getItemDto).toList();
    }
}
