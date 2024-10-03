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
        return categoryRepository.findById(no).orElseThrow(()-> new Exception("존재하지않는 카테고리입니다."));
    }

    public void addCategory(){
        int minPrice=0;int maxPrice=0; //추후 쉘,프로세스를 통한 파이썬 함수 호출을 통하여 값을 받아올 예정
        Category category = new Category(minPrice,maxPrice);
        categoryRepository.save(category);
    }
    @Transactional
    public void updatePrice(Long no) throws Exception {
        int minPrice = 0;int maxPrice = 0; //추후 쉘,프로세스를 통한 파이썬 함수 호출을 통하여 값을 받아올 예정
        Category category = categoryRepository.findById(no).orElseThrow(()->new Exception("존재하지않는 카테고리입니다."));
        category.setPrice(minPrice,maxPrice);
    }
}
