package Graduated.Task.C2C.Item.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class joinItemDto {
    private String images;
    private String itemName;
    private int price;
    private Long categoryNo;
    private int itemState;
    private boolean priceSimilar;

}
