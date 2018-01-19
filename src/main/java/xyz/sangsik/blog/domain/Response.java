package xyz.sangsik.blog.domain;

import lombok.Getter;

public class Response {

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

    public void setError() {
        this.status = FAIL;
        this.message = "오류가 발생하였습니다.";
    }

    public void setSuccess() {
        this.status = SUCCESS;
        this.message = "요청이 성공적으로 완료되었습니다.";
    }

    public void setSuccess(Long id) {
        this.id = id;
        this.setSuccess();
    }

}
