package Graduated.Task.C2C.Item.Dto;

import lombok.Data;

@Data
public class ItemDetailDto {
    private Long itemId;
    private String images;
    private String itemName;
    private int price;
    private String category;
    private int minPrice;
    private int maxPrice;
    private int itemState;

    public ItemDetailDto(Long itemId, String image,String itemName, int price, String category, int minPrice, int maxPrice, int itemState) {
        this.itemId = itemId;
        this.images = image;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.itemState = itemState;
    }


}
