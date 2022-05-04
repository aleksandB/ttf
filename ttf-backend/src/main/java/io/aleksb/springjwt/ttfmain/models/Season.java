package io.aleksb.springjwt.ttfmain.models;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "season_info")
@Data
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "season_id", referencedColumnName = "season")
    private Global season;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

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
