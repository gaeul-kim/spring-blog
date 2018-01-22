package xyz.sangsik.blog.web.dto.comment;

import lombok.Data;
import xyz.sangsik.blog.domain.Comment;

@Data
public class CommentResponseDto {

    private Long id;
    private String content;
    private String author;

    public CommentResponseDto(Comment c) {
        this.id = c.getId();
        this.content = c.getContent();
        this.author = c.getAuthor().getName();
    }
}
