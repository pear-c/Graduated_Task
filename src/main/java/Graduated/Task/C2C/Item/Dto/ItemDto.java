package Graduated.Task.C2C.Item.Dto;

import Graduated.Task.C2C.Item.Entity.Item;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {
    private Long itemId;

    private String image;

    private String itemName;

    private int price;

    private boolean priceSimilar;

    private LocalDateTime time;

    public ItemDto(Long itemId, String image,String itemName, int price, boolean priceSimilar, LocalDateTime time) {
        this.itemId = itemId;
        this.image=image;
        this.itemName = itemName;
        this.price = price;
        this.priceSimilar = priceSimilar;
        this.time = time;
    }
}
