package io.aleksb.springjwt.ttfmain.models;
import io.aleksb.springjwt.auth.models.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "season_info")
@Data
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @OneToOne
    @JoinColumn(name = "season_id", referencedColumnName = "season")
    private Global season;

    @OneToOne
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    private League league;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @Column(name="games")
    private Integer games;

    @Column(name="games_won")
    private Integer gamesWon;

    @Column(name="games_lost")
    private Integer gamesLost;

    @Column(name="set_won")
    private Integer setWon;

    @Column(name="set_lost")
    private Integer setLost;

    @Column(name="score_won")
    private Instant scoreWon;

    @Column(name="score_lost")
    private Instant scoreLost;

    @Column(name="score_season")
    private Instant scoreSeason;

    @Column(name="place")
    private Instant place;

    @Enumerated(EnumType.STRING)
    @Column(name="status",length = 20)
    private EStatus status;

}