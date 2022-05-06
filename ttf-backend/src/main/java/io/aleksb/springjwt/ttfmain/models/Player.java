package io.aleksb.springjwt.ttfmain.models;

import io.aleksb.springjwt.auth.models.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    private League league;

    @ManyToOne
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;

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
    private Integer scoreWon;

    @Column(name="score_lost")
    private Integer scoreLost;

    @Column(name="score_season")
    private Integer scoreSeason;

    @Column(name="place")
    private Integer place;

    @Enumerated(EnumType.STRING)
    @Column(name="status",length = 20)
    private EStatus status;

    public Player() {
    }

    public Player(Integer parentId, User user, Team team, League league, Season season, EStatus status) {
        this.parentId = parentId;
        this.user = user;
        this.team = team;
        this.league = league;
        this.season = season;
        this.status = status;
    }
}
