package xyz.sangsik.blog.domain;

import lombok.Data;

@Data
public class ResponseDTO {
    private Long id;
    private String status;
    private String message;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";


    public void setBindingError() {
        this.status = FAIL;
        this.message = "입력값이 올바르지 않습니다.";
    }

    public void setError() {
        this.status = FAIL;
        this.message = "오류가 발생하였습니다. 재시도 바랍니다.";
    }

    public void setSuccess(Post p) {
        this.status = SUCCESS;
        this.message = "";
        this.id = p.getId();
    }
}
