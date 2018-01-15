package xyz.sangsik.blog.domain;

import lombok.Data;

@Data
public class UserResponse extends CommonResponse {
    public void setDuplicateName() {
        setStatus(FAIL);
        setMessage("중복된 사용자 이름이 존재합니다.");
    }
}