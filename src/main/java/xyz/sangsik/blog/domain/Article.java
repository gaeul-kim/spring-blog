package xyz.sangsik.blog.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sangsik on 2017-12-14.
 */
@Getter
@Setter
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    private Integer viewCount = 0;

    public void increaseViewCount() {
        this.viewCount++;
    }
}
