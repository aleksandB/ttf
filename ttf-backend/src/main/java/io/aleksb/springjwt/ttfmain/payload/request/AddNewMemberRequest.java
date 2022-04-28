package io.aleksb.springjwt.ttfmain.payload.request;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

public class AddNewMemberRequest {
    @NotBlank
    private Long userId;

    @NotBlank
    public  Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
