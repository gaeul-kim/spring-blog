package xyz.sangsik.blog.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.sangsik.blog.util.BooleanToStringConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sangsik on 2017-12-14.
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
public class Post {

    public Post(Category category, String title, String content, User writer) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank
    @Length(max = 100)
    private String title;

    @NotBlank
    @Length(max = 5000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @Convert(converter = BooleanToStringConverter.class)
    private boolean isDeleted;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    private int viewCount;

    public void increaseViewCount() {
        this.viewCount++;
    }
}
