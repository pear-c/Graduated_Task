package Graduated.Task.C2C;

import Graduated.Task.C2C.Category.Entity.Category;
import Graduated.Task.C2C.Category.Repository.CategoryRepository;
import Graduated.Task.C2C.Category.Service.CategoryService;
import Graduated.Task.C2C.Item.Dto.ItemDetailDto;
import Graduated.Task.C2C.Item.Dto.ItemDto;
import Graduated.Task.C2C.Item.Service.ItemService;
import Graduated.Task.C2C.User.Entity.User;
import Graduated.Task.C2C.User.Repository.UserRepository;
import Graduated.Task.C2C.User.Service.UserService;
import Graduated.Task.C2C.User.responseDto.userInfoDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class itemTest {
	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryService categoryService;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	EntityManager em;

	@Test
	void itemAdd() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		em.clear();
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		System.out.println(l);
	}
	@Test
	void deleteItem() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		em.clear();
		itemService.deleteItem(l);
	}

	@Test
	void changeItem() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		em.clear();
		itemService.changeItem(l,"2",4000, category.getNo(), 3,true);
	}

	@Test
	void categoryItem() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		em.clear();
		List<ItemDto> itemDtos = itemService.viewCategoryItem(l, 0, 10);
		System.out.println(itemDtos);
	}
	@Test
	void searchItem() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		em.clear();
		List<ItemDto> itemDtos = itemService.searchItem("1", 0, 10);
		System.out.println(itemDtos);
	}

	@Test
	void popluarItem() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		em.clear();
		List<ItemDto> itemDetailDto = itemService.findPopularItem();
		System.out.println(itemDetailDto);
	}

	@Test
	void recentItem() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		em.clear();
		categoryRepository.findAllPost();
		List<ItemDto> itemDetailDto = itemService.findRecentItem();
		System.out.println(itemDetailDto);
	}
	@Test
	void Iteminfo() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		Category category = new Category("test",0);
		categoryRepository.save(category);
		Long l = itemService.addItem("1", 3000, user.getId(), category.getNo(), 2, true);
		em.clear();
		categoryRepository.findAllPost();
		ItemDetailDto itemDetailDto = itemService.itemInformation(l);
		System.out.println(itemDetailDto);
	}


}

