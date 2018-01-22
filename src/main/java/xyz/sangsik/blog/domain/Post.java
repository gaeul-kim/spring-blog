package xyz.sangsik.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.sangsik.blog.service.CategoryService;
import xyz.sangsik.blog.util.BooleanToStringConverter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sangsik on 2017-12-14.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post extends BaseTime {

    public Post(Category category, String title, String content, User author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post(Category category, String title, String content) {
        this.category = category;
        this.title = title;
        this.content = content;
    }

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank
    @Length(max = 100)
    private String title;

    @Lob
    @NotBlank
    @Length(max = 10000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Convert(converter = BooleanToStringConverter.class)
    private boolean isDeleted;

    private int viewCount;

    public void increaseViewCount() {
        this.viewCount++;
    }

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private List<Comment> comments;
}
