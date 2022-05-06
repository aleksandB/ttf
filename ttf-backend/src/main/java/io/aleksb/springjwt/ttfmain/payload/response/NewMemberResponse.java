package io.aleksb.springjwt.ttfmain.payload.response;

import lombok.Data;

@Data
public class NewMemberResponse {

    private int playerId;
    private boolean statusCh;

    private String message;

    public NewMemberResponse(int playerId, boolean statusCh,String message) {
        this.playerId = playerId;
        this.statusCh = statusCh;
        this.message = message;
    }
}
