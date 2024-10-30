package Graduated.Task.C2C;

import Graduated.Task.C2C.User.Entity.User;
import Graduated.Task.C2C.User.Repository.UserRepository;
import Graduated.Task.C2C.User.Service.UserService;
import Graduated.Task.C2C.User.responseDto.userInfoDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class user_Test {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EntityManager em;

	@Test
	void login() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		em.clear();
		String login = userService.login("1", "1");
	}
	@Test
	void logout() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		em.clear();
		String login = userService.login("1", "1");
		em.clear();
		userService.logout(login);
	}

	@Test
	void userinfo() throws Exception {
		User user = new User("1","1","1");
		userRepository.save(user);
		em.clear();
		String login = userService.login("1", "1");
		em.clear();
		userInfoDto userinfo = userService.userinfo(login);
		System.out.println(userinfo);
	}

}

