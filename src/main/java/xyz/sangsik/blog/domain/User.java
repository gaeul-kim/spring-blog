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
import org.springframework.util.StringUtils;

import javax.annotation.RegEx;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Pattern(message = "3~15자의 영문, 숫자, 특수기호(_)만 가능합니다.", regexp = "[A-Za-z0-9_]{3,15}")
    private String name;

    @NotBlank
    @Pattern(message = "5~15자의 영문, 숫자나 특수문자가 필요합니다.", regexp = "(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{5,15}")
    private String password;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    private int loginCount;

    public void increaseLoginCount() {
        this.loginCount++;
    }
}
