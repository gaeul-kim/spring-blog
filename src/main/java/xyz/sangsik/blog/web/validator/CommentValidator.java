package xyz.sangsik.blog.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import xyz.sangsik.blog.model.entity.Comment;

@Component
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Comment.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Comment comment = (Comment) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "post", "NotEmpty");
    }
}
