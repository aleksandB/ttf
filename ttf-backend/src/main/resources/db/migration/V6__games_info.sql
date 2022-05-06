CREATE TABLE IF NOT EXISTS games (
  id BIGINT NOT NULL AUTO_INCREMENT,
  season_id INT NOT NULL,
  player1_id INT NOT NULL,
  player2_id INT NOT NULL,
  game_total_player1 INT,
  game_total_player2 INT,
  score_set1_player1 INT,
  score_set1_player2 INT,
  score_set2_player1 INT,
  score_set2_player2 INT,
  score_set3_player1 INT,
  score_set3_player2 INT,
  score_set4_player1 INT,
  score_set4_player2 INT,
  score_set5_player1 INT,
  score_set5_player2 INT,
  date_game DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  done TINYINT NOT NULL,
  date_edit DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  user_id_edit BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(season_id) REFERENCES seasons(id),
  FOREIGN KEY(player1_id) REFERENCES players(id),
  FOREIGN KEY(player2_id) REFERENCES players(id),
  FOREIGN KEY(user_id_edit) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
