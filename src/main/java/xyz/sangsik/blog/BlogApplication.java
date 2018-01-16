package xyz.sangsik.blog;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.repository.PostRepository;
import xyz.sangsik.blog.repository.UserRepository;

@SpringBootApplication
public class BlogApplication {
    @Component
    class DummyData implements CommandLineRunner {


        private final PostRepository postRepository;
        private final UserRepository userRepository;

        @Autowired
        public DummyData(PostRepository postRepository, UserRepository userRepository) {
            this.postRepository = postRepository;
            this.userRepository = userRepository;
        }

        @Override
        public void run(String... args) throws Exception {

            User[] users = {
                    new User("sangsik", "abcd11111!")
                    , new User("sion", "aaaa22222!")
                    , new User("iuu", "bbbb33333!")
            };
            for (User u : users) {
                userRepository.save(u);
            }

            Category category = null;
            Lorem lorem = LoremIpsum.getInstance();

            for (int i = 1; i <= 500; i++) {

                switch (i % 3) {
                    case 0:
                        category = Category.IT;
                        break;
                    case 1:
                        category = Category.PROGRAMMING;
                        break;
                    case 2:
                        category = Category.TRAVEL;
                        break;
                }
                postRepository.save(new Post(category
                        , lorem.getTitle(1, 10)
                        , lorem.getParagraphs(1, 5)
                        , userRepository.findOne((long) (Math.random() * 3 + 1))));
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
