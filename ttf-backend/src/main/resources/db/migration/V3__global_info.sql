CREATE TABLE IF NOT EXISTS global_info (
  id INT NOT NULL AUTO_INCREMENT,
  season INT NOT NULL,
  nbr_players INT,
  nbr_leagues INT,
  nbr_teams INT,
  nbr_games INT,
  nbr_weeks INT,
  start_date DATETIME,
  end_date DATETIME,
  end_status TINYINT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_season (season)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO global_info (id, season, nbr_players, nbr_leagues, nbr_teams,nbr_games,nbr_weeks,start_date,end_date,end_status)
VALUES (1, 1, null,null,null,null,NULL, NULL, NULL, 0);

