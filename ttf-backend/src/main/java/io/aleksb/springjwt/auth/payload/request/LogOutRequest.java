package io.aleksb.springjwt.auth.payload.request;

public class LogOutRequest {
    private Long userId;
    public Long getUserId(){
        return this.userId;
    }
}
