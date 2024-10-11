package Graduated.Task.C2C.Item;

import Graduated.Task.C2C.Item.Dto.ItemDetailDto;
import Graduated.Task.C2C.Item.Dto.ItemDto;
import Graduated.Task.C2C.Item.Service.ItemService;
import Graduated.Task.C2C.core.ErrorMessage;
import Graduated.Task.C2C.core.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class itemController {
    private final ItemService itemService;
    @GetMapping(value = {"/api/{categoryNo}/{page}","/api/{categoryNo}"})
    public ResponseEntity<?> CategoryItem(@PathVariable("categoryNo") Long categoryNo, @PathVariable("page") Integer page ){
        if(page==null) {
            page = 1;
        }
        List<ItemDto> itemDtos = itemService.viewCategoryItem(categoryNo, page, 10);
        Message<List<ItemDto>> message = Message.of(200, itemDtos);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = {"/search/{item}/{page}","/search/{item}"})
    public ResponseEntity<?> SearchItem(@PathVariable("item") String word,@PathVariable("page") Integer page){
        if(page==null) {
            page = 1;
        }
        List<ItemDto> itemDtos = itemService.searchItem(word, page, 10);
        Message<List<ItemDto>> message = Message.of(200, itemDtos);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/itemInfo/{ItemNo}")
    public ResponseEntity<?> itemDetail(@PathVariable("ItemNo") Long itemNo){
        try {
            ItemDetailDto itemDetailDto = itemService.itemInformation(itemNo);
            Message<ItemDetailDto> itemDetailDtoMessage = Message.of(200, itemDetailDto);
            return new ResponseEntity<>(itemDetailDtoMessage,HttpStatus.OK);
        }
        catch (NullPointerException e){
            ErrorMessage errorMessage = ErrorMessage.of(404, e.getMessage());
            return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
        }
        catch (NoSuchElementException e){
            ErrorMessage errorMessage = ErrorMessage.of(401, e.getMessage());
            return new ResponseEntity<>(errorMessage,HttpStatus.UNAUTHORIZED);}
        catch (Exception e){
            ErrorMessage errorMessage = ErrorMessage.of(500, "다시 시도해주십시오");
            return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
