package xyz.sangsik;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Autowired
	UserService userService;

	@Before
	public void init() {
		User user = new User();
		user.setName("sangsik");
		user.setPassword("1234");
		userService.add(user);
	}


	@Test
	public void contextLoads() {
	}

}
