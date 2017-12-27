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
            // todo : 존재하지 않는 카테고리를 요청할경우 어떻게 처리 할것인지.
            this.setValue(null);
        }
    }
}
