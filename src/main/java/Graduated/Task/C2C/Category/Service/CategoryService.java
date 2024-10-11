package Graduated.Task.C2C.Category.Service;

import Graduated.Task.C2C.Category.Repository.CategoryRepository;
import Graduated.Task.C2C.Item.Dto.ItemDto;
import Graduated.Task.C2C.Item.Entity.Item;
import Graduated.Task.C2C.Item.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final ItemService itemService;

}
