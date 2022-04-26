package io.aleksb.springjwt.ttfmain.models;


import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "global_info",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "season")
        })
@Data
public class Global {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="season", nullable = false, unique = true)
    private Integer season;

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

    @Column(name="start_date")
    private Instant startDate;

    @Column(name="end_date")
    private Instant endDate;

    @Column(name="end_status",nullable = false)
    private Boolean endStatus;


}
