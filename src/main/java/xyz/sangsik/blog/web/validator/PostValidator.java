package xyz.sangsik.blog.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import xyz.sangsik.blog.model.entity.Category;
import xyz.sangsik.blog.model.entity.Post;
import xyz.sangsik.blog.repository.CategoryRepository;

@Component
public class PostValidator implements Validator {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Post.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Post post = (Post) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty");

        if (isInvalidCategory(post.getCategory())) {
            errors.rejectValue("category", "Invalid.category.name");
        }
    }

    private boolean isInvalidCategory(Category category) {
        return categoryRepository.findOne(category.getId()) == null ? true : false;
    }
}
