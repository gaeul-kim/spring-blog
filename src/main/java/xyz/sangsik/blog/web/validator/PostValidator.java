package xyz.sangsik.blog.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import xyz.sangsik.blog.repository.CategoryRepository;
import xyz.sangsik.blog.web.dto.post.PostRequestDto;

@Component
public class PostValidator implements Validator {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return PostRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostRequestDto dto = (PostRequestDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");

        if (!isCorrectCategory(dto.getCategory())) {
            errors.rejectValue("category", "Invalid.category.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty");
    }

    private boolean isCorrectCategory(Long category) {
        return categoryRepository.findOne(category) != null ? true : false;
    }
}
