package xyz.sangsik.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.domain.Category;
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
                    new User("sangsik", "1111", "김상식")
                    , new User("sion", "2222", "송시온")
                    , new User("iu", "3333", "이지은")
            };
            for (User u : users) {
                userRepository.save(u);
            }

            Category category=Category.QUESTION;

            for (int i = 1; i <= 500; i++) {
/*
                switch (i % 3) {
                    case 0:
                        category = Category.NOTICE;
                        break;
                    case 1:
                        category = Category.QUESTION;
                        break;
                    case 2:
                        category = Category.MARKET;
                        break;
                }
*/


                postRepository.save(new Post(category
                        , "질문" + i
                        , "질문내용" + i
                        , userRepository.findOne((long) (Math.random() * 3 + 1))));
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
