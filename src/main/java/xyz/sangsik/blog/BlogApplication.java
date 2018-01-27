package xyz.sangsik.blog;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import xyz.sangsik.blog.model.entity.Category;
import xyz.sangsik.blog.model.entity.Post;
import xyz.sangsik.blog.model.entity.Role;
import xyz.sangsik.blog.model.entity.User;
import xyz.sangsik.blog.repository.CategoryRepository;
import xyz.sangsik.blog.repository.PostRepository;
import xyz.sangsik.blog.repository.RoleRepository;
import xyz.sangsik.blog.repository.UserRepository;
import xyz.sangsik.blog.service.UserService;

@SpringBootApplication
public class BlogApplication {
    @Component
    class DummyData implements CommandLineRunner {


        private final PostRepository postRepository;
        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final CategoryRepository categoryRepository;
        private final UserService userService;


        @Autowired
        public DummyData(PostRepository postRepository, UserRepository userRepository, RoleRepository roleRepository, UserService userService, CategoryRepository categoryRepository) {
            this.postRepository = postRepository;
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.userService = userService;
            this.categoryRepository = categoryRepository;
        }

        @Override
        public void run(String... args) throws Exception {

            Role r1 = roleRepository.save(new Role("USER"));

            Category c1 = categoryRepository.save(new Category("java"));
            Category c2 = categoryRepository.save(new Category("html"));
            Category c3 = categoryRepository.save(new Category("javascript"));
            Category c4 = categoryRepository.save(new Category("etc"));

            User[] users = {
                    new User("sangsik", "qwe123")
                    , new User("sion", "qwe123")
                    , new User("iuu", "qwe123")
            };
            for (User u : users) {
                userService.registration(u);
            }

            Lorem lorem = LoremIpsum.getInstance();

            for (int i = 1; i <= 500; i++) {
                postRepository.save(
                        new Post(categoryRepository.findOne((long) (Math.random() * 4 + 1)), lorem.getTitle(1, 10)
                                , lorem.getParagraphs(1, 5)
                                , userRepository.findOne((long) (Math.random() * 3 + 1))));
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
