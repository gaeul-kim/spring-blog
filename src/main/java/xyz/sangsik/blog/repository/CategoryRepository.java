package xyz.sangsik.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.sangsik.blog.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
