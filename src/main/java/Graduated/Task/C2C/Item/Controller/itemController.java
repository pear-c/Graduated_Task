package Graduated.Task.C2C.Item.Controller;

import Graduated.Task.C2C.Item.Dto.ItemDetailDto;
import Graduated.Task.C2C.Item.Dto.ItemDto;
import Graduated.Task.C2C.Item.Dto.itemRequestDto;
import Graduated.Task.C2C.Item.Dto.joinItemDto;
import Graduated.Task.C2C.Item.Service.ItemService;
import Graduated.Task.C2C.core.ErrorMessage;
import Graduated.Task.C2C.core.JwtTokenUtil;
import Graduated.Task.C2C.core.Message;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class itemController {
    private final ItemService itemService;
    private final JwtTokenUtil jwtTokenUtil;
    @GetMapping(value = {"/api/{categoryNo}/{page}"})
    public ResponseEntity<?> CategoryItem(@PathVariable("categoryNo") Long categoryNo, @PathVariable("page") int page) {
        List<ItemDto> itemDtos = itemService.viewCategoryItem(categoryNo, page, 10);
        Message<List<ItemDto>> message = Message.of(200, itemDtos);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = {"/search/{item}/{page}"})
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
    @PostMapping("/item/create")
    public ResponseEntity<?> createItem(@RequestBody joinItemDto joinItemDto, HttpServletRequest request){
        System.out.println(joinItemDto);
        String accessToken = jwtTokenUtil.resolveAccessToken(request);
        String userId = jwtTokenUtil.getclaims(accessToken).getSubject();
        try{
            Long itemNo = itemService.addItem(joinItemDto.getItemName(), joinItemDto.getPrice(), userId, joinItemDto.getCategoryNo(), joinItemDto.getItemState(),
                    joinItemDto.isPriceSimilar());
            Message<itemRequestDto> message = Message.of(201,new itemRequestDto(itemNo,"상품이 성공적으로 수정되었습니다."));
            return new ResponseEntity<>(message,HttpStatus.CREATED);
        }
        catch (NullPointerException e) {
            ErrorMessage errorMessage = ErrorMessage.of(404, e.getMessage());
            return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            ErrorMessage errorMessage = ErrorMessage.of(500, "다시 시도해주십시오");
            return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/item/patch/{itemNo}")
    public ResponseEntity<?> changeItem(@PathVariable("itemNo") Long itemNo, @RequestBody joinItemDto joinItemDto,HttpServletRequest request){
        try{
            itemService.changeItem(itemNo, joinItemDto.getItemName(), joinItemDto.getPrice(), joinItemDto.getCategoryNo(), joinItemDto.getItemState(),
                    joinItemDto.isPriceSimilar());
            Message<itemRequestDto> message = Message.of(200,new itemRequestDto(itemNo,"상품이 성공적으로 수정되었습니다."));
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        catch (NullPointerException e) {
            ErrorMessage errorMessage = ErrorMessage.of(404, e.getMessage());
            return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            ErrorMessage errorMessage = ErrorMessage.of(500, "다시 시도해주십시오");
            return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/item/delete/{itemNo}")
    public ResponseEntity<?> deleteItem(@PathVariable("itemNo") Long itemNo){
        try{
            itemService.deleteItem(itemNo);
            Message<String> message = Message.of(200,"상품이 성공적으로 삭제되었습니다.");
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        catch (NullPointerException e) {
            ErrorMessage errorMessage = ErrorMessage.of(404, e.getMessage());
            return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            ErrorMessage errorMessage = ErrorMessage.of(500, "다시 시도해주십시오");
            return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/userInfo/sellItems")
    public ResponseEntity<?> sellItems(HttpServletRequest request) {
        String accessToken = jwtTokenUtil.resolveAccessToken(request);
        String userId = jwtTokenUtil.getclaims(accessToken).getSubject();
        List<ItemDto> itemDtos = itemService.userSellerItem(userId);
        Message<List<ItemDto>> message = Message.of(200, itemDtos);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PostMapping("/userInfo/buyItems")
    public ResponseEntity<?> buyItems(HttpServletRequest request) {
        String accessToken = jwtTokenUtil.resolveAccessToken(request);
        String userId = jwtTokenUtil.getclaims(accessToken).getSubject();
        List<ItemDto> itemDtos = itemService.userBuyItem(userId);
        Message<List<ItemDto>> message = Message.of(200, itemDtos);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PostMapping("/userInfo/soldItems")
    public ResponseEntity<?> soldItems(HttpServletRequest request) {
        String accessToken = jwtTokenUtil.resolveAccessToken(request);
        String userId = jwtTokenUtil.getclaims(accessToken).getSubject();
        List<ItemDto> itemDtos = itemService.userSoldItem(userId);
        Message<List<ItemDto>> message = Message.of(200, itemDtos);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
