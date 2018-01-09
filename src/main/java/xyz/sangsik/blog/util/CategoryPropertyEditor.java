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
            // todo : 바인딩이 실패했을경우 어떻게 페이지를 이동시킬것인가 고민
            this.setValue(Category.ALL);
        }
    }
}
