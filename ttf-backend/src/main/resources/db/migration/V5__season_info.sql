CREATE TABLE IF NOT EXISTS season_info (
  id BIGINT NOT NULL AUTO_INCREMENT,
  season_id INT NOT NULL,
  player_id INT NOT NULL,
  games INT,
  games_won INT,
  games_lost INT,
  set_won INT,
  set_lost INT,
  score_won INT,
  score_lost INT,
  score_season INT,
  place INT,
  status ENUM('ACTUAL', 'FUTURE', 'DONE'),
  PRIMARY KEY (id),
  FOREIGN KEY(season_id) REFERENCES global_info(season),
  FOREIGN KEY(player_id) REFERENCES players(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
