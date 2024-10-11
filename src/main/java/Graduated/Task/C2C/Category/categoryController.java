package Graduated.Task.C2C.Category;

import Graduated.Task.C2C.Item.Dto.ItemDto;
import Graduated.Task.C2C.Item.Service.ItemService;
import Graduated.Task.C2C.User.responseDto.TokenDto;
import Graduated.Task.C2C.core.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class categoryController {
    private final ItemService itemService;
    @GetMapping("/api/{categoryNo}/{page}")
    public ResponseEntity<?> CategoryItem(@PathVariable("categoryNo") Long categoryNo, @PathVariable("page") int page ){
        List<ItemDto> itemDtos = itemService.viewCategoryItem(categoryNo, page, 10);
        Message<List<ItemDto>> message = Message.of(200, itemDtos);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
