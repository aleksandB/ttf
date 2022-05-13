package io.aleksb.springjwt.ttfmain.payload.response;

import lombok.Data;

@Data
public class PlayersInfoResponse {
    private int playerId;
    private String username;
    private int league;

    public PlayersInfoResponse(int playerId, String username, int league) {
        this.playerId = playerId;
        this.username = username;
        this.league = league;
    }
}
