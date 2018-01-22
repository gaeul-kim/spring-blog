package xyz.sangsik.blog.web.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.repository.CategoryRepository;

@Getter
@Setter
@Component
@NoArgsConstructor
public class PostRequestDto {

    @Autowired
    CategoryRepository categoryRepository;

    private Long category;
    private String title;
    private String content;

    public void bindingRequest(PostRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public Post toEntity() {
        return new Post(categoryRepository.findOne(category), title, content);
    }
}
