package xyz.sangsik;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.web.dto.post.PostRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = xyz.sangsik.blog.BlogApplication.class)
public class BlogApplicationTests {

    @Autowired
    DefaultListableBeanFactory bf;


}
