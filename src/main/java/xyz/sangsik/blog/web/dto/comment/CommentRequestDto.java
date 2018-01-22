package xyz.sangsik.blog.web.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.sangsik.blog.domain.Comment;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.repository.PostRepository;

@NoArgsConstructor
@Getter
@Setter
@Component
public class CommentRequestDto {
    @Autowired
    PostRepository postRepository;

    private Long postId;
    private String content;
    private User author;

    public void bindingRequest(CommentRequestDto dto) {
        this.content = dto.getContent();
        this.postId = dto.getPostId();
    }

    public Comment toEntity() {
        return new Comment(this.content, this.author, this.postRepository.findOne(this.postId));
    }
}
