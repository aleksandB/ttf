package io.aleksb.springjwt.ttfmain.models;

import io.aleksb.springjwt.auth.models.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name="games")
@Data
public class Game {
    @Id
    @GeneratedValue(generator = "games_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name="games_seq",
            sequenceName = "games_seq",
            allocationSize = 50
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "season_id", referencedColumnName = "season")
    private Global season;

    @OneToOne
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private Player player2;

    @Column(name="game_total_player1")
    private Integer gameTotalPlayer1;

    @Column(name="game_total_player2")
    private Integer gameTotalPlayer2;

    @Column(name="score_set1_player1")
    private Integer scoreSet1Player1;

    @Column(name="score_set1_player2")
    private Integer scoreSet1Player2;

    @Column(name="score_set2_player1")
    private Integer scoreSet2Player1;

    @Column(name="score_set2_player2")
    private Integer scoreSet2Player2;

    @Column(name="score_set3_player1")
    private Integer scoreSet3Player1;

    @Column(name="score_set3_player2")
    private Integer scoreSet3Player2;

    @Column(name="score_set4_player1")
    private Integer scoreSet4Player1;

    @Column(name="score_set4_player2")
    private Integer scoreSet4Player2;

    @Column(name="score_set5_player1")
    private Integer scoreSet5Player1;

    @Column(name="score_set5_player2")
    private Integer scoreSet5Player2;

    @NotNull
    @Column(name="date_game")
    private Instant dateGame;

    @Column(name="done")
    private Boolean done;

    @NotNull
    @Column(name="date_edit")
    private Instant dateEdit;

    @OneToOne
    @JoinColumn(name = "user_id_edit", referencedColumnName = "id")
    private User userEdit;

}
