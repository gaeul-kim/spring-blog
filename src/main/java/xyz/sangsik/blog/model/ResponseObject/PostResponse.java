package xyz.sangsik.blog.model.ResponseObject;

import com.youbenzi.mdtool.tool.MDTool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import xyz.sangsik.blog.model.entity.Post;
import xyz.sangsik.blog.util.DateTimeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String author;
    private String createdDate;
    private int MAX_SUMMARY_LENGTH = 300;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = (post.getCategory() == null) ? "" : post.getCategory().getName();
        this.author = post.getAuthor().getName();
        this.createdDate = DateTimeConverter.convertDateTime(post.getCreatedDate());
    }

    public String getContent() {
        return MDTool.markdown2Html(this.content);
    }

    public String getContentSummary() {
        String plainText = Jsoup.parse(getContent()).text();
        if (plainText.length() > MAX_SUMMARY_LENGTH) {
            plainText = plainText.substring(0, MAX_SUMMARY_LENGTH);
        }
        return plainText;
    }
}
