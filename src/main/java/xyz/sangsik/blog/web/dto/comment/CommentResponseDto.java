package xyz.sangsik.blog.web.dto.comment;

import lombok.Data;
import xyz.sangsik.blog.domain.Comment;

@Data
public class CommentResponseDto {

    private Long id;
    private String content;
    private String author;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor().getName();
    }
}
