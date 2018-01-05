package xyz.sangsik.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
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
@ToString
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
public class User {

    public User(String name, String password, String nickname) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(max = 10)
    private String name;

    @NotBlank
    @Length(min = 5, max = 20)
    private String password;

    @NotBlank
    @Length(max = 15)
    private String nickname;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    private int loginCount;

    public void increaseLoginCount() {
        this.loginCount++;
    }
}
