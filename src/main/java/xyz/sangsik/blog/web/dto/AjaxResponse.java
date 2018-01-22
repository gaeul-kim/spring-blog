package xyz.sangsik.blog.web.dto;

import lombok.Getter;
import xyz.sangsik.blog.domain.Post;

public class AjaxResponse {

    @Getter
    private String status;

    @Getter
    private String message;

    @Getter
    private Long id;

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    public void setBindingError() {
        this.status = FAIL;
        this.message = "입력값이 올바르지 않습니다.";
    }

    private void setError() {
        this.status = FAIL;
        this.message = "오류가 발생하였습니다.";
    }

    private void setSuccess(Long id) {
        this.id = id;
        this.status = SUCCESS;
    }

    public void makeResponse(Post post) {
        if (post == null) {
            setError();
        } else {
            setSuccess(post.getId());
        }
    }
}
