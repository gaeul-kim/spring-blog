package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category get(Long id) {
        return categoryRepository.findOne(id);
    }

    public Category get(String name) {
        return categoryRepository.findByName(name);
    }

}
