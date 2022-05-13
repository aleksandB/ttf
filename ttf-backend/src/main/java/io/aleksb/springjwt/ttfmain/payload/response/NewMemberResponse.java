package io.aleksb.springjwt.ttfmain.payload.response;

import lombok.Data;

@Data
public class NewMemberResponse {

    private int playerId;

    private String message;

    public NewMemberResponse(int playerId, String message) {
        this.playerId = playerId;
        this.message = message;
    }
}
