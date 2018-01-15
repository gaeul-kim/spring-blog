package xyz.sangsik.blog.domain;

import lombok.Data;

@Data
public class PostResponse extends CommonResponse {
    public void setSuccess(Post post) {
        setStatus(SUCCESS);
        setMessage("");
    }
}
