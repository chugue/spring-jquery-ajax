package shop.mtcoding.blog.board;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status; //200, 400, 404, 405
    private String msg; // 성공, 실패시 -> 정확한 메시지
    private T body;

    public ApiUtil(T body){
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    public ApiUtil(Integer status, String msg){
        this.status = status;
        this.msg = msg;
        this.body = null;
    }
}
