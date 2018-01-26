package xyz.sangsik.blog.model.ResponseObject;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpResponse {
    HttpStatus status;
    Long id;

    public HttpResponse fail() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this;
    }

    public HttpResponse success() {
        this.status = HttpStatus.OK;
        return this;
    }

    public HttpResponse success(Long id) {
        this.id = id;
        this.status = HttpStatus.OK;
        return this;
    }
}
