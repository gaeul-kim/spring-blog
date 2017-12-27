package xyz.sangsik.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    @GeneratedValue
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;


    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
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
