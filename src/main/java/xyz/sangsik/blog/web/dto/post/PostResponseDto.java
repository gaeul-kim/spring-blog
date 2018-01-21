package xyz.sangsik.blog.web.dto.post;

import com.youbenzi.mdtool.tool.MDTool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import xyz.sangsik.blog.domain.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String author;
    private String createdDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = (post.getCategory() == null) ? "" : post.getCategory().getName();
        this.author = post.getAuthor().getName();
        this.createdDate = convertDateTime(post.getCreatedDate());
    }

    private String convertDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(localDateTime);
    }

    public String getContent() {
        return MDTool.markdown2Html(this.content);
    }

    public String getContentSummary() {
        int MAX_SUMMARY_LENGTH = 300;

        String plainText = Jsoup.parse(getContent()).text();
        if (plainText.length() > MAX_SUMMARY_LENGTH) {
            plainText = plainText.substring(0, MAX_SUMMARY_LENGTH);
        }
        return plainText;
    }
}
