package Graduated.Task.C2C.Category.Service;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.Category.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findCategory(Long no) throws Exception {
        return categoryRepository.findByNo(no).orElseThrow(()-> new Exception("존재하지않는 카테고리입니다."));
    }
}
