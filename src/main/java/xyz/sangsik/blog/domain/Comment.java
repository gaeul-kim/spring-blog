package xyz.sangsik.blog.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Comment extends BaseTime {

    public Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
    }


    @Id
    @GeneratedValue
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
