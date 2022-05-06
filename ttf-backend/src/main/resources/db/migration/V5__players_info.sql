
CREATE TABLE IF NOT EXISTS players (
  id INT NOT NULL AUTO_INCREMENT,
  parent_id INT,
  user_id BIGINT NOT NULL,
  team_id INT NOT NULL,
  league_id INT NOT NULL,
  season_id INT NOT NULL,
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
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(team_id) REFERENCES teams(id),
  FOREIGN KEY(league_id) REFERENCES leagues(id),
  FOREIGN KEY(season_id) REFERENCES seasons(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
