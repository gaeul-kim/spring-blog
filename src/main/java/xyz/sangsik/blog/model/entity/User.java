package xyz.sangsik.blog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import xyz.sangsik.blog.model.BaseTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sangsik on 2017-12-14.
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class User extends BaseTime {

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();

    private int loginCount;

    public void increaseLoginCount() {
        this.loginCount++;
    }
}
