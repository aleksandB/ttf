package io.aleksb.springjwt.ttfmain.models;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "seasons")
@Data
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nbr_players")
    private Integer nbrPlayers;

    @Column(name="nbr_leagues")
    private Integer nbrLeagues;

    @Column(name="nbr_teams")
    private Integer nbrTeams;

    @Column(name="nbr_games")
    private Integer nbrGames;

    @Column(name="nbr_weeks")
    private Integer nbrWeeks;

    @Column(name="win_score")
    private Integer winScore;

    @Column(name="win_score3")
    private Integer winScore3;

    @Column(name="lost_score2")
    private Integer lostScore2;

    @Column(name="start_date")
    private Instant startDate;

    @Column(name="end_date")
    private Instant endDate;

    @Column(name="start_status",nullable = false)
    private Boolean startStatus;

    @Column(name="end_status",nullable = false)
    private Boolean endStatus;

}
