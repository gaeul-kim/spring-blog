package xyz.sangsik.blog.domain;

import lombok.Data;

@Data
public class CommonResponse {
    private String status;
    private String message;

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
}