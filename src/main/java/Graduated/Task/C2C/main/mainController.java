package Graduated.Task.C2C.main;

import Graduated.Task.C2C.Category.Dto.categoryDto;
import Graduated.Task.C2C.Category.Service.CategoryService;
import Graduated.Task.C2C.Item.Dto.ItemDto;
import Graduated.Task.C2C.Item.Service.ItemService;
import Graduated.Task.C2C.core.ErrorMessage;
import Graduated.Task.C2C.core.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class mainController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    @GetMapping("/main")
    public ResponseEntity<?> home(){
        try {
            List<ItemDto> popularItem = itemService.findPopularItem();
            List<ItemDto> recentItem = itemService.findRecentItem();
            List<categoryDto> category = categoryService.findCategory();
            MainDto mainDto = new MainDto(category, popularItem, recentItem);
            Message<MainDto> message = Message.of(200, mainDto);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorMessage errorMessage = ErrorMessage.of(500, "다시 시도해주십시오");
            return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
