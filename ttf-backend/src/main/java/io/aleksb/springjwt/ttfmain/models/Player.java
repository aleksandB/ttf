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

    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    private League league;

    public Player() {
    }

    public Player(Integer parentId, User user, Team team, League league) {
        this.parentId = parentId;
        this.user = user;
        this.team = team;
        this.league = league;
    }
}
