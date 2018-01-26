package xyz.sangsik.blog.model.ResponseObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import xyz.sangsik.blog.model.entity.Comment;
import xyz.sangsik.blog.util.DateTimeConverter;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Getter
public class CommentResponse {

    private Long id;
    private String content;
    private String author;
    private String createdDate;

    public CommentResponse(Comment comment) {
        this.createdDate = DateTimeConverter.convertDateTime(comment.getCreatedDate());
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor().getName();
    }
}
