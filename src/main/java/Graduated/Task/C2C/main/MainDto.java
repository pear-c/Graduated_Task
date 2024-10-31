package Graduated.Task.C2C.main;

import Graduated.Task.C2C.Category.Dto.categoryDto;
import Graduated.Task.C2C.Item.Dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MainDto {
    private List<categoryDto> categories;
    private List<ItemDto> popularItems;
    private List<ItemDto> recentItems;
}
