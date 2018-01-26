package xyz.sangsik.blog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.sangsik.blog.model.BaseTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Category extends BaseTime {

    public Category(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
