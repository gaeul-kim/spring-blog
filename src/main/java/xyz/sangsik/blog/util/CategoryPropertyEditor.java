package xyz.sangsik.blog.util;

import xyz.sangsik.blog.domain.Category;

import java.beans.PropertyEditorSupport;

/**
 * Created by sangsik on 2017-12-27.
 */
public class CategoryPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            this.setValue(Category.valueOf(text.trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            this.setValue(Category.INVALID);

        }
    }
}
